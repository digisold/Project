package com.digisold.web.manage.system.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.digisold.core.util.Constant;
import com.digisold.core.util.ToolUtil;
import com.digisold.web.manage.base.BaseController;
import com.digisold.web.manage.system.service.UserIfc;
import com.digisold.web.mybatis.entity.SystemUser;

@Controller
public class LoginController extends BaseController {

	private static Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private UserIfc userService;

	@RequestMapping(value = "/user_login", method = RequestMethod.POST)
	@ResponseBody
	public String loginController(String username, String password, String verify) {
		JSONObject result = new JSONObject();
		try {
			String yzmSessValue = (String) getSessionValue(Constant.VERIFY_SESSION);
			if (!yzmSessValue.equalsIgnoreCase(verify)) {
				result.put(MESSAGE, "验证码错误！");
				return toJSONString(result);
			}

			SystemUser user = userService.login(username.toLowerCase(), ToolUtil.MD5Encoder(password));
			if (user == null) { // 登录失败
				result.put(MESSAGE, "登录失败，用户名或密码错误！");
				return toJSONString(result);
			}

			Integer status = user.getStatus();
			Date useSdate = user.getUseStartDate();
			Date useEdate = user.getUseEndDate();
			if (status == 2) {
				result.put(MESSAGE, "用户已被锁定！");
				return toJSONString(result);
			}

			if (useSdate != null && useEdate != null) {
				Calendar cal = Calendar.getInstance();
				{
					cal.set(Calendar.HOUR_OF_DAY, 0);
					cal.set(Calendar.MINUTE, 0);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
				}
				long now = cal.getTimeInMillis();
				if (now > useEdate.getTime()) {
					result.put(MESSAGE, "用户使用期限已失效！");
					return toJSONString(result);
				}
			}
			setSessionAttribute(Constant.LOGIN_USER_SESSION, user);
			result.put(REDIRECT, "/index");
		} catch (Exception ex) {
			result.put(REDIRECT, Constant.OPT_FAIL_MSG);
			ex.printStackTrace();
			logger.error("用户登录出错[LoginController.loginController].", ex);
		}
		return toJSONString(result);
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	@RequestMapping("/logout")
	public String logoutPage(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}

}
