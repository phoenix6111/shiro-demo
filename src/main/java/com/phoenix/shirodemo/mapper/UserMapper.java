package com.phoenix.shirodemo.mapper;

import com.phoenix.shirodemo.model.User;

import org.apache.ibatis.annotations.Param;

/**
 * User: sheng
 * Date: 2018-04-18 14:29
 * Description:
 */
public interface UserMapper {

    User findByUsername(@Param("username") String username);

}
