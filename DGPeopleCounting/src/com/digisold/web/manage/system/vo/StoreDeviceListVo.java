package com.digisold.web.manage.system.vo;

public class StoreDeviceListVo {

	private Integer id; // 主键ID

	private String storeId; // 案场ID

	private String storeName; // 案场名称

	private String deviceId; // 设备ID

	private String code; // 设备码

	private Integer type; // 设备类型

	private String ip; // 设备IP

	private String lastUploadDate; // 数据的最后上传时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
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

}
