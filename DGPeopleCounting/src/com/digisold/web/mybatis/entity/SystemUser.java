package com.digisold.web.mybatis.entity;

import java.util.Date;

public class SystemUser {
	private String id;

	private String username;

	private String password;

	private String roleId;

	private Integer status;

	private Date useStartDate;

	private Date useEndDate;

	private String sdate;

	private String edate;

	private String creator;

	private Date createDate;

	private String updater;

	private Date updateDate;

	public SystemUser(String id, String username, String password, String roleId, Integer status, Date useStartDate,
			Date useEndDate, String creator, Date createDate, String updater, Date updateDate) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.roleId = roleId;
		this.status = status;
		this.useStartDate = useStartDate;
		this.useEndDate = useEndDate;
		this.creator = creator;
		this.createDate = createDate;
		this.updater = updater;
		this.updateDate = updateDate;
	}

	public SystemUser() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUseStartDate() {
		return useStartDate;
	}

	public void setUseStartDate(Date useStartDate) {
		this.useStartDate = useStartDate;
	}

	public Date getUseEndDate() {
		return useEndDate;
	}

	public void setUseEndDate(Date useEndDate) {
		this.useEndDate = useEndDate;
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

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}
}