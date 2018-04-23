package com.digisold.web.manage.business_data.service;

import java.util.List;

import com.digisold.web.manage.business_data.model.ActivityListModel;
import com.digisold.web.manage.business_data.vo.ActivityListVo;
import com.digisold.web.mybatis.entity.Activity;

public interface ActivityIfc {

	public void addActivity(Activity activity);

	public void editActivity(Activity activity);

	public void deleteActivity(String activityId);

	public Activity getActivityById(String activityId);

	public long countActivityList(ActivityListModel listModel);

	public List<ActivityListVo> activityList(ActivityListModel listModel);
}
