package com.digisold.web.manage.system.vo;

public class DeviceListVo {

	private String id; // 主键ID

	private String code; // 设备码

	private Integer type; // 设备类型

	private String ip; // 设备IP

	private String lastUploadDate; // 数据的最后上传时间

	private Long stores; // 设备关联的案场数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLastUploadDate() {
		return lastUploadDate;
	}

	public void setLastUploadDate(String lastUploadDate) {
		this.lastUploadDate = lastUploadDate;
	}

	public Long getStores() {
		return stores;
	}

	public void setStores(Long stores) {
		this.stores = stores;
	}

}
