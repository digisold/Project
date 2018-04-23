package com.digisold.web.manage.system.controller;

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
import com.digisold.web.manage.base.BaseController;
import com.digisold.web.manage.system.model.StoreDeviceListModel;
import com.digisold.web.manage.system.service.PassageIfc;
import com.digisold.web.manage.system.service.StoreDeviceIfc;
import com.digisold.web.manage.system.service.StoreDevicePassageIfc;
import com.digisold.web.manage.system.vo.StoreDeviceListVo;
import com.digisold.web.mybatis.entity.StoreDevicePassage;

@Controller("storeDeviceController")
@RequestMapping("/store_device")
public class StoreDeviceController extends BaseController {

	private static Logger logger = Logger.getLogger(StoreDeviceController.class);

	@Autowired
	private StoreDeviceIfc storeDeviceService;

	@Autowired
	private PassageIfc passageService;

	@Autowired
	private StoreDevicePassageIfc storeDevicePassageService;

	final static int pageSize = 15;

	@RequestMapping("/index")
	public String devicePage() {
		return "system/store_device";
	}

	/**
	 * 案场设备记录数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list_count", method = RequestMethod.POST)
	@ResponseBody
	public String listCountController(@ModelAttribute StoreDeviceListModel listModel) {
		JSONObject result = new JSONObject();
		try {
			long records = storeDeviceService.countStoreDeviceList(listModel);
			result.put(RECORDS, records);
			result.put(PAGESIZE, pageSize);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取案场设备记录数出错[StoreDeviceController.listCountController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 案场设备列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String listController(@ModelAttribute StoreDeviceListModel listModel, int currentPage) {
		JSONObject result = new JSONObject();
		try {
			listModel.setPageSize(pageSize);
			listModel.setPageIndex((currentPage - 1) * pageSize);
			List<StoreDeviceListVo> list = storeDeviceService.storeDeviceList(listModel);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取案场设备列表出错[StoreDeviceController.listController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 删除案场设备
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteController(Integer id) {
		JSONObject result = new JSONObject();
		try {
			storeDeviceService.deleteStoreDevice(id);
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("删除案场设备出错[StoreDeviceController.deleteController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 案场通道列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/store_passage_list", method = RequestMethod.POST)
	@ResponseBody
	public String storePassageListController(String storeId) {
		JSONObject result = new JSONObject();
		try {
			List<Map<?, ?>> passageList = passageService.passageListByStore(storeId);
			result.put(DATA, passageList);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取案场通道列表出错[StoreDeviceController.storePassageListController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 案场设备通道明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/get_store_device_passage", method = RequestMethod.POST)
	@ResponseBody
	public String getStoreDevicePassageController(Integer storeDeviceId) {
		JSONObject result = new JSONObject();
		try {
			result.put(DATA, storeDevicePassageService.detailByStoreDeviceId(storeDeviceId));
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取案场设备通道明细出错[StoreDeviceController.getStoreDevicePassageController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 保存案场设备的通道
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save_store_device_passage", method = RequestMethod.POST)
	@ResponseBody
	public String saveStoreDevicePassageController(StoreDevicePassage passage) {
		JSONObject result = new JSONObject();
		try {
			if (passage.getPassageId() != null) {
				storeDeviceService.saveStoreDevicePassage(passage);
			} else {
				result.put(MESSAGE, "请选择案场通道！");
			}
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存案场设备的通道出错[StoreDeviceController.saveStoreDevicePassageController()].", ex);
		}
		return toJSONString(result);
	}
}
