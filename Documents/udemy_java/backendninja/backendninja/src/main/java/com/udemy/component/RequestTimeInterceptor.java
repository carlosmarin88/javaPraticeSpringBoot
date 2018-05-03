package com.udemy.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {
	
	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);
	
	
	//PRIMERO
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// ante que se ejecute realiza esto
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	//SEGUNDO
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (long) request.getAttribute("startTime");
		StringBuilder strBuilder = new StringBuilder("--REQUEST URL: '")
										.append(request.getRequestURL().toString()).append("'")
										.append(" --TOTAL TIME: '")
										.append((System.currentTimeMillis() - startTime)).append("'ms");
		LOG.info(strBuilder.toString());
	}

}
