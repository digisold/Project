package com.digisold.web.manage.system.service;

import java.util.List;

import com.digisold.web.manage.system.model.RoleListModel;
import com.digisold.web.manage.system.vo.RoleListVo;
import com.digisold.web.mybatis.entity.Role;
import com.digisold.web.mybatis.entity.SystemMenu;

public interface RoleIfc {

	public void addRole(Role role);

	public void editRole(Role role);

	public void deleteRole(String id);

	public Role getRoleByName(String name);

	public List<SystemMenu> menuList();

	public Role getRoleById(String id);

	public long checkRoleName(Role role);

	public long countRoleList();

	public List<RoleListVo> roleList(RoleListModel listModel);
	
	public List<Role> roleItems();

}
