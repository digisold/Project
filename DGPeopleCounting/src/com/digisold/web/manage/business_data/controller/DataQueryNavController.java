package com.digisold.web.manage.business_data.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digisold.core.util.ToolUtil;
import com.digisold.web.manage.base.BaseController;

@Controller("dataQueryNavController")
@RequestMapping("/data-query")
public class DataQueryNavController extends BaseController {

	@RequestMapping("/date")
	public String datePage() {
		setReqAttribute("curDate", ToolUtil.dateFormat("yyyy-MM-dd", new Date()));
		return "data-query/date";
	}

	@RequestMapping("/week")
	public String weekPage() {
		Date date = new Date();
		setReqAttribute("endDate", ToolUtil.dateFormat("yyyy-MM-dd", date));
		Calendar cal = Calendar.getInstance();
		{
			cal.add(Calendar.DATE, -6);
		}
		setReqAttribute("startDate", ToolUtil.dateFormat("yyyy-MM-dd", cal.getTime()));
		return "data-query/week";
	}

	@RequestMapping("/month")
	public String monthPage() {
		Date date = new Date();
		setReqAttribute("curMonth", ToolUtil.dateFormat("yyyy-MM", date));
		return "data-query/month";
	}

	@RequestMapping("/year")
	public String yearPage() {
		Date date = new Date();
		setReqAttribute("curYear", ToolUtil.dateFormat("yyyy", date));
		return "data-query/year";
	}

	@RequestMapping("/custom")
	public String customPage() {
		Date date = new Date();
		setReqAttribute("endDate", ToolUtil.dateFormat("yyyy-MM-dd", date));
		Calendar cal = Calendar.getInstance();
		{
			cal.add(Calendar.DATE, -14);
		}
		setReqAttribute("startDate", ToolUtil.dateFormat("yyyy-MM-dd", cal.getTime()));
		return "data-query/custom";
	}

}
