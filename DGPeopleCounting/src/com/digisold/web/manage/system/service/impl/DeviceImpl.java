package com.digisold.web.manage.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.core.util.ToolUtil;
import com.digisold.web.manage.system.model.DeviceListModel;
import com.digisold.web.manage.system.service.DeviceIfc;
import com.digisold.web.manage.system.vo.DeviceListVo;
import com.digisold.web.mybatis.dao.ChinaMapper;
import com.digisold.web.mybatis.dao.DeviceMapper;
import com.digisold.web.mybatis.dao.StoreDeviceMapper;
import com.digisold.web.mybatis.entity.Device;
import com.digisold.web.mybatis.entity.StoreDevice;

@Service("deviceService")
public class DeviceImpl implements DeviceIfc {

	@Autowired
	DeviceMapper deviceMapper;

	@Autowired
	ChinaMapper chinaMapper;

	@Autowired
	StoreDeviceMapper storeDeviceMapper;

	public void saveDevice(Device device) {
		String id = device.getId();
		if (id != null && !"".equals(id)) {
			deviceMapper.updateByPrimaryKeySelective(device);
		} else {
			device.setId(ToolUtil.getUUID());
			deviceMapper.insertSelective(device);
		}
	}

	public void deleteDevice(String id) {
		deviceMapper.deleteByPrimaryKey(id);
	}

	public Device getDeviceById(String id) {
		return deviceMapper.selectByPrimaryKey(id);
	}

	public long checkCodeIsExists(Device device) {
		return deviceMapper.checkCodeIsExists(device);
	}

	public long countDeviceList(DeviceListModel listModel) {
		return deviceMapper.countDeviceList(listModel);
	}

	public List<DeviceListVo> deviceList(DeviceListModel listModel) {
		return deviceMapper.deviceList(listModel);
	}

	public List<Map<?, ?>> deviceListByStore(String storeId) {
		return deviceMapper.deviceListByStore(storeId);
	}

	public List<StoreDevice> storeListByDevice(String deviceId) {
		return storeDeviceMapper.storeListByDevice(deviceId);
	}

	public void saveDeviceStore(String deviceId, String[] storeItem) {
		if (storeItem == null) {
			storeItem = new String[0];
		}
		List<StoreDevice> storeDeviceList = storeDeviceMapper.storeListByDevice(deviceId);
		// 删除不存在的
		for (StoreDevice sd : storeDeviceList) {
			String sid = sd.getStoreId();
			boolean flag = false;
			for (int i = 0; i < storeItem.length; i++) {
				if (sid.equals(storeItem[i])) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				storeDeviceMapper.deleteByPrimaryKey(sd.getId());
			}
		}
		// 是否已经存在
		for (int i = 0; i < storeItem.length; i++) {
			String sid = storeItem[i];
			boolean flag = false;
			for (StoreDevice sd : storeDeviceList) {
				if (sid.equals(sd.getStoreId())) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				StoreDevice sd = new StoreDevice();
				{
					sd.setStoreId(storeItem[i]);
					sd.setDeviceId(deviceId);
				}
				storeDeviceMapper.insertSelective(sd);
			}
		}
	}
}
