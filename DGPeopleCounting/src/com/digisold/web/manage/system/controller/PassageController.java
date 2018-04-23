package com.digisold.web.manage.system.controller;

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
import com.digisold.web.manage.system.model.PassageListModel;
import com.digisold.web.manage.system.service.PassageIfc;
import com.digisold.web.manage.system.vo.PassageListVo;
import com.digisold.web.mybatis.entity.Passage;

@Controller("passageController")
@RequestMapping("/passage")
public class PassageController extends BaseController {

	private static Logger logger = Logger.getLogger(PassageController.class);

	@Autowired
	private PassageIfc passageService;

	final static int pageSize = 15;

	@RequestMapping("/index")
	public String passagePage() {
		return "system/passage";
	}

	/**
	 * 新增或修改通道
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveController(@ModelAttribute Passage passage, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			// 判断通道名称是否重复
			long checkRecord = passageService.checkNameIsExists(passage);
			if (checkRecord > 0) {
				result.put(MESSAGE, "通道名称已经存在！");
				return toJSONString(result);
			}
			String id = passage.getId();
			if (id != null && !"".equals(id)) {
				passage.setUpdater(getUpdater());
				passage.setUpdateDate(new Date());
				passageService.editPassage(passage);
			} else {
				passage.setId(ToolUtil.getUUID());
				passage.setCreator(getCreator());
				passageService.addPassage(passage);
			}
			result.put(MESSAGE, "保存成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存通道出错[PassageController.saveController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 通道记录数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list_count", method = RequestMethod.POST)
	@ResponseBody
	public String listCountController(@ModelAttribute PassageListModel listModel) {
		JSONObject result = new JSONObject();
		try {
			long records = passageService.countPassageList(listModel);
			result.put(RECORDS, records);
			result.put(PAGESIZE, pageSize);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取通道记录数出错[PassageController.listCountController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 通道列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String listController(@ModelAttribute PassageListModel listModel, int currentPage) {
		JSONObject result = new JSONObject();
		try {
			listModel.setPageSize(pageSize);
			listModel.setPageIndex((currentPage - 1) * pageSize);
			List<PassageListVo> list = passageService.passageList(listModel);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取通道列表出错[PassageController.listController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 通道明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	@ResponseBody
	public String detailController(String id) {
		JSONObject result = new JSONObject();
		try {
			Passage passage = passageService.getPassageById(id);
			result.put(DATA, passage);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取通道明细出错[PassageController.detailController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 删除通道
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteController(String id) {
		JSONObject result = new JSONObject();
		try {
			passageService.deletePassage(id);
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("删除通道出错[PassageController.deleteController()].", ex);
		}
		return toJSONString(result);
	}
}
