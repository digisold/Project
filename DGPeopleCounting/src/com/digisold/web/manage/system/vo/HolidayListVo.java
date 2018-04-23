package com.digisold.web.manage.system.vo;

public class HolidayListVo {

	private String id; // 主键ID
	
	private String name; // 节假日名称
	
	private String startDate; // 假日开始时间
	
	private String endDate; // 假日结束时间

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
