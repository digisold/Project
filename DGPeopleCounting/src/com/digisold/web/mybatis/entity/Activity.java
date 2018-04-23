package com.digisold.web.mybatis.entity;

import java.util.Date;

public class Activity {
	private String id;

	private String storeId;

	private String name;

	private Date startDate;

	private Date endDate;
	
	private String sdate;

	private String edate;

	private String description;

	private String creator;

	private Date createDate;

	private String updater;

	private Date updateDate;

	public Activity(String id, String storeId, String name, Date startDate, Date endDate, String description,
			String creator, Date createDate, String updater, Date updateDate) {
		this.id = id;
		this.storeId = storeId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.creator = creator;
		this.createDate = createDate;
		this.updater = updater;
		this.updateDate = updateDate;
	}

	public Activity() {
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}
}