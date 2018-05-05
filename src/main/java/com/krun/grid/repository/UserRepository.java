/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: grid
 * File:      UserRepository.java
 * Date:    18-5-5 下午4:30
 * Author: krun
 */

package com.krun.grid.repository;

import com.krun.grid.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author krun
 * @date 2018/05/05
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
