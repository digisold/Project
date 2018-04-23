package com.digisold.web.mybatis.dao;

import java.util.List;

import com.digisold.web.manage.system.model.PassageDeviceListModel;
import com.digisold.web.manage.system.vo.PassageDeviceListVo;
import com.digisold.web.mybatis.entity.StoreDevicePassage;

public interface StoreDevicePassageMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(StoreDevicePassage record);

	int insertSelective(StoreDevicePassage record);

	StoreDevicePassage selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(StoreDevicePassage record);

	int updateByPrimaryKey(StoreDevicePassage record);

	StoreDevicePassage selectByStoreDeviceId(Integer storeDeviceId);

	long countPassageDeviceList(PassageDeviceListModel listModel);

	List<PassageDeviceListVo> passageDeviceList(PassageDeviceListModel listModel);
}