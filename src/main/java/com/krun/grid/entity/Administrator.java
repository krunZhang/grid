/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: grid
 * File:      Administrator.java
 * Date:    18-5-5 下午4:27
 * Author: krun
 */

package com.krun.grid.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author krun
 * @date 20180/05/05
 */
@Data
@NoArgsConstructor
@Entity
public class Administrator extends UserProperties implements Serializable {

}
