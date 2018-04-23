package com.digisold.web.mybatis.dao;

import java.util.List;

import com.digisold.web.manage.system.model.HolidayListModel;
import com.digisold.web.manage.system.vo.HolidayListVo;
import com.digisold.web.mybatis.entity.Holiday;

public interface HolidayMapper {
	int deleteByPrimaryKey(String id);

	int insert(Holiday record);

	int insertSelective(Holiday record);

	HolidayListVo selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Holiday record);

	int updateByPrimaryKey(Holiday record);

	long countHolidayList(HolidayListModel listModel);

	List<Holiday> holidayList(HolidayListModel listModel);
}