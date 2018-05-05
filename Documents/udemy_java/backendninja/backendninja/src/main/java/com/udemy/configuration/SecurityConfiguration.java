package com.udemy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author carlosmarin
 *
 */
//esto le dice es que es una clase de configuracion
@Configuration
@EnableWebSecurity
//segirizar metodos, nos permite escribir anotaciones para controlar la 
//seguridad de la aplicacion
@EnableGlobalMethodSecurity(prePostEnabled=true)
// la clase de extencion nos ayuda como guia para la configuracion
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("userService")
	private UserDetailsService userService;
	
	//nos sirve para enviar el userDetail service que generamos
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//request ques estan autorizada sin hacer el login de la aplicacion
		http.authorizeRequests()
		.antMatchers("/css/*", "/imgs/*").permitAll()
		//el resto de request por autenticacion
		.anyRequest().authenticated()
		.and()
		//le indico donde se encuentra el login de la aplicacion
		.formLogin().loginPage("/login")
		//indico donde proceso la auntentificacion
		.loginProcessingUrl("/logincheck")
		//indico cuales son los parametros enviado para la autotentificacin
		.usernameParameter("username").passwordParameter("password")
		//url por defecto luego que el usuario se haya auntentificado
		.defaultSuccessUrl("/loginsuccess").permitAll()
		.and()
		//le digo cual es la url para desloguearse mas donde va redirigir despues de eso
		.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout").permitAll();
		//super.configure(http);
	}

}
