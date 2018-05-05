package com.udemy.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.udemy.repository.LogRepository;

@Component("requestTimeInterceptor")
public class RequestTimeInterceptor extends HandlerInterceptorAdapter {
	
	private static final Log LOG = LogFactory.getLog(RequestTimeInterceptor.class);
	
	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
	
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
		String url = request.getRequestURL().toString();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 String username ="";
		 String details = "";
		 
		 if(auth != null) {
			 details = auth.getDetails().toString();
			 if(auth.isAuthenticated())username = auth.getName();
		 }
		
		logRepository.save(new com.udemy.entity.Log(new Date(),details,
				username, url));
		
		StringBuilder strBuilder = new StringBuilder("--REQUEST URL: '")
										.append(url).append("'")
										.append(" --TOTAL TIME: '")
										.append((System.currentTimeMillis() - startTime)).append("'ms");
		LOG.info(strBuilder.toString());
	}

}
