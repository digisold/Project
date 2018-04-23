package com.digisold.web.manage.system.controller;

import java.util.ArrayList;
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
import com.digisold.web.manage.system.model.RoleListModel;
import com.digisold.web.manage.system.service.RoleIfc;
import com.digisold.web.manage.system.vo.RoleListVo;
import com.digisold.web.mybatis.entity.Role;
import com.digisold.web.mybatis.entity.SystemMenu;

@Controller("roleController")
@RequestMapping("/role")
public class RoleController extends BaseController {

	private static Logger logger = Logger.getLogger(RoleController.class);

	@Autowired
	private RoleIfc roleService;

	final static int pageSize = 15;

	@RequestMapping("/index")
	public String rolePage() {
		return "system/role";
	}

	/**
	 * 获取系统所有的菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/system_menu_list", method = RequestMethod.POST)
	@ResponseBody
	public String systemMenuListController() {
		JSONObject result = new JSONObject();
		try {
			List<SystemMenu> list = new ArrayList<>();
			List<SystemMenu> menuList = roleService.menuList();
			for (SystemMenu menu : menuList) {
				Integer menuId = menu.getId().intValue();
				Integer parentId = menu.getParentMenuId().intValue();
				if (parentId == null || parentId == 0) {
					list.add(menu);
					for (SystemMenu childMenu : menuList) {
						Integer c_menuId = childMenu.getId().intValue();
						Integer c_parentId = childMenu.getParentMenuId().intValue();
						if (c_parentId == menuId) {
							childMenu.setName(childMenu.getName());
							list.add(childMenu);
							for (SystemMenu childMenu2 : menuList) {
								Integer c2_parentId = childMenu2.getParentMenuId().intValue();
								if (c2_parentId == c_menuId) {
									childMenu2.setName(childMenu2.getName());
									list.add(childMenu2);
								}
							}
						}
					}
				}
			}
			result.put("data", list);
		} catch (Exception ex) {
			result.put("message", Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("加载系统菜单出错[RoleController.systemMenuListController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 新增或修改角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveController(@ModelAttribute Role role, Integer[] menuId) {
		JSONObject result = new JSONObject();
		try {
			// 判断角色是否重复
			long checkRecord = roleService.checkRoleName(role);
			if (checkRecord > 0) {
				result.put(MESSAGE, "角色已经存在！");
				return toJSONString(result);
			}

			if (menuId == null) {
				menuId = new Integer[0];
			}
			int[] obj = new int[100];
			int idx = 0;
			for (int i = 0; i < menuId.length; i++) {
				if (menuId[i] >= 1000) {
					obj[idx++] = menuId[i];
				}
			}
			int[] menuArray = new int[idx];
			System.arraycopy(obj, 0, menuArray, 0, idx);

			String id = role.getId();
			role.setSysMenus(toJSONString(menuArray));
			if (id != null && !"".equals(id)) {
				role.setUpdater(getUpdater());
				role.setUpdateDate(new Date());
				roleService.editRole(role);
			} else {
				role.setId(ToolUtil.getUUID());
				role.setCreator(getCreator());
				roleService.addRole(role);
			}
			result.put(MESSAGE, "保存成功！");
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("保存角色出错[RoleController.saveController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 角色记录数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list_count", method = RequestMethod.POST)
	@ResponseBody
	public String listCountController() {
		JSONObject result = new JSONObject();
		try {
			long records = roleService.countRoleList();
			result.put(RECORDS, records);
			result.put(PAGESIZE, pageSize);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取角色记录数出错[RoleController.listCountController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 角色列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public String listController(@ModelAttribute RoleListModel listModel, int currentPage) {
		JSONObject result = new JSONObject();
		try {
			listModel.setPageSize(pageSize);
			listModel.setPageIndex((currentPage - 1) * pageSize);
			List<RoleListVo> list = roleService.roleList(listModel);
			result.put(DATA, list);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取角色列表出错[RoleController.listController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 角色明细
	 * 
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	@ResponseBody
	public String detailController(String id) {
		JSONObject result = new JSONObject();
		try {
			Role role = roleService.getRoleById(id);
			result.put(DATA, role);
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("获取角色明细出错[RoleController.detailController()].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 删除角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public String deleteController(String id) {
		JSONObject result = new JSONObject();
		try {
			roleService.deleteRole(id);
			result.put(STATUS, "10000");
		} catch (Exception ex) {
			result.put(MESSAGE, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("删除角色出错[RoleController.deleteController()].", ex);
		}
		return toJSONString(result);
	}
}
