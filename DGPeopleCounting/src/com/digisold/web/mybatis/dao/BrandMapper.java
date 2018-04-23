package com.digisold.web.mybatis.dao;

import java.util.List;

import com.digisold.web.mybatis.entity.Brand;

public interface BrandMapper {
	int deleteByPrimaryKey(String id);

	int insert(Brand record);

	int insertSelective(Brand record);

	Brand selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Brand record);

	int updateByPrimaryKey(Brand record);

	List<Brand> brandList();

	Brand getBrandByName(Brand brand);
}