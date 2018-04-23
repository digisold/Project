package com.digisold.web.manage.kldata.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.digisold.core.util.Constant;
import com.digisold.core.util.ToolUtil;
import com.digisold.web.manage.base.BaseController;
import com.digisold.web.manage.kldata.model.KlDataListModel;
import com.digisold.web.manage.kldata.service.KlDataIfc;
import com.digisold.web.manage.system.service.PassageIfc;

@Controller("klDataController")
@RequestMapping("/kldata")
public class KlDataController extends BaseController {

	private static Logger logger = Logger.getLogger(KlDataController.class);

	@Autowired
	KlDataIfc kldataService;

	@Autowired
	PassageIfc passageService;

	@RequestMapping(value = "/passageList", method = RequestMethod.POST)
	@ResponseBody
	public String storePassageListController(String storeId) {
		JSONObject result = new JSONObject();
		try {
			result.put(DATA, passageService.passageListByStore(storeId));
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取案场通道列表出错[KlDataController.storePassageListController()].", ex);
		}
		return toJSONString(result);
	}

	public KlDataListModel setttingDate(KlDataListModel listModel) {
		final String[] dateformat = { "%H", "%Y-%m-%d", "%Y-%m-%d", "%Y-%m", "%Y-%m-%d" };
		final String dfmtStr = "yyyy-MM-dd HH:mm:ss";
		Integer type = listModel.getType();
		String sdate = listModel.getSdate();
		String edate = listModel.getEdate();
		listModel.setDateformat(dateformat[type - 1]);

		switch (type) {
		case 1:
			listModel.setStartDate(ToolUtil.dateParse(dfmtStr, sdate + " 00:00:00"));
			listModel.setEndDate(ToolUtil.dateParse(dfmtStr, sdate + " 23:59:59"));
			break;
		case 2:
			listModel.setStartDate(ToolUtil.dateParse(dfmtStr, sdate + " 00:00:00"));
			listModel.setEndDate(ToolUtil.dateParse(dfmtStr, edate + " 23:59:59"));
			break;
		case 3:
			String ymDtfmt = "yyyy-MM";
			Date date = ToolUtil.dateParse(ymDtfmt, sdate);
			listModel.setStartDate(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			listModel.setEndDate(ToolUtil.dateParse(dfmtStr, sdate + "-" + maxDate + " 23:59:59"));
			break;
		case 4:
			listModel.setStartDate(ToolUtil.dateParse(dfmtStr, sdate + "-01-01 00:00:00"));
			listModel.setEndDate(ToolUtil.dateParse(dfmtStr, sdate + "-12-31 23:59:59"));
			break;
		case 5:
			listModel.setStartDate(ToolUtil.dateParse(dfmtStr, sdate + " 00:00:00"));
			listModel.setEndDate(ToolUtil.dateParse(dfmtStr, edate + " 23:59:59"));
			break;
		}
		return listModel;
	}

	@RequestMapping(value = "/getKlDataList", method = RequestMethod.POST)
	@ResponseBody
	public String kldataListController(@ModelAttribute KlDataListModel listModel) {
		JSONObject result = new JSONObject();
		try {
			// listModel.setStoreId("ce18a786-3ad4-11e8-90f9-3d1ca403d28f");
			// listModel.setType(3);
			// listModel.setSdate("2018-4");
			// listModel.setPassageId("e99d7c19-3ad4-11e8-90f9-3d1ca403d28f");
			// listModel.setEdate("2018-04-15");

			Map<String, Object> map = kldataService.klByDate(setttingDate(listModel));
			result.put(DATA, map);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取客流数据出错[KlDataController.kldataListController()].", ex);
		}
		return toJSONString(result);
	}

	@RequestMapping(value = "/activityList", method = RequestMethod.POST)
	@ResponseBody
	public String activityListController(@ModelAttribute KlDataListModel listModel) {
		JSONObject result = new JSONObject();
		try {
			listModel = setttingDate(listModel);
			Map<String, Object> activity = kldataService.activityListBydate(listModel.getStartDate(),
					listModel.getEndDate());
			result.put(DATA, activity);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取活动明细出错[KlDataController.activityListController()].", ex);
		}
		return toJSONString(result);
	}

}
