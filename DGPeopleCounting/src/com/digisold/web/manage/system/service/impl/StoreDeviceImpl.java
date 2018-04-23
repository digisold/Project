package com.digisold.web.manage.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.web.manage.system.model.StoreDeviceListModel;
import com.digisold.web.manage.system.service.StoreDeviceIfc;
import com.digisold.web.manage.system.vo.StoreDeviceListVo;
import com.digisold.web.mybatis.dao.StoreDeviceMapper;
import com.digisold.web.mybatis.dao.StoreDevicePassageMapper;
import com.digisold.web.mybatis.entity.StoreDevicePassage;

@Service("storeDeviceService")
public class StoreDeviceImpl implements StoreDeviceIfc {

	@Autowired
	StoreDeviceMapper storeDeviceMapper;

	@Autowired
	StoreDevicePassageMapper sdpMapper;

	public int deleteStoreDevice(Integer id) {
		return storeDeviceMapper.deleteByPrimaryKey(id);
	}

	public long countStoreDeviceList(StoreDeviceListModel listModel) {
		return storeDeviceMapper.countStoreDeviceList(listModel);
	}

	public List<StoreDeviceListVo> storeDeviceList(StoreDeviceListModel listModel) {
		return storeDeviceMapper.storeDeviceList(listModel);
	}

	public void saveStoreDevicePassage(StoreDevicePassage passage) {
		Integer id = passage.getId();
		if (id != null && id > 0) {
			sdpMapper.updateByPrimaryKey(passage);
		} else {
			sdpMapper.insertSelective(passage);
		}
	}
}
