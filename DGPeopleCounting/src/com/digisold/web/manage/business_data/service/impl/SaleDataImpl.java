package com.digisold.web.manage.business_data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.web.manage.business_data.model.SaleListModel;
import com.digisold.web.manage.business_data.service.SaleDataIfc;
import com.digisold.web.manage.business_data.vo.SaleListVo;
import com.digisold.web.manage.business_data.vo.TotalSaleDataVo;
import com.digisold.web.mybatis.dao.SalesMapper;
import com.digisold.web.mybatis.entity.Sales;

@Service("saleDataService")
public class SaleDataImpl implements SaleDataIfc {

	@Autowired
	SalesMapper saleMapper;

	public void addSale(Sales sale) {
		saleMapper.insertSelective(sale);
	}

	public void editSale(Sales sale) {
		saleMapper.updateByPrimaryKeySelective(sale);
	}

	public void deleteSale(String saleId) {
		saleMapper.deleteByPrimaryKey(saleId);
	}

	public Sales getSaleById(String saleId) {
		return saleMapper.selectByPrimaryKey(saleId);
	}

	public long countSaleList(SaleListModel listModel) {
		return saleMapper.countSaleList(listModel);
	}

	public List<SaleListVo> saleList(SaleListModel listModel) {
		return saleMapper.saleList(listModel);
	}

	public Sales selectSaleByDate(Sales sale) {
		return saleMapper.selectSaleByDate(sale);
	}

	public int updateSaleByDate(Sales sale) {
		return saleMapper.updateSaleByDate(sale);
	}

	public TotalSaleDataVo totalCountSale(SaleListModel listModel) {
		return saleMapper.totalCountSale(listModel);
	}
}
