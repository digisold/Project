package com.digisold.web.mybatis.dao;

import java.util.List;
import java.util.Map;

import com.digisold.web.manage.system.model.PassageListModel;
import com.digisold.web.manage.system.vo.PassageListVo;
import com.digisold.web.mybatis.entity.Passage;

public interface PassageMapper {
	int deleteByPrimaryKey(String id);

	int insert(Passage record);

	int insertSelective(Passage record);

	Passage selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Passage record);

	int updateByPrimaryKey(Passage record);

	long checkNameIsExists(Passage passage);

	long countPassageList(PassageListModel listModel);

	List<PassageListVo> passageList(PassageListModel listModel);

	List<Map<?, ?>> passageListByStore(String storeId);
	
	List<Passage> passageListByType(Integer passageType);
}