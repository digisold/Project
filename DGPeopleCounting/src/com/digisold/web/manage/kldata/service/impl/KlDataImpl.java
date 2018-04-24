package com.digisold.web.manage.kldata.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.core.util.ToolUtil;
import com.digisold.web.manage.kldata.model.KlDataListModel;
import com.digisold.web.manage.kldata.service.KlDataIfc;
import com.digisold.web.manage.kldata.vo.KlDataListVo;
import com.digisold.web.mybatis.dao.ActivityMapper;
import com.digisold.web.mybatis.dao.KlDataMapper;
import com.digisold.web.mybatis.dao.StoreMapper;
import com.digisold.web.mybatis.entity.Store;

@Service("kldataService")
public class KlDataImpl implements KlDataIfc {

	@Autowired
	KlDataMapper kldataMapper;

	@Autowired
	StoreMapper storeMapper;

	@Autowired
	ActivityMapper activityMapper;

	public Map<String, Object> klByDate(KlDataListModel listModel) {
		Map<String, Object> map = new HashMap<>();
		Store store = storeMapper.selectByPrimaryKey(listModel.getStoreId());
		listModel.setTableIdx(store.getDataTable());

		List<KlDataListVo> kldataList = null;

		String[] xAxis = null; // X坐标轴值
		long[] enterArray = null;
		long[] exitArray = null;
		long[] stayArray = null;
		long totalEnter = 0;
		long totalExit = 0;

		kldataList = kldataMapper.klByDate(listModel);
		Integer type = listModel.getType();
		switch (type) {
		case 1: // 日
			// 案场营业时间
			int business_start = store.getBusinessStartTime();
			int business_end = store.getBusinessEndTime();
			// END
			int arraySize = (business_end - business_start) + 1;
			xAxis = new String[arraySize];
			enterArray = new long[arraySize];
			exitArray = new long[arraySize];
			stayArray = new long[arraySize];
			int idx = 0;

			for (int i = business_start; i <= business_end; i++) {
				idx += 1;
				xAxis[idx - 1] = i + ":00";
				enterArray[idx - 1] = 0;
				exitArray[idx - 1] = 0;
				stayArray[idx - 1] = 0;
				for (KlDataListVo kldata : kldataList) {
					String date = kldata.getDate();
					if (i == Integer.valueOf(date)) {
						totalEnter += kldata.getEnters();
						totalExit += kldata.getExits();
						enterArray[idx - 1] = kldata.getEnters();
						exitArray[idx - 1] = kldata.getExits();
						stayArray[idx - 1] = (kldata.getEnters() - kldata.getExits());
						break;
					}
				}
			}
			break;
		case 2: // 周
			xAxis = new String[7];
			enterArray = new long[7];
			for (int i = 0; i < 7; i++) {
				Calendar cal = Calendar.getInstance();
				{
					cal.setTime(listModel.getStartDate());
					cal.add(Calendar.DATE, i);
				}
				xAxis[i] = ToolUtil.dateFormat("MM-dd(E)", cal.getTime()).replace("星期", "周");
				enterArray[i] = 0;
				String w_date = ToolUtil.dateFormat("yyyy-MM-dd", cal.getTime());
				for (KlDataListVo kldata : kldataList) {
					String date = kldata.getDate();
					if (w_date.equals(date)) {
						totalEnter += kldata.getEnters();
						enterArray[i] = kldata.getEnters();
						break;
					}
				}
			}
			break;
		case 3: // 月
			Calendar cal = Calendar.getInstance();
			cal.setTime(listModel.getStartDate());
			int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			xAxis = new String[maxDate];
			enterArray = new long[maxDate];
			for (int i = 1; i <= maxDate; i++) {
				String d_value = ToolUtil.dateFormat("yyyy-MM", listModel.getStartDate()) + "-" + (i < 10 ? "0" : "")
						+ i;
				Date mdate = ToolUtil.dateParse("yyyy-MM-dd", d_value);
				Calendar mcal = Calendar.getInstance();
				{
					mcal.setTime(mdate);
				}
				int week = mcal.get(Calendar.DAY_OF_WEEK);
				if (week == 1 || week == 7) {
					xAxis[i - 1] = ToolUtil.dateFormat("d(E)", mcal.getTime()).replace("星期", "周");
				} else {
					xAxis[i - 1] = String.valueOf(i);
				}
				enterArray[i - 1] = 0;
				for (KlDataListVo kldata : kldataList) {
					if (d_value.equals(kldata.getDate())) {
						totalEnter += kldata.getEnters();
						enterArray[i - 1] = kldata.getEnters();
						break;
					}
				}
			}
			break;
		case 4: // 年
			cal = Calendar.getInstance();
			xAxis = new String[12];
			enterArray = new long[12];
			for (int i = 1; i <= 12; i++) {
				String month = listModel.getSdate() + "-" + (i < 10 ? "0" : "") + i;
				xAxis[i - 1] = i + "月";
				enterArray[i - 1] = 0;
				for (KlDataListVo kldata : kldataList) {
					if (month.equals(kldata.getDate())) {
						totalEnter += kldata.getEnters();
						enterArray[i - 1] = kldata.getEnters();
						break;
					}
				}
			}
			break;
		case 5: // 自定义
			int days = (int) ((listModel.getEndDate().getTime() - listModel.getStartDate().getTime()) / 1000 / 24
					/ 3600) + 1;
			xAxis = new String[days];
			enterArray = new long[days];
			for (int i = 0; i < days; i++) {
				Calendar zcal = Calendar.getInstance();
				{
					zcal.setTime(listModel.getStartDate());
					zcal.add(Calendar.DATE, i);
				}
				int week = zcal.get(Calendar.DAY_OF_WEEK);
				String dt_fmt = "MM-dd";
				if (week == 1 || week == 7) {
					dt_fmt = "MM-dd(E)";
				}
				xAxis[i] = ToolUtil.dateFormat(dt_fmt, zcal.getTime()).replace("星期", "周");
				enterArray[i] = 0;
				String date_str = ToolUtil.dateFormat("yyyy-MM-dd", zcal.getTime());
				for (KlDataListVo kldata : kldataList) {
					if (date_str.equals(kldata.getDate())) {
						totalEnter += kldata.getEnters();
						enterArray[i] = kldata.getEnters();
						break;
					}
				}
			}
			break;
		}
		map.put("xAxis", xAxis);
		map.put("enterArray", enterArray);
		map.put("totalEnter", new DecimalFormat("#,##0").format(totalEnter));
		if (exitArray != null) {
			map.put("exitArray", exitArray);
			map.put("totalExit", totalExit);
		}
		if (stayArray != null) {
			map.put("stayArray", stayArray);
		}
		return map;
	}

	public Map<String, Object> activityListBydate(Date startDate, Date endDate) {
		return activityMapper.activityListByDate(startDate, endDate);
	}

}
