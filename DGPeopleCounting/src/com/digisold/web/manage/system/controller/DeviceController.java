package com.digisold.web.manage.system.controller;

import java.util.Date;
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
import com.digisold.web.manage.system.model.DeviceListModel;
import com.digisold.web.manage.system.model.StoreListModel;
import com.digisold.web.manage.system.service.DeviceIfc;
import com.digisold.web.manage.system.service.PassageIfc;
import com.digisold.web.manage.system.service.StoreIfc;
import com.digisold.web.manage.system.vo.DeviceListVo;
import com.digisold.web.mybatis.entity.Device;

@Controller("deviceController")
@RequestMapping("/device")
public class DeviceController extends BaseController {

	private static Logger logger = Logger.getLogger(DeviceController.class);

	@Autowired
	private DeviceIfc deviceService;

	@Autowired
	private PassageIfc passageService;

	@Autowired
	private StoreIfc storeService;

	final static int pageSize = 15;

	@RequestMapping("/index")
	public String devicePage() {
		return "system/device";
	}

	/**
	 * 案场列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/store_list", method = RequestMethod.POST)
	@ResponseBody
	public String storeListController() {
		JSONObject result = new JSONObject();
		try {
			StoreListModel listModel = new StoreListModel();
			listModel.setPageIndex(0);
			listModel.setPageSize(10000);
			List<?> list = storeService.storeList(listModel);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取案场列表出错[DeviceController.storeListController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 新增或修改设备
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveController(@ModelAttribute Device device) {
		JSONObject result = new JSONObject();
		try {
			// 判断设备码是否重复
			long checkRecord = deviceService.checkCodeIsExists(device);
			if (checkRecord > 0) {
				result.put(MESSAGE, "设备码已经存在！");
				return toJSONString(result);
			}
			String id = device.getId();
			if (id != null && !"".equals(id)) {
				device.setUpdater(getUpdater());
				device.setUpdateDate(new Date());
				deviceService.saveDevice(device);
			} else {
				device.setCreator(getCreator());
				deviceService.saveDevice(device);
			}
			result.put(MESSAGE, "保存成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存设备出错[DeviceController.saveController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 设备记录数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list_count", method = RequestMethod.POST)
	@ResponseBody
	public String listCountController(@ModelAttribute DeviceListModel listModel) {
		JSONObject result = new JSONObject();
		try {
			long records = deviceService.countDeviceList(listModel);
			result.put(RECORDS, records);
			result.put(PAGESIZE, pageSize);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取设备记录数出错[DeviceController.listCountController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 设备列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String listController(@ModelAttribute DeviceListModel listModel, int currentPage) {
		JSONObject result = new JSONObject();
		try {
			listModel.setPageSize(pageSize);
			listModel.setPageIndex((currentPage - 1) * pageSize);
			List<DeviceListVo> list = deviceService.deviceList(listModel);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取设备列表出错[DeviceController.listController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 设备明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	@ResponseBody
	public String detailController(String id) {
		JSONObject result = new JSONObject();
		try {
			Device device = deviceService.getDeviceById(id);
			result.put(DATA, device);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取设备明细出错[DeviceController.detailController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 删除设备
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteController(String id) {
		JSONObject result = new JSONObject();
		try {
			deviceService.deleteDevice(id);
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("删除设备出错[DeviceController.deleteController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 获取案场的通道
	 * 
	 * @param storeId
	 * @return
	 */
	@RequestMapping(value = "/passage_list", method = RequestMethod.POST)
	@ResponseBody
	public String passageListController(String storeId) {
		JSONObject result = new JSONObject();
		try {
			List<Map<?, ?>> list = passageService.passageListByStore(storeId);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取案场的通道 出错[DeviceController.passageListController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 设备所在的案场列表
	 * 
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value = "/store_device_list", method = RequestMethod.POST)
	@ResponseBody
	public String storeDeviceListController(String deviceId) {
		JSONObject result = new JSONObject();
		try {
			result.put(DATA, deviceService.storeListByDevice(deviceId));
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取设备所在的案场列表出错[DeviceController.storeListByDevice()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 保存设备关联的案场
	 * 
	 * @param storeId
	 * @param passageId
	 * @return
	 */
	@RequestMapping(value = "/save_device_store", method = RequestMethod.POST)
	@ResponseBody
	public String saveDeviceStoreController(String deviceId, String[] storeItem) {
		JSONObject result = new JSONObject();
		try {
			deviceService.saveDeviceStore(deviceId, storeItem);
			result.put(MESSAGE, "保存成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存设备关联的案场出错[DeviceController.saveDeviceStoreController()].", ex);
		}
		return toJSONString(result);
	}
}
