package com.digisold.web.manage.system.service;

import java.util.List;

import com.digisold.web.manage.system.model.HolidayListModel;
import com.digisold.web.manage.system.vo.HolidayListVo;
import com.digisold.web.mybatis.entity.Holiday;

public interface HolidayIfc {
	public void addHoliday(Holiday holiday);

	public void editHoliday(Holiday holiday);

	public void deleteHoliday(String id);

	public HolidayListVo getHolidayById(String id);

	public long countHolidayList(HolidayListModel listModel);

	public List<Holiday> holidayList(HolidayListModel listModel);
}
