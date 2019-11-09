package com.qf.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.qf.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroCinfiguration {

    @Bean(name = "getShiroFilter")
    public ShiroFilterFactoryBean getShiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/showPage");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauth");

        Map<String,String> map = new HashMap<String, String>();
        map.put("/loginP","anon");
        map.put("/login","anon");
        map.put("/**","authc");

        map.put("/addPage","roles[role1]");
        map.put("/addQingPage","roles[role1]");

        map.put("/aboQing","roles[role2]");
        map.put("/updateHoliday","roles[role2]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public SecurityManager getSecurityManager(MyRealm myRealm){
        SecurityManager securityManager = new DefaultWebSecurityManager(myRealm);
        return securityManager;
    }

    @Bean(name = "myRealm")
    public MyRealm getMyRealm(){
        return new MyRealm();
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }

    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        return credentialsMatcher;
    }
}
