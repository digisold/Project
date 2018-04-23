package com.digisold.web.manage.system.service;

import java.util.List;

import com.digisold.web.manage.system.model.StoreDeviceListModel;
import com.digisold.web.manage.system.vo.StoreDeviceListVo;
import com.digisold.web.mybatis.entity.StoreDevicePassage;

public interface StoreDeviceIfc {

	public int deleteStoreDevice(Integer id);

	public long countStoreDeviceList(StoreDeviceListModel listModel);

	public List<StoreDeviceListVo> storeDeviceList(StoreDeviceListModel listModel);

	public void saveStoreDevicePassage(StoreDevicePassage passage);
}
