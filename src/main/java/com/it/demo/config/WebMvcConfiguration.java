package com.it.demo.config;

import com.it.demo.interceptor.ApiSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器
 * @author Brave
 * @version 1.0
 * @date 2020/10/12
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Autowired
	private ApiSecurityInterceptor apiSecurityInterceptor;

	/**
	 * 添加自定义拦截器
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(apiSecurityInterceptor).addPathPatterns("/**");
	}
}
