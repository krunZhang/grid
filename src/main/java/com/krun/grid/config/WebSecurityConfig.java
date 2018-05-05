/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: grid
 * File:      WebSecurityConfig.java
 * Date:    18-5-5 下午4:45
 * Author: krun
 */

package com.krun.grid.config;

import com.krun.grid.filter.jwt.JwtBasicAuthenticationFilter;
import com.krun.grid.filter.jwt.JwtUserAuthenticationFilter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author krun
 * @date 2018/05/05
 */
@Configuration
@Order (SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	private PasswordEncoder encoder;

	public WebSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder encoder) {
		this.userDetailsService = userDetailsService;
		this.encoder = encoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		    .authorizeRequests()
//		    .antMatchers(HttpMethod.GET, "/user/hello").permitAll()
		    .antMatchers(HttpMethod.POST, "/user/signup").permitAll()
		    .anyRequest().authenticated()
		    .and()
		    .addFilter(new JwtUserAuthenticationFilter(authenticationManager()))
		    .addFilter(new JwtBasicAuthenticationFilter(authenticationManager()));
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
	}

}
