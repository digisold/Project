package com.digisold.web.mybatis.dao;

import com.digisold.web.mybatis.entity.CityWeather;

public interface CityWeatherMapper {
    int deleteByPrimaryKey(String id);

    int insert(CityWeather record);

    int insertSelective(CityWeather record);

    CityWeather selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CityWeather record);

    int updateByPrimaryKey(CityWeather record);
}