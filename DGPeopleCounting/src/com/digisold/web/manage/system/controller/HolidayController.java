package com.digisold.web.manage.system.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.digisold.web.manage.system.model.HolidayListModel;
import com.digisold.web.manage.system.service.HolidayIfc;
import com.digisold.web.manage.system.vo.HolidayListVo;
import com.digisold.web.mybatis.entity.Holiday;

@Controller("holidayController")
@RequestMapping("/holiday")
public class HolidayController extends BaseController {

	private static Logger logger = Logger.getLogger(HolidayController.class);

	@Autowired
	private HolidayIfc holidayService;

	final static int pageSize = 15;

	@RequestMapping("/index")
	public String holidayPage() {
		return "system/holiday";
	}

	/**
	 * 新增或修改节假日
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveController(@ModelAttribute Holiday holiday, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			final String date_fmt = "yyyy-MM-dd";
			holiday.setStartDate(ToolUtil.dateParse(date_fmt, holiday.getSdate()));
			holiday.setEndDate(ToolUtil.dateParse(date_fmt, holiday.getEdate()));
			String id = holiday.getId();
			if (id != null && !"".equals(id)) {
				holiday.setUpdater(getUpdater());
				holiday.setUpdateDate(new Date());
				holidayService.editHoliday(holiday);
			} else {
				holiday.setId(ToolUtil.getUUID());
				holiday.setCreator(getCreator());
				holidayService.addHoliday(holiday);
			}
			result.put(MESSAGE, "保存成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存节假日出错[HolidayController.saveController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 节假日记录数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list_count", method = RequestMethod.POST)
	@ResponseBody
	public String listCountController(@ModelAttribute HolidayListModel listModel) {
		JSONObject result = new JSONObject();
		try {
			long records = holidayService.countHolidayList(listModel);
			result.put(RECORDS, records);
			result.put(PAGESIZE, pageSize);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取节假日记录数出错[HolidayController.listCountController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 节假日列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String listController(@ModelAttribute HolidayListModel listModel, int currentPage) {
		JSONObject result = new JSONObject();
		try {
			listModel.setPageSize(pageSize);
			listModel.setPageIndex((currentPage - 1) * pageSize);
			List<Holiday> list = holidayService.holidayList(listModel);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取节假日列表出错[HolidayController.listController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 节假日明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	@ResponseBody
	public String detailController(String id) {
		JSONObject result = new JSONObject();
		try {
			HolidayListVo holiday = holidayService.getHolidayById(id);
			result.put(DATA, holiday);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取节假日明细出错[HolidayController.detailController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 删除节假日
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteController(String id) {
		JSONObject result = new JSONObject();
		try {
			holidayService.deleteHoliday(id);
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("删除节假日出错[HolidayController.deleteController()].", ex);
		}
		return toJSONString(result);
	}
}
