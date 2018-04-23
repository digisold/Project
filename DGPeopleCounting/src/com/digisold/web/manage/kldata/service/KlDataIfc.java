package com.digisold.web.manage.kldata.service;

import java.util.Date;
import java.util.Map;

import com.digisold.web.manage.kldata.model.KlDataListModel;

public interface KlDataIfc {

	public Map<String, Object> klByDate(KlDataListModel listModel);
	
	public Map<String, Object> activityListBydate(Date startDate, Date endDate);

}
