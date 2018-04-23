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
import com.digisold.web.manage.system.model.PassageDeviceListModel;
import com.digisold.web.manage.system.service.PassageIfc;
import com.digisold.web.manage.system.service.StoreDevicePassageIfc;
import com.digisold.web.manage.system.vo.PassageDeviceListVo;

@Controller("passageDeviceController")
@RequestMapping("/passage_device")
public class PassageDeviceController extends BaseController {

	private static Logger logger = Logger.getLogger(PassageDeviceController.class);

	@Autowired
	private StoreDevicePassageIfc sdpService;

	@Autowired
	private PassageIfc passageService;

	final static int pageSize = 15;

	@RequestMapping("/index")
	public String devicePage() {
		return "system/passage_device";
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
			logger.error("获取案场通道列表出错[PassageDeviceController.storePassageListController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 通道设备记录数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list_count", method = RequestMethod.POST)
	@ResponseBody
	public String listCountController(@ModelAttribute PassageDeviceListModel listModel) {
		JSONObject result = new JSONObject();
		try {
			long records = sdpService.countPassageDeviceList(listModel);
			result.put(RECORDS, records);
			result.put(PAGESIZE, pageSize);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取通道设备记录数出错[PassageDeviceController.listCountController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 通道设备列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String listController(@ModelAttribute PassageDeviceListModel listModel, int currentPage) {
		JSONObject result = new JSONObject();
		try {
			listModel.setPageSize(pageSize);
			listModel.setPageIndex((currentPage - 1) * pageSize);
			List<PassageDeviceListVo> list = sdpService.passageDeviceList(listModel);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取通道设备列表出错[PassageDeviceController.listController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 删除通道设备
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteController(Integer id) {
		JSONObject result = new JSONObject();
		try {
			sdpService.deletePassageDevice(id);
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("删除通道设备出错[PassageDeviceController.deleteController()].", ex);
		}
		return toJSONString(result);
	}
}
