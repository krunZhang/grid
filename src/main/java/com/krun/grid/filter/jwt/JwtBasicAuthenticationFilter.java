/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: grid
 * File:      JwtBasicAuthenticationFilter.java
 * Date:    18-5-5 下午4:44
 * Author: krun
 */

package com.krun.grid.filter.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author krun
 * @date 2018/05/05
 */
public class JwtBasicAuthenticationFilter extends BasicAuthenticationFilter {

	public JwtBasicAuthenticationFilter (AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
	                                                                                                             IOException,
	                                                                                                             ServletException {
		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith(JwtUserAuthenticationFilter.HEAD)) {
			chain.doFilter(request, response);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);

	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null) {
			// parse the token.
			String user = Jwts.parser()
			                  .setSigningKey(JwtUserAuthenticationFilter.SECRET)
			                  .parseClaimsJws(token.replace(JwtUserAuthenticationFilter.HEAD, ""))
			                  .getBody()
			                  .getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}

}
