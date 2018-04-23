package com.digisold.web.manage.business_data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digisold.web.manage.business_data.model.ActivityListModel;
import com.digisold.web.manage.business_data.service.ActivityIfc;
import com.digisold.web.manage.business_data.vo.ActivityListVo;
import com.digisold.web.mybatis.dao.ActivityMapper;
import com.digisold.web.mybatis.entity.Activity;

@Service("activityServicev vv")
public class ActivityImpl implements ActivityIfc {

	@Autowired
	ActivityMapper activityMapper;

	public void addActivity(Activity activity) {
		activityMapper.insertSelective(activity);
	}

	public void editActivity(Activity activity) {
		activityMapper.updateByPrimaryKeySelective(activity);
	}

	public void deleteActivity(String activityId) {
		activityMapper.deleteByPrimaryKey(activityId);
	}

	public Activity getActivityById(String activityId) {
		return activityMapper.selectByPrimaryKey(activityId);
	}

	public long countActivityList(ActivityListModel listModel) {
		return activityMapper.countActivityList(listModel);
	}

	public List<ActivityListVo> activityList(ActivityListModel listModel) {
		return activityMapper.activityList(listModel);
	}
}
