package com.digisold.web.manage.kldata.model;

import java.util.Date;

public class KlDataListModel {

	private String storeId;

	private Integer type = 1; // 1: 日，2：周，3：月，4：年，5：自定义

	private Integer tableIdx;

	private String dateformat;

	private Date startDate;

	private String sdate;

	private Date endDate;

	private String edate;

	private String passageId;
	
	private Integer isMain;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Integer getTableIdx() {
		return tableIdx;
	}

	public void setTableIdx(Integer tableIdx) {
		this.tableIdx = tableIdx;
	}

	public String getDateformat() {
		return dateformat;
	}

	public void setDateformat(String dateformat) {
		this.dateformat = dateformat;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPassageId() {
		return passageId;
	}

	public void setPassageId(String passageId) {
		this.passageId = passageId;
	}

	public Integer getIsMain() {
		return isMain;
	}

	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}
}
