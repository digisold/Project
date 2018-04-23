package com.digisold.web.manage.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.web.manage.system.model.PassageDeviceListModel;
import com.digisold.web.manage.system.service.StoreDevicePassageIfc;
import com.digisold.web.manage.system.vo.PassageDeviceListVo;
import com.digisold.web.mybatis.dao.StoreDevicePassageMapper;
import com.digisold.web.mybatis.entity.StoreDevicePassage;

@Service("storeDevicePassageService")
public class StoreDevicePassageImpl implements StoreDevicePassageIfc {

	@Autowired
	StoreDevicePassageMapper sdpMapper;

	public StoreDevicePassage detailByStoreDeviceId(Integer storeDeviceId) {
		return sdpMapper.selectByStoreDeviceId(storeDeviceId);
	}

	public int deletePassageDevice(Integer id) {
		return sdpMapper.deleteByPrimaryKey(id);
	}

	public long countPassageDeviceList(PassageDeviceListModel listModel) {
		return sdpMapper.countPassageDeviceList(listModel);
	}

	public List<PassageDeviceListVo> passageDeviceList(PassageDeviceListModel listModel) {
		return sdpMapper.passageDeviceList(listModel);
	}
}
