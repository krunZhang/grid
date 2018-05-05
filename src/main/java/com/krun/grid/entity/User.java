/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: grid
 * File:      User.java
 * Date:    18-5-5 下午4:28
 * Author: krun
 */

package com.krun.grid.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author krun
 * @date 2018/05/05
 */
@Data
@NoArgsConstructor
@Entity
public class User extends UserProperties implements Serializable {

}
