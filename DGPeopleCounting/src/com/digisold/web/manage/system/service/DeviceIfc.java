package com.digisold.web.manage.system.service;

import java.util.List;
import java.util.Map;

import com.digisold.web.manage.system.model.DeviceListModel;
import com.digisold.web.manage.system.vo.DeviceListVo;
import com.digisold.web.mybatis.entity.Device;
import com.digisold.web.mybatis.entity.StoreDevice;

public interface DeviceIfc {
	public void saveDevice(Device device);

	public void deleteDevice(String id);

	public Device getDeviceById(String id);

	public long checkCodeIsExists(Device device);

	public long countDeviceList(DeviceListModel listModel);

	public List<DeviceListVo> deviceList(DeviceListModel listModel);

	public List<Map<?, ?>> deviceListByStore(String storeId);

	public List<StoreDevice> storeListByDevice(String deviceId);
	
	public void saveDeviceStore(String deviceId, String[] storeItem);
}
