package com.digisold.web.mybatis.dao;

import java.util.List;

import com.digisold.web.mybatis.entity.UserStore;

public interface UserStoreMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserStore record);

	int insertSelective(UserStore record);

	UserStore selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserStore record);

	int updateByPrimaryKey(UserStore record);

	List<UserStore> userStoreList(String userId);
}