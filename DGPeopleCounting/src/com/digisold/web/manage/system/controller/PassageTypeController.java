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
import com.digisold.web.manage.base.BaseController;
import com.digisold.web.manage.system.model.PassageTypeListModel;
import com.digisold.web.manage.system.service.PassageTypeIfc;
import com.digisold.web.manage.system.vo.PassageTypeListVo;
import com.digisold.web.mybatis.entity.PassageType;

@Controller("passageTypeController")
@RequestMapping("/passage_type")
public class PassageTypeController extends BaseController {

	private static Logger logger = Logger.getLogger(PassageTypeController.class);

	@Autowired
	private PassageTypeIfc passageTypeService;

	final static int pageSize = 15;

	@RequestMapping("/index")
	public String passageTypePage() {
		return "system/passage_type";
	}

	/**
	 * 新增或修改通道类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveController(@ModelAttribute PassageType passageType, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			// 判断通道类型名称是否重复
			long checkRecord = passageTypeService.checkNameIsExists(passageType);
			if (checkRecord > 0) {
				result.put(MESSAGE, "类型名称已经存在！");
				return toJSONString(result);
			}
			Integer id = passageType.getId();
			if (id != null && id > 0) {
				passageType.setUpdater(getUpdater());
				passageType.setUpdateDate(new Date());
				passageTypeService.editPassageType(passageType);
			} else {
				passageType.setCreator(getCreator());
				passageTypeService.addPassageType(passageType);
			}
			result.put(MESSAGE, "保存成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存通道类型出错[PassageTypeController.saveController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 通道类型记录数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list_count", method = RequestMethod.POST)
	@ResponseBody
	public String listCountController(@ModelAttribute PassageTypeListModel listModel) {
		JSONObject result = new JSONObject();
		try {
			long records = passageTypeService.countPassageTypeList(listModel);
			result.put(RECORDS, records);
			result.put(PAGESIZE, pageSize);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取通道类型记录数出错[PassageTypeController.listCountController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 通道类型列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String listController(@ModelAttribute PassageTypeListModel listModel, int currentPage) {
		JSONObject result = new JSONObject();
		try {
			listModel.setPageSize(pageSize);
			listModel.setPageIndex((currentPage - 1) * pageSize);
			List<PassageTypeListVo> list = passageTypeService.passageTypeList(listModel);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取通道类型列表出错[PassageTypeController.listController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 通道类型明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	@ResponseBody
	public String detailController(Integer id) {
		JSONObject result = new JSONObject();
		try {
			PassageType passageType = passageTypeService.getPassageTypeById(id);
			result.put(DATA, passageType);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取通道类型明细出错[PassageTypeController.detailController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 删除通道类型
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteController(Integer id) {
		JSONObject result = new JSONObject();
		try {
			passageTypeService.deletePassageType(id);
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("删除通道类型出错[PassageTypeController.deleteController()].", ex);
		}
		return toJSONString(result);
	}
}
