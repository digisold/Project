package com.digisold.web.manage.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.digisold.core.util.Constant;
import com.digisold.core.util.FastJsonUtil;
import com.digisold.web.mybatis.entity.SystemUser;

public class BaseController extends FastJsonUtil {
	@Autowired
	private HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	private String creator;

	private String updater;

	public final String REDIRECT = "redirect";

	public final String MESSAGE = "message";

	public final String DATA = "data";
	
	public final String STATUS = "status";
	
	public final String RECORDS = "records";
	
	public final String PAGESIZE = "pageSize";

	public void setReqAttribute(String name, Object value) {
		this.request.setAttribute(name, value);
	}

	public Object getReqValue(String name) {
		return this.request.getAttribute(name);
	}

	public String setSessionAttribute(String name, Object value) {
		HttpSession session = this.request.getSession();
		session.setAttribute(name, value);
		return session.getId();
	}

	public Object getSessionValue(String name) {
		HttpSession session = this.request.getSession(false);
		session = session == null ? this.request.getSession() : session;
		return session == null ? null : this.request.getSession().getAttribute(name);
	}
	
	public void removeSession(String name) {
		HttpSession session = this.request.getSession(false);
		session = session == null ? this.request.getSession() : session;
		session.removeAttribute(name);
	}

	public String getCreator() {
		HttpSession session = this.request.getSession(false);
		session = session == null ? this.request.getSession() : session;
		SystemUser user = (SystemUser) session.getAttribute(Constant.LOGIN_USER_SESSION);
		creator = user == null ? "0" : user.getId();
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdater() {
		HttpSession session = this.request.getSession(false);
		session = session == null ? this.request.getSession() : session;
		SystemUser user = (SystemUser) session.getAttribute(Constant.LOGIN_USER_SESSION);
		updater = user == null ? "0" : user.getId();
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

}
