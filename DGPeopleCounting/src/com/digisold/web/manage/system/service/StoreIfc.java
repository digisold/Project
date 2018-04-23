package com.digisold.web.manage.system.service;

import java.util.List;

import com.digisold.web.manage.system.model.StoreListModel;
import com.digisold.web.mybatis.entity.China;
import com.digisold.web.mybatis.entity.Store;

public interface StoreIfc {
	public void addStore(Store store);

	public void editStore(Store store);

	public void deleteStore(String id);

	public Store getStoreById(String id);

	public long countStoreList(StoreListModel listModel);

	public List<?> storeList(StoreListModel listModel);
	
	public List<China> chinaList();
	
	public List<Store> storeListByUser(String userId);

}
