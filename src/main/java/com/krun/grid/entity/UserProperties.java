/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: grid
 * File:      UserProperties.java
 * Date:    18-5-5 下午4:35
 * Author: krun
 */

package com.krun.grid.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author krun
 * @date 2018/05/05
 */
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProperties {

	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	protected String id;

	protected String username;
	protected String password;

}
