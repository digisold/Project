package com.digisold.web.mybatis.dao;

import java.util.List;

import com.digisold.web.manage.system.model.RoleListModel;
import com.digisold.web.manage.system.vo.RoleListVo;
import com.digisold.web.mybatis.entity.Role;

public interface RoleMapper {
	int deleteByPrimaryKey(String id);

	int insert(Role record);

	int insertSelective(Role record);

	Role selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	Role getRoleByName(String name);

	long checkRoleName(Role role);

	long countRoleList();

	List<RoleListVo> roleList(RoleListModel listModel);

	List<Role> roleItems();
}