package com.digisold.web.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.digisold.web.manage.system.model.UserListModel;
import com.digisold.web.manage.system.vo.UserListVo;
import com.digisold.web.mybatis.entity.SystemUser;

public interface SystemUserMapper {
	int deleteByPrimaryKey(String id);

	int insert(SystemUser record);

	int insertSelective(SystemUser record);

	SystemUser selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(SystemUser record);

	int updateByPrimaryKey(SystemUser record);

	SystemUser getUserByUsername(String username);

	SystemUser userLogin(@Param("username") String username, @Param("password") String password);

	long checkUsername(SystemUser user);

	long countUserList();

	List<UserListVo> userList(UserListModel listModel);

	int resetPassage(@Param("userId") String userId, @Param("password") String password);
}