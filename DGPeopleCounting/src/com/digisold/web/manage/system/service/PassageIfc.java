package com.digisold.web.manage.system.service;

import java.util.List;
import java.util.Map;

import com.digisold.web.manage.system.model.PassageListModel;
import com.digisold.web.manage.system.vo.PassageListVo;
import com.digisold.web.mybatis.entity.Passage;

public interface PassageIfc {
	public void addPassage(Passage passage);

	public void editPassage(Passage passage);

	public void deletePassage(String id);

	public Passage getPassageById(String id);

	public long checkNameIsExists(Passage passage);

	public long countPassageList(PassageListModel listModel);

	public List<PassageListVo> passageList(PassageListModel listModel);

	public List<Map<?, ?>> passageListByStore(String storeId);
	
	public List<Passage> passageListByType(Integer passageType);
}
