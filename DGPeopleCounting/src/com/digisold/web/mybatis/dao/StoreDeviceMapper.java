package com.digisold.web.mybatis.dao;

import java.util.List;

import com.digisold.web.manage.system.model.StoreDeviceListModel;
import com.digisold.web.manage.system.vo.StoreDeviceListVo;
import com.digisold.web.mybatis.entity.StoreDevice;

public interface StoreDeviceMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(StoreDevice record);

	int insertSelective(StoreDevice record);

	StoreDevice selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(StoreDevice record);

	int updateByPrimaryKey(StoreDevice record);

	List<StoreDevice> storeListByDevice(String deviceId);
	
	long countStoreDeviceList(StoreDeviceListModel listModel);

	List<StoreDeviceListVo> storeDeviceList(StoreDeviceListModel listModel);
}