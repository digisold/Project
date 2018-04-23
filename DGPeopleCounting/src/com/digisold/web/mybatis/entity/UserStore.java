package com.digisold.web.mybatis.entity;

public class UserStore {
	private Integer id;

	private String userId;

	private String storeId;

	public UserStore(Integer id, String userId, String storeId) {
		this.id = id;
		this.userId = userId;
		this.storeId = storeId;
	}

	public UserStore() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
}