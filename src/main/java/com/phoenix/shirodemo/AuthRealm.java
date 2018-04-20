package com.phoenix.shirodemo;

import com.phoenix.shirodemo.model.Permission;
import com.phoenix.shirodemo.model.Role;
import com.phoenix.shirodemo.model.User;
import com.phoenix.shirodemo.service.UserService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * User: sheng
 * Date: 2018-04-19 10:34
 * Description:
 */
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //把user的所有权限列表放进AuthorizationInfo
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissions = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();

        Set<Role> roles = user.getRoles();
        if(CollectionUtils.isNotEmpty(roles)) {
            for(Role role : roles) {
                roleNameList.add(role.getRname());
                Set<Permission> permissionSet = role.getPermissions();
                if(CollectionUtils.isNotEmpty(permissionSet)) {
                    for(Permission permission : permissionSet) {
                        permissions.add(permission.getPname());
                    }
                }
            }
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        authorizationInfo.addRoles(roleNameList);
        return authorizationInfo;
    }

    /**
     * 用户认证登陆
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        User user = userService.findByUsername(username);
        //把user信息放进AuthenticationInfo
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }
}
