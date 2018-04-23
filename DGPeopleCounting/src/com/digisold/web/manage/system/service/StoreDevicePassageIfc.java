package com.digisold.web.manage.system.service;

import java.util.List;

import com.digisold.web.manage.system.model.PassageDeviceListModel;
import com.digisold.web.manage.system.vo.PassageDeviceListVo;
import com.digisold.web.mybatis.entity.StoreDevicePassage;

public interface StoreDevicePassageIfc {

	public StoreDevicePassage detailByStoreDeviceId(Integer storeDeviceId);
	
	public int deletePassageDevice(Integer id);
	
	public long countPassageDeviceList(PassageDeviceListModel listModel);

	public List<PassageDeviceListVo> passageDeviceList(PassageDeviceListModel listModel);

}
