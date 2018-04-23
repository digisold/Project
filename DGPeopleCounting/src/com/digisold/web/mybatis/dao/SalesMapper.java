package com.digisold.web.mybatis.dao;

import java.util.List;

import com.digisold.web.manage.business_data.model.SaleListModel;
import com.digisold.web.manage.business_data.vo.SaleListVo;
import com.digisold.web.manage.business_data.vo.TotalSaleDataVo;
import com.digisold.web.mybatis.entity.Sales;

public interface SalesMapper {
	int deleteByPrimaryKey(String id);

	int insert(Sales record);

	int insertSelective(Sales record);

	Sales selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Sales record);

	int updateByPrimaryKey(Sales record);

	long countSaleList(SaleListModel listModel);

	List<SaleListVo> saleList(SaleListModel listModel);

	Sales selectSaleByDate(Sales sale);

	int updateSaleByDate(Sales sale);

	TotalSaleDataVo totalCountSale(SaleListModel listModel);

}