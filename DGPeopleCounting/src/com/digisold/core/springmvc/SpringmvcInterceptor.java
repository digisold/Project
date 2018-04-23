package com.digisold.core.springmvc;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;
import com.digisold.core.util.Constant;
import com.digisold.core.util.FastJsonUtil;
import com.digisold.web.mybatis.entity.SystemUser;

public class SpringmvcInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURL().toString();
		/********* 拦截未登入 *********/
		HttpSession session = request.getSession(false);
		session = session == null ? request.getSession() : session;
		if (!url.endsWith("/login") && !url.endsWith("/user_login")) {
			SystemUser user = (SystemUser) session.getAttribute(Constant.LOGIN_USER_SESSION);
			if (user == null) {
				String reqType = request.getHeader("X-Requested-With");// 识别ajax的响应头
				String loginUrl = request.getContextPath() + "/login";
				if (reqType != null && "XMLHttpRequest".equalsIgnoreCase(reqType)) {
					JSONObject result = new JSONObject();
					{
						result.put("logout", loginUrl);
					}
					response.getWriter().print(FastJsonUtil.toJSONString(result));
				} else {
					PrintWriter out = response.getWriter();
					out.println("<html><head><script>window.parent.location.href = '" + loginUrl
							+ "';</script></head></html>");
					out.close();
					// response.sendRedirect(loginUrl);
				}
				return false;
			}
		}

		System.out.println(System.getProperty("line.separator") + "* 请求路径 *：" + url);
		/************************************ 调试【获取参数，值】 ***********************************/
		if (url.indexOf("127.0.0.1") != -1 || url.indexOf("localhost") != -1) {
			if (handler instanceof HandlerMethod) {
				HandlerMethod method = (HandlerMethod) handler;
				String clsName = method.getBean().getClass().getName();
				String clsMethod = method.getMethod().getName();
				System.out.println(clsName + " : " + clsMethod);
				Enumeration<String> enu = request.getParameterNames();
				int k = 0;
				while (enu.hasMoreElements()) {
					k += 1;
					if (k == 1) {
						System.out.println("**********************请求参数名及值***********************");
					}
					String name = (String) enu.nextElement();
					System.out.println(name + ": " + request.getParameter(name));
				}
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj,
			ModelAndView modelandview) throws Exception {
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj,
			Exception exception) throws Exception {
	}

}
