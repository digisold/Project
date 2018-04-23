package com.digisold.web.manage.business_data.vo;

public class TotalSaleDataVo {

	private Long totalOrders; // 总交易数

	private Long totalAmount; // 总销售数量

	private Double total; // 总销售额

	public Long getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(Long totalOrders) {
		this.totalOrders = totalOrders;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
