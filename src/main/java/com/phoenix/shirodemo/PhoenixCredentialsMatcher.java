package com.phoenix.shirodemo;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
 * User: sheng
 * Date: 2018-04-19 11:25
 * Description: 用户密码校验规则
 */
public class PhoenixCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userPassword = new String(usernamePasswordToken.getPassword());

        String dbPassword = (String) info.getCredentials();

        return StringUtils.equals(userPassword,dbPassword);
    }
}
