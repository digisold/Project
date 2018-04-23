package com.digisold.web.manage.system.vo;

public class StoreListVo {

	private String id; // 主键ID

	private String brandName; // 品牌名称

	private String name; // 案场名称

	private Integer bstartTime; // 营业开始时间

	private Integer bendTime; // 营业结束时间

	private String province; // 省份

	private String city; // 城市

	private String createDate; // 创建时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBstartTime() {
		return bstartTime;
	}

	public void setBstartTime(Integer bstartTime) {
		this.bstartTime = bstartTime;
	}

	public Integer getBendTime() {
		return bendTime;
	}

	public void setBendTime(Integer bendTime) {
		this.bendTime = bendTime;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
