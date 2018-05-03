package com.udemy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.udemy.component.RequestTimeInterceptor;

/**
 * clase de configuracion de spring mvc 
 * se extiende de la clase web
 * @author carlosmarin
 *
 */
@Configuration
public class WebMvcConfiguration  extends WebMvcConfigurerAdapter{
	
	//todo se carga cuando se levanta la aplicacion
	
	@Autowired
	@Qualifier("requestTimeInterceptor")
	private RequestTimeInterceptor requestTimeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestTimeInterceptor);
	}
	
	
}
