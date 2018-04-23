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
import com.digisold.web.manage.system.model.StoreListModel;
import com.digisold.web.manage.system.service.BrandIfc;
import com.digisold.web.manage.system.service.StoreIfc;
import com.digisold.web.mybatis.entity.Brand;
import com.digisold.web.mybatis.entity.China;
import com.digisold.web.mybatis.entity.Store;

@Controller("storeController")
@RequestMapping("/store")
public class StoreController extends BaseController {

	private static Logger logger = Logger.getLogger(StoreController.class);

	@Autowired
	private StoreIfc storeService;

	@Autowired
	private BrandIfc brandService;

	final static int pageSize = 15;

	@RequestMapping("/index")
	public String storePage() {
		return "system/store";
	}

	/**
	 * 品牌列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/brand_list", method = RequestMethod.POST)
	@ResponseBody
	public String brandListController() {
		JSONObject result = new JSONObject();
		try {
			List<Brand> list = brandService.brandList();
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取品牌列表出错[StoreController.brandListController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 整个中国地区列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/area_list", method = RequestMethod.POST)
	@ResponseBody
	public String areaListController() {
		JSONObject result = new JSONObject();
		try {
			List<China> list = storeService.chinaList();
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取整个中国地区列表出错[StoreController.areaListController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 新增或修改案场
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveController(@ModelAttribute Store store, HttpServletRequest request) {
		JSONObject result = new JSONObject();
		try {
			String id = store.getId();
			if (id != null && !"".equals(id)) {
				store.setUpdater(getUpdater());
				store.setUpdateDate(new Date());
				storeService.editStore(store);
			} else {
				store.setId(ToolUtil.getUUID());
				store.setCreator(getCreator());
				storeService.addStore(store);
			}
			result.put(MESSAGE, "保存成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存案场出错[StoreController.saveController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 案场记录数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list_count", method = RequestMethod.POST)
	@ResponseBody
	public String listCountController(@ModelAttribute StoreListModel listModel) {
		JSONObject result = new JSONObject();
		try {
			long records = storeService.countStoreList(listModel);
			result.put(RECORDS, records);
			result.put(PAGESIZE, pageSize);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取案场记录数出错[StoreController.listCountController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 案场列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String listController(@ModelAttribute StoreListModel listModel, int currentPage) {
		JSONObject result = new JSONObject();
		try {
			listModel.setPageSize(pageSize);
			listModel.setPageIndex((currentPage - 1) * pageSize);
			List<?> list = storeService.storeList(listModel);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取案场列表出错[StoreController.listController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 案场明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	@ResponseBody
	public String detailController(String id) {
		JSONObject result = new JSONObject();
		try {
			Store store = storeService.getStoreById(id);
			result.put(DATA, store);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取案场明细出错[StoreController.detailController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 删除案场
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteController(String id) {
		JSONObject result = new JSONObject();
		try {
			storeService.deleteStore(id);
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("删除案场出错[StoreController.deleteController()].", ex);
		}
		return toJSONString(result);
	}
}
