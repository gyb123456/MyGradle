package com;

import javax.servlet.Filter;

import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 整个项目的程序入口
 * @author gyb
 *
 */
public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override //根容器，用于获取Spring应用容器的配置文件
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}

	@Override //Spring mvc容器，是根容器的子容器
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override //"/"表示由DispatcherServlet处理所有向该应用发起的请求。
	protected String[] getServletMappings() {
		return new String[]{"/"};	
	}

	@Override
	protected Filter[] getServletFilters() {
//			OpenSessionInViewFilter  openSessionInViewFilter  = new OpenSessionInViewFilter ();
			CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();  
	        characterEncodingFilter.setEncoding("UTF-8");  
	        characterEncodingFilter.setForceEncoding(true);  
	        return new Filter[] {characterEncodingFilter,characterEncodingFilter};  
//		return super.getServletFilters();
	}
	
}
