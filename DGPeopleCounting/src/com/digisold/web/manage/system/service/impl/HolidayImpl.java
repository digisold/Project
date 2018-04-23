package com.digisold.web.manage.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.web.manage.system.model.HolidayListModel;
import com.digisold.web.manage.system.service.HolidayIfc;
import com.digisold.web.manage.system.vo.HolidayListVo;
import com.digisold.web.mybatis.dao.HolidayMapper;
import com.digisold.web.mybatis.entity.Holiday;

@Service("holidayService")
public class HolidayImpl implements HolidayIfc {

	@Autowired
	HolidayMapper holidayMapper;

	public void addHoliday(Holiday holiday) {
		holidayMapper.insertSelective(holiday);
	}

	public void editHoliday(Holiday holiday) {
		holidayMapper.updateByPrimaryKeySelective(holiday);
	}

	public void deleteHoliday(String id) {
		holidayMapper.deleteByPrimaryKey(id);
	}

	public HolidayListVo getHolidayById(String id) {
		return holidayMapper.selectByPrimaryKey(id);
	}

	public long countHolidayList(HolidayListModel listModel) {
		return holidayMapper.countHolidayList(listModel);
	}

	public List<Holiday> holidayList(HolidayListModel listModel) {
		return holidayMapper.holidayList(listModel);
	}
}
