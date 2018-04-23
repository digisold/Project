package com.digisold.web.mybatis.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.digisold.web.manage.business_data.model.ActivityListModel;
import com.digisold.web.manage.business_data.vo.ActivityListVo;
import com.digisold.web.mybatis.entity.Activity;

public interface ActivityMapper {
	int deleteByPrimaryKey(String id);

	int insert(Activity record);

	int insertSelective(Activity record);

	Activity selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Activity record);

	int updateByPrimaryKey(Activity record);

	long countActivityList(ActivityListModel listModel);

	List<ActivityListVo> activityList(ActivityListModel listModel);

	Map<String, Object> activityListByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}