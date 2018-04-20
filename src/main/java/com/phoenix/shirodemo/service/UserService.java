package com.phoenix.shirodemo.service;

import com.phoenix.shirodemo.model.User;

/**
 * User: sheng
 * Date: 2018-04-18 14:30
 * Description:
 */
public interface UserService {

    User findByUsername(String username);

}
