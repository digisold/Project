package com.digisold.web.mybatis.entity;

import java.util.Date;

public class KlData {
	private Integer id;

	private String deviceId;

	private Integer enters;

	private Integer exits;

	private Date startDate;

	private Date endDate;

	private Date createDate;

	public KlData(Integer id, String deviceId, Integer enters, Integer exits, Date startDate, Date endDate,
			Date createDate) {
		this.id = id;
		this.deviceId = deviceId;
		this.enters = enters;
		this.exits = exits;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createDate = createDate;
	}

	public KlData() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getEnters() {
		return enters;
	}

	public void setEnters(Integer enters) {
		this.enters = enters;
	}

	public Integer getExits() {
		return exits;
	}

	public void setExits(Integer exits) {
		this.exits = exits;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}