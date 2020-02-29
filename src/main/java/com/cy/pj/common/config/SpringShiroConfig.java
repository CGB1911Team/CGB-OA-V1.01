
package com.cy.pj.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.cache.SimpleCache;
/**
 * @Configuration 描述类时，表示这是由spring容器管理的一个配置类的对象
 * 
 * @author Administrator
 *
 */
@Configuration
public class SpringShiroConfig {
	
	/**
	 * 配置Shiro会话时长
	 */
	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sManager=
				new DefaultWebSessionManager();
		sManager.setGlobalSessionTimeout(7*24*60*60*1000);
		return sManager;
	}
	
	/**
	 * 配置shiro缓存
	 */
	@Bean
	public CacheManager shiroCacheManager() {
		return new MemoryConstrainedCacheManager();
	}
	
	@Bean
	public RememberMeManager rememberMeManager() {
		CookieRememberMeManager cManager=new CookieRememberMeManager();
		SimpleCookie cookie=new SimpleCookie("rememberMe");
		cookie.setMaxAge(7*24*60*60);
		cManager.setCookie(cookie);
		return cManager;
	}
	
	/**
	 * 配置SecurityManager对象，此对象是shiro对象的核心
	 * @Bean注解描述的方法的返回值要交给spring容器管理
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(Realm realm
			,CacheManager cacheManager
			,RememberMeManager rememberMeManager
			,SessionManager sessionManager) {
		DefaultWebSecurityManager sManager=
				new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		sManager.setCacheManager(cacheManager);
		sManager.setRememberMeManager(rememberMeManager);
		sManager.setSessionManager(sessionManager);
		return sManager;
	}
	
	/**
	 * 配置ShiroFilterFactoryBean對象，基于此对象创建过滤器工厂
	 * 通过过滤器工厂创建过滤器（Filter）,
	 * @param securityManager
	 * @return
	 */
	@Bean
	//默认有@Autowired
	public ShiroFilterFactoryBean shiroFilterFactory (
				 SecurityManager securityManager) {
			 ShiroFilterFactoryBean sfBean=
			 new ShiroFilterFactoryBean();
			 sfBean.setSecurityManager(securityManager);
			 sfBean.setLoginUrl("/doLoginUI");
			 //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
			 LinkedHashMap<String,String> map=
					 new LinkedHashMap<>();
			 //静态资源允许匿名访问:"anon"
			 map.put("/bower_components/**","anon");
			 map.put("/build/**","anon");
			 map.put("/dist/**","anon");
			 map.put("/plugins/**","anon");
			 map.put("/user/doLogin","anon");
			 map.put("/doLogout","logout");
			 //除了匿名访问的资源,其它都要认证("authc")后访问
			 //map.put("/**","authc");
			 map.put("/**","user");//当写了记住我后，
			 sfBean.setFilterChainDefinitionMap(map);
			 return sfBean;
		 }

	/**
	 * 配置shiro授权是需要的advisor(顾问)对象，此对象提供了切入点（PointCUt）
	 * 和通知相关信息（advice）
	 * 此advisor 对象中，描述了由@RequiresPermissions 
	 * 注解描述的方法会作为切入点
	 * 
	 * @param securityManager
	 * @return
	 */
	 @Bean
	 public AuthorizationAttributeSourceAdvisor 
	 authorizationAttributeSourceAdvisor(
	 	    		    SecurityManager securityManager) {
	 		        AuthorizationAttributeSourceAdvisor advisor=
	 				new AuthorizationAttributeSourceAdvisor();
	 //advisor.setSecurityManager(securityManager);
	 	return advisor;
	 }
}
