package com.digisold.web.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.digisold.web.manage.system.model.DeviceListModel;
import com.digisold.web.manage.system.vo.DeviceListVo;
import com.digisold.web.mybatis.entity.Device;

public interface DeviceMapper {
	int deleteByPrimaryKey(String id);

	int insert(Device record);

	int insertSelective(Device record);

	Device selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Device record);

	int updateByPrimaryKey(Device record);

	long checkCodeIsExists(Device device);

	long countDeviceList(DeviceListModel listModel);

	List<DeviceListVo> deviceList(DeviceListModel listModel);

	public List<Map<?, ?>> deviceListByStore(String storeId);
}