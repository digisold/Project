package com.digisold.web.manage.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.digisold.core.servlet.InitSuperUserServlet;
import com.digisold.core.util.Constant;
import com.digisold.web.manage.system.service.StoreIfc;
import com.digisold.web.mybatis.entity.Store;
import com.digisold.web.mybatis.entity.SystemUser;

@Controller
@RequestMapping("/base_data")
public class CommonDataController extends BaseController {

	private static Logger logger = Logger.getLogger(CommonDataController.class);

	@Autowired
	StoreIfc storeService;

	/**
	 * 物流列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/store_list", method = RequestMethod.POST)
	@ResponseBody
	public String storeListController() {
		JSONObject result = new JSONObject();
		try {
			SystemUser user = (SystemUser) getSessionValue(Constant.LOGIN_USER_SESSION);
			List<Store> list = new ArrayList<>();
			boolean isSuperUser = user.getUsername().equalsIgnoreCase(InitSuperUserServlet.username);
			List<Store> storeList = storeService.storeListByUser(isSuperUser ? null : user.getId());
			for (Store store : storeList) {
				String id = store.getId();
				String parentId = store.getParentId();
				if (parentId == null || "".equals(parentId)) {
					list.add(store);
					for (Store store2 : storeList) {
						String pid = store2.getParentId();
						if (pid != null && pid.equalsIgnoreCase(id)) {
							list.add(store2);
						}
					}
				}
			}
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put("message", Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取案场列表出错[CommonDataController.storeListController()].", ex);
		}
		return toJSONString(result);
	}

}
