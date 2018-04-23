package com.digisold.web.manage.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.web.manage.system.model.StoreListModel;
import com.digisold.web.manage.system.service.StoreIfc;
import com.digisold.web.manage.system.vo.StoreListVo;
import com.digisold.web.mybatis.dao.ChinaMapper;
import com.digisold.web.mybatis.dao.StoreMapper;
import com.digisold.web.mybatis.entity.China;
import com.digisold.web.mybatis.entity.Store;

@Service("storeService")
public class StoreImpl implements StoreIfc {

	@Autowired
	StoreMapper storeMapper;

	@Autowired
	ChinaMapper chinaMapper;

	public void addStore(Store store) {
		Integer maxTableIdx = storeMapper.getMaxTableIdx() + 1;
		storeMapper.createDataTable(maxTableIdx);
		store.setDataTable(maxTableIdx);
		storeMapper.insertSelective(store);
	}

	public void editStore(Store store) {
		storeMapper.updateByPrimaryKeySelective(store);
	}

	public void deleteStore(String id) {
		storeMapper.deleteByPrimaryKey(id);
	}

	public Store getStoreById(String id) {
		return storeMapper.selectByPrimaryKey(id);
	}

	public long countStoreList(StoreListModel listModel) {
		return storeMapper.countStoreList(listModel);
	}

	public List<?> storeList(StoreListModel listModel) {
		List<Map<String, Object>> list = new ArrayList<>();
		List<StoreListVo> storeList = storeMapper.storeList(listModel);
		for (StoreListVo store : storeList) {
			Map<String, Object> map = new HashMap<>();
			{
				map.put("store", store);
				map.put("childStoreList", storeMapper.childStoreList(store.getId()));
			}
			list.add(map);
		}
		return list;
	}

	public List<China> chinaList() {
		return chinaMapper.chinaList();
	}

	public List<Store> storeListByUser(String userId) {
		return storeMapper.storeListByUser(userId);
	}
}
