/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: grid
 * File:      JwtUserAuthenticationFilter.java
 * Date:    18-5-5 下午4:43
 * Author: krun
 */

package com.krun.grid.filter.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krun.grid.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author krun
 * @date 2018/05/05
 */
public class JwtUserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public static final String SECRET = "grid_secret";
	public static final String HEAD = "GRID ";

	private AuthenticationManager authenticationManager;

	public JwtUserAuthenticationFilter (AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	// 接收并解析用户凭证
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res) throws AuthenticationException {
		try {
			User user = new ObjectMapper()
					.readValue(req.getInputStream(), User.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							user.getUsername(),
							user.getPassword(),
							new ArrayList<>())
			);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// 用户成功登录后，这个方法会被调用，在这个方法里生成token
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
	                                        HttpServletResponse res,
	                                        FilterChain chain,
	                                        Authentication auth) throws IOException, ServletException {

		String token = Jwts.builder()
		                   .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
		                   .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
		                   .signWith(SignatureAlgorithm.HS512, SECRET)
		                   .compact();
		res.addHeader("Authorization", HEAD + token);
	}

}
