package com.digisold.web.manage.system.service;

import java.util.List;
import java.util.Map;

import com.digisold.web.manage.system.model.PassageTypeListModel;
import com.digisold.web.manage.system.vo.PassageTypeListVo;
import com.digisold.web.mybatis.entity.PassageType;

public interface PassageTypeIfc {
	public void addPassageType(PassageType passageType);

	public void editPassageType(PassageType passageType);

	public void deletePassageType(Integer id);

	public PassageType getPassageTypeById(Integer id);

	public long checkNameIsExists(PassageType passageType);

	public long countPassageTypeList(PassageTypeListModel listModel);

	public List<PassageTypeListVo> passageTypeList(PassageTypeListModel listModel);
	
	public List<PassageType> ptypeListByStore(String storeId);
}
