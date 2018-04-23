package com.digisold.web.manage.system.model;

public class PassageDeviceListModel {

	private String storeId;

	private String passageId;

	private int pageIndex;

	private int pageSize;

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getPassageId() {
		return passageId;
	}

	public void setPassageId(String passageId) {
		this.passageId = passageId;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
