/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: grid
 * File:      AdministratorRepository.java
 * Date:    18-5-5 下午4:31
 * Author: krun
 */

package com.krun.grid.repository;

import com.krun.grid.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author krun
 * @date 2018/05/05
 */
@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, String> {}
