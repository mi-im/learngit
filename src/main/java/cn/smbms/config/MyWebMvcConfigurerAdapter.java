package cn.smbms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.smbms.interceptor.LoginInterceptor;

@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
		
		super.addInterceptors(registry);
	}

	
}
