package com.digisold.web.mybatis.entity;

import java.util.Date;

public class Store {
    private String id;

    private String brandId;

    private String name;

    private Integer city;

    private Integer businessStartTime;

    private Integer businessEndTime;

    private Integer employees;

    private Integer area;

    private String contact;

    private String tel;

    private String parentId;

    private Integer dataTable;

    private String creator;

    private Date createDate;

    private String updater;

    private Date updateDate;

    public Store(String id, String brandId, String name, Integer city, Integer businessStartTime, Integer businessEndTime, Integer employees, Integer area, String contact, String tel, String parentId, Integer dataTable, String creator, Date createDate, String updater, Date updateDate) {
        this.id = id;
        this.brandId = brandId;
        this.name = name;
        this.city = city;
        this.businessStartTime = businessStartTime;
        this.businessEndTime = businessEndTime;
        this.employees = employees;
        this.area = area;
        this.contact = contact;
        this.tel = tel;
        this.parentId = parentId;
        this.dataTable = dataTable;
        this.creator = creator;
        this.createDate = createDate;
        this.updater = updater;
        this.updateDate = updateDate;
    }

    public Store() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getBusinessStartTime() {
        return businessStartTime;
    }

    public void setBusinessStartTime(Integer businessStartTime) {
        this.businessStartTime = businessStartTime;
    }

    public Integer getBusinessEndTime() {
        return businessEndTime;
    }

    public void setBusinessEndTime(Integer businessEndTime) {
        this.businessEndTime = businessEndTime;
    }

    public Integer getEmployees() {
        return employees;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getDataTable() {
        return dataTable;
    }

    public void setDataTable(Integer dataTable) {
        this.dataTable = dataTable;
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