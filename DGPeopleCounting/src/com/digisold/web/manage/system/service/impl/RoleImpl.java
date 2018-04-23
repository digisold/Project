package com.digisold.web.manage.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.web.manage.system.model.RoleListModel;
import com.digisold.web.manage.system.service.RoleIfc;
import com.digisold.web.manage.system.vo.RoleListVo;
import com.digisold.web.mybatis.dao.RoleMapper;
import com.digisold.web.mybatis.dao.SystemMenuMapper;
import com.digisold.web.mybatis.entity.Role;
import com.digisold.web.mybatis.entity.SystemMenu;

@Service("roleService")
public class RoleImpl implements RoleIfc {

	@Autowired
	RoleMapper roleMapper;

	@Autowired
	SystemMenuMapper sysMenuMapper;

	public void addRole(Role role) {
		roleMapper.insertSelective(role);
	}

	public void editRole(Role role) {
		roleMapper.updateByPrimaryKeySelective(role);
	}

	public void deleteRole(String id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	public Role getRoleByName(String name) {
		return roleMapper.getRoleByName(name);
	}

	public List<SystemMenu> menuList() {
		return sysMenuMapper.menuList();
	}

	public Role getRoleById(String id) {
		return roleMapper.selectByPrimaryKey(id);
	}

	public long checkRoleName(Role role) {
		return roleMapper.checkRoleName(role);
	}

	public long countRoleList() {
		return roleMapper.countRoleList();
	}

	public List<RoleListVo> roleList(RoleListModel listModel) {
		return roleMapper.roleList(listModel);
	}

	public List<Role> roleItems() {
		return roleMapper.roleItems();
	}
}
