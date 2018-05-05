/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: grid
 * File:      NameResolverImpl.java
 * Date:    18-5-5 下午4:51
 * Author: krun
 */

package com.krun.grid.config;

import com.krun.spring.extend.mapping.resolver.impl.AbstractMappingNameResolver;
import org.springframework.stereotype.Component;

/**
 * @author krun
 * @date 2018/05/05
 */
@Component
public class NameResolverImpl extends AbstractMappingNameResolver {

	@Override // 前缀
	protected String getPrefix () {
		return "";
	}
	@Override // 后缀
	protected String getSuffix () {
		return "Controller";
	}
}
