package com.digisold.web.mybatis.entity;

public class StoreDevicePassage {
    private Integer id;

    private Integer storeDeviceId;

    private String passageId;

    private Integer status;

    private Integer dataReverse;

    public StoreDevicePassage(Integer id, Integer storeDeviceId, String passageId, Integer status, Integer dataReverse) {
        this.id = id;
        this.storeDeviceId = storeDeviceId;
        this.passageId = passageId;
        this.status = status;
        this.dataReverse = dataReverse;
    }

    public StoreDevicePassage() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreDeviceId() {
        return storeDeviceId;
    }

    public void setStoreDeviceId(Integer storeDeviceId) {
        this.storeDeviceId = storeDeviceId;
    }

    public String getPassageId() {
        return passageId;
    }

    public void setPassageId(String passageId) {
        this.passageId = passageId;
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