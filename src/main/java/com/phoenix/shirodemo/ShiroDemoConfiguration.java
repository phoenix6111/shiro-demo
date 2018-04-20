package com.phoenix.shirodemo;

import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * User: sheng
 * Date: 2018-04-19 11:35
 * Description:
 */
@Configuration
public class ShiroDemoConfiguration {

    /**
     * 用户认证安全配置
     * @param securityManager
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);

        //设置登陆url
        filterFactoryBean.setLoginUrl("/login");
        //设置登陆成功后跳转的url
        filterFactoryBean.setSuccessUrl("/index");
        //设置用户未认证时跳转的url
        filterFactoryBean.setUnauthorizedUrl("/unauthorized");

        //设置哪些url需要认证，哪些url不需要
        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        //设置访问/index url时需要认证，参照org.apache.shiro.web.filter.mgt.DefaultFilter
        filterChainDefinitionMap.put("/index","authc");
        //设置/login可以匿名访问
        filterChainDefinitionMap.put("/login","anon");
        //设置/admin只能角色名为admin的用户可以访问
        filterChainDefinitionMap.put("/admin","roles[admin]");
        //设置/delete只能拥有delete权限的用户访问
        filterChainDefinitionMap.put("/delete","perms[delete]");
        //设置/druid/*请求 匿名用户也可访问
        filterChainDefinitionMap.put("/druid/**","anon");
        //设置其它路由检测用户是否登陆
        filterChainDefinitionMap.put("/**","user");
        filterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return filterFactoryBean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);

        return securityManager;
    }

    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher credentialsMatcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(credentialsMatcher);

        return authRealm;
    }

    @Bean("credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new PhoenixCredentialsMatcher();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor advisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);

        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator creator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);

        return creator;
    }

}
