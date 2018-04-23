package com.digisold.web.manage.system.service;

import java.util.List;

import com.digisold.web.mybatis.entity.Brand;

public interface BrandIfc {
	
	public void addBrand(Brand brand);

	public void editBrand(Brand brand);
	
	public void deleteBrand(String id);

	public Brand getBrandByName(Brand brand);

	public List<Brand> brandList();
	
	public Brand getBrandById(String id);
	
}
