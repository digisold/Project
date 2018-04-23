package com.digisold.web.mybatis.entity;

import java.util.Date;

public class Sales {
	private String id;

	private String storeId;

	private Date saleDate;

	private String saleDateStr;

	private Integer orders;

	private Integer amount;

	private Double total;

	private String creator;

	private Date createDate;

	private String updater;

	private Date updateDate;

	public Sales(String id, String storeId, Date saleDate, Integer orders, Integer amount, Double total, String creator,
			Date createDate, String updater, Date updateDate) {
		this.id = id;
		this.storeId = storeId;
		this.saleDate = saleDate;
		this.orders = orders;
		this.amount = amount;
		this.total = total;
		this.creator = creator;
		this.createDate = createDate;
		this.updater = updater;
		this.updateDate = updateDate;
	}

	public Sales() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getSaleDateStr() {
		return saleDateStr;
	}

	public void setSaleDateStr(String saleDateStr) {
		this.saleDateStr = saleDateStr;
	}
}