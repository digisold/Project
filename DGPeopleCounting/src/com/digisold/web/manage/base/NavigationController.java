package com.digisold.web.manage.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digisold.core.util.Constant;
import com.digisold.web.manage.system.service.RoleIfc;
import com.digisold.web.mybatis.dao.SystemMenuMapper;
import com.digisold.web.mybatis.entity.Role;
import com.digisold.web.mybatis.entity.SystemMenu;
import com.digisold.web.mybatis.entity.SystemUser;

@Controller
public class NavigationController extends BaseController {

	@Autowired
	private RoleIfc roleService;

	@Autowired
	private SystemMenuMapper sysMenuMapper;

	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String indexPage() {
		SystemUser user = (SystemUser) getSessionValue(Constant.LOGIN_USER_SESSION);
		String roleId = user.getRoleId();
		Role role = roleService.getRoleById(roleId);
		int[] menus = (int[]) parseJSONObject(role.getSysMenus(), int[].class);

		// 加载所有MENU
		final List<SystemMenu> menuList = sysMenuMapper.menuList();
		for (int i = menuList.size() - 1; i >= 0; i--) {
			SystemMenu menu = menuList.get(i);
			Integer mid = menu.getId();
			// 去除第三级菜单
			if (mid >= 1000) {
				boolean flag = false;
				for (int j = 0; j < menus.length; j++) {
					if (menus[j] == mid) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					menuList.remove(menu);
				}
			}
			// 去除第二级菜单
			if (mid >= 10 && mid <= 100) {
				boolean flag = false;
				for (SystemMenu sysMenu : menuList) {
					if (sysMenu.getParentMenuId() == mid) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					menuList.remove(menu);
				}
			}
			// 去除第一级菜单
			if (mid > 1 && mid <= 10) {
				boolean flag = false;
				for (SystemMenu sysMenu : menuList) {
					if (sysMenu.getParentMenuId() == mid) {
						flag = true;
						break;
					}
				}
				if (!flag) {
					menuList.remove(menu);
				}
			}
		}

		// 主菜单
		List<SystemMenu> rootMenuList = new ArrayList<>();
		for (SystemMenu menu : menuList) {
			Integer parentId = menu.getParentMenuId();
			if (parentId == null || parentId == 0) {
				rootMenuList.add(menu);
			}
		}
		setReqAttribute("rootMenuList", rootMenuList);

		// 子菜单
		List<Map<String, Object>> childMenuList = new ArrayList<>();
		for (SystemMenu menu : menuList) {
			Integer p_menuId = menu.getId();
			Integer parentId = menu.getParentMenuId();
			for (SystemMenu rootMenu : rootMenuList) {
				Integer rootMenuId = rootMenu.getId();
				if (parentId.intValue() == rootMenuId.intValue()) {
					Map<String, Object> map = new HashMap<>();
					// 加载子菜单
					List<SystemMenu> ppmenuList = new ArrayList<>();
					for (SystemMenu childMenu : menuList) {
						Integer pp_id = childMenu.getParentMenuId();
						if (pp_id.intValue() == p_menuId.intValue()) {
							ppmenuList.add(childMenu);
						}
					}
					map.put("child_menu", menu);
					map.put("sub_child_menulist", ppmenuList);
					childMenuList.add(map);
				}
			}
		}
		setReqAttribute("childMenuList", childMenuList);
		return "index";
	}
}
