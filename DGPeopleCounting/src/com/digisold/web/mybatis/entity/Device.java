package com.digisold.web.mybatis.entity;

import java.util.Date;

public class Device {
	private String id;

	private Integer type;

	private String code;

	private String ip;

	private String macAddress;

	private Date lastUploadDate;

	private String creator;

	private Date createDate;

	private String updater;

	private Date updateDate;

	public Device(String id, Integer type, String code, String ip, String macAddress, Date lastUploadDate,
			String creator, Date createDate, String updater, Date updateDate) {
		this.id = id;
		this.type = type;
		this.code = code;
		this.ip = ip;
		this.macAddress = macAddress;
		this.lastUploadDate = lastUploadDate;
		this.creator = creator;
		this.createDate = createDate;
		this.updater = updater;
		this.updateDate = updateDate;
	}

	public Device() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public Date getLastUploadDate() {
		return lastUploadDate;
	}

	public void setLastUploadDate(Date lastUploadDate) {
		this.lastUploadDate = lastUploadDate;
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
}