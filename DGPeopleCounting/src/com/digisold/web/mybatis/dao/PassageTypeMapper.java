package com.digisold.web.mybatis.dao;

import java.util.List;

import com.digisold.web.manage.system.model.PassageTypeListModel;
import com.digisold.web.manage.system.vo.PassageTypeListVo;
import com.digisold.web.mybatis.entity.PassageType;

public interface PassageTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PassageType record);

    int insertSelective(PassageType record);

    PassageType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PassageType record);

    int updateByPrimaryKey(PassageType record);
    
    long checkNameIsExists(PassageType passageType);

   	long countPassageTypeList(PassageTypeListModel listModel);

   	List<PassageTypeListVo> passageTypeList(PassageTypeListModel listModel);
   	
   	List<PassageType> ptypeListByStore(String storeId);
}