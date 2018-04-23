package com.digisold.web.manage.business_data.service;

import java.util.List;

import com.digisold.web.manage.business_data.model.SaleListModel;
import com.digisold.web.manage.business_data.vo.SaleListVo;
import com.digisold.web.manage.business_data.vo.TotalSaleDataVo;
import com.digisold.web.mybatis.entity.Sales;

public interface SaleDataIfc {

	public void addSale(Sales sale);

	public void editSale(Sales sale);

	public void deleteSale(String saleId);

	public Sales getSaleById(String saleId);

	public long countSaleList(SaleListModel listModel);

	public List<SaleListVo> saleList(SaleListModel listModel);

	public Sales selectSaleByDate(Sales sale);

	public int updateSaleByDate(Sales sale);
	
	public TotalSaleDataVo totalCountSale(SaleListModel listModel);

}
