package com.digisold.web.manage.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.web.manage.system.model.PassageListModel;
import com.digisold.web.manage.system.service.PassageIfc;
import com.digisold.web.manage.system.vo.PassageListVo;
import com.digisold.web.mybatis.dao.ChinaMapper;
import com.digisold.web.mybatis.dao.PassageMapper;
import com.digisold.web.mybatis.entity.Passage;

@Service("passageService")
public class PassageImpl implements PassageIfc {

	@Autowired
	PassageMapper passageMapper;

	@Autowired
	ChinaMapper chinaMapper;

	public void addPassage(Passage passage) {
		passageMapper.insertSelective(passage);
	}

	public void editPassage(Passage passage) {
		passageMapper.updateByPrimaryKeySelective(passage);
	}

	public void deletePassage(String id) {
		passageMapper.deleteByPrimaryKey(id);
	}

	public Passage getPassageById(String id) {
		return passageMapper.selectByPrimaryKey(id);
	}

	public long checkNameIsExists(Passage passage) {
		return passageMapper.checkNameIsExists(passage);
	}

	public long countPassageList(PassageListModel listModel) {
		return passageMapper.countPassageList(listModel);
	}

	public List<PassageListVo> passageList(PassageListModel listModel) {
		return passageMapper.passageList(listModel);
	}

	public List<Map<?, ?>> passageListByStore(String storeId) {
		return passageMapper.passageListByStore(storeId);
	}
}
