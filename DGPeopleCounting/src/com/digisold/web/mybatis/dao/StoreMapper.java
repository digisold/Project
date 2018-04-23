package com.digisold.web.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.digisold.web.manage.system.model.StoreListModel;
import com.digisold.web.manage.system.vo.StoreListVo;
import com.digisold.web.mybatis.entity.Store;

public interface StoreMapper {
	int deleteByPrimaryKey(String id);

	int insert(Store record);

	int insertSelective(Store record);

	Store selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Store record);

	int updateByPrimaryKey(Store record);

	long countStoreList(StoreListModel listModel);

	List<StoreListVo> storeList(StoreListModel listModel);

	List<StoreListVo> childStoreList(String storeId);

	Integer getMaxTableIdx();

	int createDataTable(Integer tableSequence);

	List<Store> storeListByUser(@Param("userId") String userId);
}