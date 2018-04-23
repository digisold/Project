package com.digisold.web.mybatis.dao;

import java.util.List;

import com.digisold.web.mybatis.entity.China;

public interface ChinaMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(China record);

	int insertSelective(China record);

	China selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(China record);

	int updateByPrimaryKey(China record);

	List<China> chinaList();
}