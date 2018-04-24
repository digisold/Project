package com.digisold.web.manage.system.vo;

public class PassageDeviceListVo {

	private Integer id; // 主键ID

	private String storeName; // 案场名称

	private String passsageType; // 通道类型名称

	private String name; // 通道名称

	private String code; // 设备码

	private String ip; // 设备IP

	private String lastUploadDate; // 设备数据最后一次上传的时间

	private Integer status; // 通道设备状态

	private Integer dataReverse; // 进出反转

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getPasssageType() {
		return passsageType;
	}

	public void setPasssageType(String passsageType) {
		this.passsageType = passsageType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getLastUploadDate() {
		return lastUploadDate;
	}

	public void setLastUploadDate(String lastUploadDate) {
		this.lastUploadDate = lastUploadDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDataReverse() {
		return dataReverse;
	}

	public void setDataReverse(Integer dataReverse) {
		this.dataReverse = dataReverse;
	}

}
