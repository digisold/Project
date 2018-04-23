package com.digisold.web.manage.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.web.manage.system.service.BrandIfc;
import com.digisold.web.mybatis.dao.BrandMapper;
import com.digisold.web.mybatis.entity.Brand;

@Service("brandService")
public class BrandImpl implements BrandIfc {

	@Autowired
	BrandMapper brandMapper;

	public void addBrand(Brand brand) {
		brandMapper.insertSelective(brand);
	}

	public void editBrand(Brand brand) {
		brandMapper.updateByPrimaryKeySelective(brand);
	}

	public void deleteBrand(String id) {
		brandMapper.deleteByPrimaryKey(id);
	}
	
	public Brand getBrandByName(Brand brand) {
		return brandMapper.getBrandByName(brand);
	}

	public List<Brand> brandList() {
		return brandMapper.brandList();
	}

	public Brand getBrandById(String id) {
		return brandMapper.selectByPrimaryKey(id);
	}
}
