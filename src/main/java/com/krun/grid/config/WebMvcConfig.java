/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: grid
 * File:      WebMvcConfig.java
 * Date:    18-5-5 下午4:51
 * Author: krun
 */

package com.krun.grid.config;

import com.krun.spring.extend.mapping.handler.DomainMappingHandler;
import com.krun.spring.extend.mapping.resolver.MappingNameResolver;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @author krun
 * @date 2018/05/05
 */
@Configuration
public class WebMvcConfig implements WebMvcRegistrations {

	private MappingNameResolver nameResolver;

	public WebMvcConfig (MappingNameResolver nameResolver) {
		this.nameResolver = nameResolver;
	}

	@Override
	public RequestMappingHandlerMapping getRequestMappingHandlerMapping () {
		return new DomainMappingHandler(nameResolver);
	}
}
