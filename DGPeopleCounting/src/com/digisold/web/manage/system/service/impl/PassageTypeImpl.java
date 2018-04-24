package com.digisold.web.manage.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.web.manage.system.model.PassageTypeListModel;
import com.digisold.web.manage.system.service.PassageTypeIfc;
import com.digisold.web.manage.system.vo.PassageTypeListVo;
import com.digisold.web.mybatis.dao.ChinaMapper;
import com.digisold.web.mybatis.dao.PassageTypeMapper;
import com.digisold.web.mybatis.entity.PassageType;

@Service("passageTypeService")
public class PassageTypeImpl implements PassageTypeIfc {

	@Autowired
	PassageTypeMapper passageTypeMapper;

	@Autowired
	ChinaMapper chinaMapper;

	public void addPassageType(PassageType passageType) {
		passageTypeMapper.insertSelective(passageType);
	}

	public void editPassageType(PassageType passageType) {
		passageTypeMapper.updateByPrimaryKeySelective(passageType);
	}

	public void deletePassageType(Integer id) {
		passageTypeMapper.deleteByPrimaryKey(id);
	}

	public PassageType getPassageTypeById(Integer id) {
		return passageTypeMapper.selectByPrimaryKey(id);
	}

	public long checkNameIsExists(PassageType passageType) {
		return passageTypeMapper.checkNameIsExists(passageType);
	}

	public long countPassageTypeList(PassageTypeListModel listModel) {
		return passageTypeMapper.countPassageTypeList(listModel);
	}

	public List<PassageTypeListVo> passageTypeList(PassageTypeListModel listModel) {
		return passageTypeMapper.passageTypeList(listModel);
	}

	public List<PassageType> ptypeListByStore(String storeId) {
		return passageTypeMapper.ptypeListByStore(storeId);
	}
}
