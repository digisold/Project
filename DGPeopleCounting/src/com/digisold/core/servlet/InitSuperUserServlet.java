package com.digisold.core.servlet;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.digisold.core.util.FastJsonUtil;
import com.digisold.core.util.ToolUtil;
import com.digisold.web.manage.system.service.RoleIfc;
import com.digisold.web.manage.system.service.UserIfc;
import com.digisold.web.mybatis.entity.Role;
import com.digisold.web.mybatis.entity.SystemMenu;
import com.digisold.web.mybatis.entity.SystemUser;

@Component
public class InitSuperUserServlet extends HttpServlet {

	/**
	 * 初始化超级用户
	 */
	private static final long serialVersionUID = 1545451986998288777L;

	private static Logger logger = Logger.getLogger(InitSuperUserServlet.class);

	private static final String rolename = "系统管理员";

	private static final String roledesc = "此角色权限赋予系统的超级管理员使用";

	public static final String username = "admin";

	private static final String password = "abc@123";

	@Autowired
	private RoleIfc roleService;

	@Autowired
	private UserIfc userService;

	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		logger.info("Init system...............");
		try {
			// 初始化角色
			Role role = roleService.getRoleByName(rolename);
			if (role == null) {
				role = new Role();
				{
					role.setName(rolename);
					role.setDescription(roledesc);
					role.setCreator("0");
				}
			}
			List<SystemMenu> systemMenuList = roleService.menuList();
			int[] tmp_menus = new int[systemMenuList.size()];
			int idx = 0;
			for (SystemMenu menu : systemMenuList) {
				Integer menuId = menu.getId();
				if (menuId > 1000) {
					tmp_menus[idx++] = menuId;
				}
			}
			int[] target_menus = new int[idx];
			System.arraycopy(tmp_menus, 0, target_menus, 0, idx);
			role.setSysMenus(FastJsonUtil.toJSONString(target_menus));
			String roleId = role.getId();
			if (roleId != null && !"".equals(roleId)) {
				role.setUpdater("0");
				role.setUpdateDate(new Date());
				roleService.editRole(role);
			} else {
				role.setId(ToolUtil.getUUID());
				roleService.addRole(role);
			}

			// 初始化超级管理员
			SystemUser user = userService.getUserByUsername(username);
			if (user == null) {
				user = new SystemUser();
				{
					user.setUsername(username);
					user.setPassword(ToolUtil.MD5Encoder(password));
					user.setRoleId(role.getId());
					user.setCreator("0");
				}
			}
			String userId = user.getId();
			if (userId != null && !"".equals(userId)) {
				user.setUpdater("0");
				user.setUpdateDate(new Date());
				userService.editUser(user);
			} else {
				user.setId(ToolUtil.getUUID());
				userService.addUser(user);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}
}
