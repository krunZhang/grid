/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: grid
 * File:      UserController.java
 * Date:    18-5-5 下午4:47
 * Author: krun
 */

package com.krun.grid.controller;

import com.krun.grid.entity.User;
import com.krun.grid.repository.UserRepository;
import com.krun.spring.extend.mapping.RestDomainMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author krun
 * @date 2018/05/05
 */

@RestDomainMapping
public class UserController {

	private PasswordEncoder encoder;
	private UserRepository  repository;

	public UserController (PasswordEncoder encoder, UserRepository repository) {
		this.encoder = encoder;
		this.repository = repository;
	}

	public String hello(){
		return "hello";
	}

	@PostMapping ("/signup")
	public void signUp(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		repository.save(user);
	}
}
