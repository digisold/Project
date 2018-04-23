package com.digisold.web.manage.business_data.controller;

import java.util.Date;
import java.util.List;

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
import com.digisold.web.manage.business_data.model.ActivityListModel;
import com.digisold.web.manage.business_data.service.ActivityIfc;
import com.digisold.web.manage.business_data.vo.ActivityListVo;
import com.digisold.web.mybatis.entity.Activity;

@Controller("activityController")
@RequestMapping("/activity")
public class ActivityController extends BaseController {

	private static Logger logger = Logger.getLogger(ActivityController.class);

	@Autowired
	private ActivityIfc activityService;

	final static int pageSize = 15;

	@RequestMapping("/index")
	public String activityPage() {
		return "business_data/activity";
	}

	/**
	 * 新增或修改活动
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveController(@ModelAttribute Activity activity) {
		JSONObject result = new JSONObject();
		try {
			String id = activity.getId();
			final String date_fmt = "yyyy-MM-dd";
			String sdate = activity.getSdate();
			String edate = activity.getEdate();
			activity.setStartDate(ToolUtil.dateParse(date_fmt, sdate));
			activity.setEndDate(ToolUtil.dateParse(date_fmt, edate));
			if (activity.getStartDate().getTime() > activity.getEndDate().getTime()) {
				result.put(MESSAGE, "开始时间不能大于结束时间！");
				return toJSONString(result);
			}
			if (id != null && !"".equals(id)) {
				activity.setUpdater(getUpdater());
				activity.setUpdateDate(new Date());
				activityService.editActivity(activity);
			} else {
				activity.setId(ToolUtil.getUUID());
				activity.setCreator(getCreator());
				activityService.addActivity(activity);
			}
			result.put(MESSAGE, "保存成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存活动出错[ActivityController.saveController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 活动记录数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list_count", method = RequestMethod.POST)
	@ResponseBody
	public String listCountController(@ModelAttribute ActivityListModel listModel) {
		JSONObject result = new JSONObject();
		try {
			long records = activityService.countActivityList(listModel);
			result.put(RECORDS, records);
			result.put(PAGESIZE, pageSize);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取活动记录数出错[ActivityController.listCountController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 活动列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String listController(@ModelAttribute ActivityListModel listModel, int currentPage) {
		JSONObject result = new JSONObject();
		try {
			listModel.setPageSize(pageSize);
			listModel.setPageIndex((currentPage - 1) * pageSize);
			List<ActivityListVo> list = activityService.activityList(listModel);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取活动列表出错[ActivityController.listController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 活动明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	@ResponseBody
	public String detailController(String id) {
		JSONObject result = new JSONObject();
		try {
			Activity activity = activityService.getActivityById(id);
			result.put(DATA, activity);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取活动明细出错[ActivityController.detailController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 删除活动
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteController(String id) {
		JSONObject result = new JSONObject();
		try {
			activityService.deleteActivity(id);
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("删除活动出错[ActivityController.deleteController()].", ex);
		}
		return toJSONString(result);
	}
}
