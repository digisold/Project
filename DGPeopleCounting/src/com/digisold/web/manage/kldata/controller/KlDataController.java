package com.digisold.web.manage.kldata.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.digisold.web.manage.system.service.PassageTypeIfc;
import com.digisold.web.mybatis.entity.Passage;
import com.digisold.web.mybatis.entity.PassageType;

@Controller("klDataController")
@RequestMapping("/kldata")
public class KlDataController extends BaseController {

	private static Logger logger = Logger.getLogger(KlDataController.class);

	@Autowired
	KlDataIfc kldataService;

	@Autowired
	PassageTypeIfc passageTypeService;

	@Autowired
	PassageIfc passageService;

	@RequestMapping("/date")
	public String datePage() {
		setReqAttribute("curDate", ToolUtil.dateFormat("yyyy-MM-dd", new Date()));
		return "data-query/date";
	}

	@RequestMapping("/week")
	public String weekPage() {
		Date date = new Date();
		setReqAttribute("endDate", ToolUtil.dateFormat("yyyy-MM-dd", date));
		Calendar cal = Calendar.getInstance();
		{
			cal.add(Calendar.DATE, -6);
		}
		setReqAttribute("startDate", ToolUtil.dateFormat("yyyy-MM-dd", cal.getTime()));
		return "data-query/week";
	}

	@RequestMapping("/month")
	public String monthPage() {
		Date date = new Date();
		setReqAttribute("curMonth", ToolUtil.dateFormat("yyyy-MM", date));
		return "data-query/month";
	}

	@RequestMapping("/year")
	public String yearPage() {
		Date date = new Date();
		setReqAttribute("curYear", ToolUtil.dateFormat("yyyy", date));
		return "data-query/year";
	}

	@RequestMapping("/custom")
	public String customPage() {
		Date date = new Date();
		setReqAttribute("endDate", ToolUtil.dateFormat("yyyy-MM-dd", date));
		Calendar cal = Calendar.getInstance();
		{
			cal.add(Calendar.DATE, -14);
		}
		setReqAttribute("startDate", ToolUtil.dateFormat("yyyy-MM-dd", cal.getTime()));
		return "data-query/custom";
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

	@RequestMapping(value = "/getPassageTypeList", method = RequestMethod.POST)
	@ResponseBody
	public String passageTypeListController(@ModelAttribute KlDataListModel listModel) {
		JSONObject result = new JSONObject();
		try {
			String storeId = listModel.getStoreId();
			List<PassageType> ptypeList = passageTypeService.ptypeListByStore(storeId);
			result.put(DATA, ptypeList);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取通道类型出错[KlDataController.passageTypeListController()].", ex);
		}
		return toJSONString(result);
	}

	@RequestMapping(value = "/getPassageKlDataList", method = RequestMethod.POST)
	@ResponseBody
	public String passageKlDataController(@ModelAttribute KlDataListModel listModel, Integer passageTypeId) {
		JSONObject result = new JSONObject();
		try {
			List<Map<String, Object>> list = new ArrayList<>();
			listModel = setttingDate(listModel);
			List<Passage> passageList = passageService.passageListByType(passageTypeId);
			for (Passage passage : passageList) {
				Map<String, Object> map = new LinkedHashMap<>();
				map.put("passage", passage);
				listModel.setPassageId(passage.getId());
				Map<String, Object> klMap = kldataService.klByDate(listModel);
				map.put("passageKl", klMap);
				list.add(map);
			}
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取通道客流数据出错[KlDataController.passageKlDataController()].", ex);
		}
		return toJSONString(result);
	}

}
