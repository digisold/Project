package com.digisold.web.mybatis.entity;

import java.util.Date;

public class Role {
	private String id;

	private String name;

	private String sysMenus;

	private String description;

	private String creator;

	private Date createDate;

	private String updater;

	private Date updateDate;

	public Role(String id, String name, String sysMenus, String description, String creator, Date createDate,
			String updater, Date updateDate) {
		this.id = id;
		this.name = name;
		this.sysMenus = sysMenus;
		this.description = description;
		this.creator = creator;
		this.createDate = createDate;
		this.updater = updater;
		this.updateDate = updateDate;
	}

	public Role() {
		super();
	}

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

	public String getSysMenus() {
		return sysMenus;
	}

	public void setSysMenus(String sysMenus) {
		this.sysMenus = sysMenus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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