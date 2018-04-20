package com.phoenix.shirodemo.service;

import com.phoenix.shirodemo.mapper.UserMapper;
import com.phoenix.shirodemo.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: sheng
 * Date: 2018-04-18 14:31
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
