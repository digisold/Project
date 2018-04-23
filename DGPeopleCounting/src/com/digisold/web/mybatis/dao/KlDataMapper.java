package com.digisold.web.mybatis.dao;

import java.util.List;

import com.digisold.web.manage.kldata.model.KlDataListModel;
import com.digisold.web.manage.kldata.vo.KlDataListVo;

public interface KlDataMapper {
	
	List<KlDataListVo> klByDate(KlDataListModel listModel);
	
}