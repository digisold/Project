package com.digisold.web.manage.system.controller;

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
import com.digisold.web.manage.system.model.StoreListModel;
import com.digisold.web.manage.system.model.UserListModel;
import com.digisold.web.manage.system.service.RoleIfc;
import com.digisold.web.manage.system.service.StoreIfc;
import com.digisold.web.manage.system.service.UserIfc;
import com.digisold.web.manage.system.vo.UserListVo;
import com.digisold.web.mybatis.entity.SystemUser;

@Controller("userController")
@RequestMapping("/user")
public class UserController extends BaseController {

	private static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserIfc userService;

	@Autowired
	private RoleIfc roleService;

	@Autowired
	private StoreIfc storeService;

	final static int pageSize = 15;

	@RequestMapping("/index")
	public String userPage() {
		return "system/user";
	}

	/**
	 * 获取角色列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/role_list", method = RequestMethod.POST)
	@ResponseBody
	public String roleListController() {
		JSONObject result = new JSONObject();
		try {
			result.put("data", roleService.roleItems());
		} catch (Exception ex) {
			result.put("message", Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取角色列表出错[UserController.roleListController()].", ex);
		}
		return toJSONString(result);
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
			logger.error("获取案场列表出错[UserController.storeListController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 新增或修改用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveController(@ModelAttribute SystemUser user) {
		JSONObject result = new JSONObject();
		try {
			// 判断用户是否重复
			long checkRecord = userService.checkUsername(user);
			if (checkRecord > 0) {
				result.put(MESSAGE, "用户名已经存在！");
				return toJSONString(result);
			}
			String id = user.getId();
			final String date_fmt = "yyyy-MM-dd";
			String password = user.getPassword();
			String sdate = user.getSdate();
			String edate = user.getEdate();
			if (password != null && !"".equals(password)) {
				user.setPassword(ToolUtil.MD5Encoder(password));
			}
			if (sdate != null && !"".equals(sdate)) {
				user.setUseStartDate(ToolUtil.dateParse(date_fmt, sdate));
			}
			if (edate != null && !"".equals(edate)) {
				user.setUseEndDate(ToolUtil.dateParse(date_fmt, edate));
			}
			if (id != null && !"".equals(id)) {
				user.setUpdater(getUpdater());
				user.setUpdateDate(new Date());
				userService.editUser(user);
			} else {
				user.setId(ToolUtil.getUUID());
				user.setCreator(getCreator());
				userService.addUser(user);
			}
			result.put(MESSAGE, "保存成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存用户出错[UserController.saveController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 用户记录数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list_count", method = RequestMethod.POST)
	@ResponseBody
	public String listCountController() {
		JSONObject result = new JSONObject();
		try {
			long records = userService.countUserList();
			result.put(RECORDS, records);
			result.put(PAGESIZE, pageSize);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取用户记录数出错[UserController.listCountController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 用户列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String listController(@ModelAttribute UserListModel listModel, int currentPage) {
		JSONObject result = new JSONObject();
		try {
			listModel.setPageSize(pageSize);
			listModel.setPageIndex((currentPage - 1) * pageSize);
			List<UserListVo> list = userService.userList(listModel);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取用户列表出错[UserController.listController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 用户明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	@ResponseBody
	public String detailController(String id) {
		JSONObject result = new JSONObject();
		try {
			SystemUser user = userService.getUserById(id);
			result.put(DATA, user);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取用户明细出错[UserController.detailController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteController(String id) {
		JSONObject result = new JSONObject();
		try {
			userService.deleteUser(id);
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("删除用户出错[UserController.deleteController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 重置密码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/reset_password", method = RequestMethod.POST)
	@ResponseBody
	public String resetPwdController(String id) {
		JSONObject result = new JSONObject();
		try {
			String pwd = "abc@123";
			userService.resetPassage(id, ToolUtil.MD5Encoder(pwd));
			result.put(MESSAGE, "重置密码成功，新密码为：" + pwd);
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("重置密码出错[UserController.resetPwdController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 用户关联的案场列表
	 * 
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value = "/user_store_list", method = RequestMethod.POST)
	@ResponseBody
	public String userStoreListController(String userId) {
		JSONObject result = new JSONObject();
		try {
			result.put(DATA, userService.userStoreList(userId));
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取用户关联的案场列表出错[DeviceController.userStoreListController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 保存用户关联的案场
	 * 
	 * @param storeId
	 * @param passageId
	 * @return
	 */
	@RequestMapping(value = "/save_user_store", method = RequestMethod.POST)
	@ResponseBody
	public String saveUserStoreController(String userId, String[] storeItem) {
		JSONObject result = new JSONObject();
		try {
			userService.saveUserStore(userId, storeItem);
			result.put(MESSAGE, "保存成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存用户关联的案场出错[UserController.saveUserStoreController()].", ex);
		}
		return toJSONString(result);
	}
}
