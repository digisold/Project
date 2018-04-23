package com.digisold.web.mybatis.dao;

import com.digisold.web.mybatis.entity.PassageType;

public interface PassageTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PassageType record);

    int insertSelective(PassageType record);

    PassageType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PassageType record);

    int updateByPrimaryKey(PassageType record);
}