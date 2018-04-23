package com.digisold.web.mybatis.entity;

public class StoreDevice {
    private Integer id;

    private String storeId;

    private String deviceId;

    public StoreDevice(Integer id, String storeId, String deviceId) {
        this.id = id;
        this.storeId = storeId;
        this.deviceId = deviceId;
    }

    public StoreDevice() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}