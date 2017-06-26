package com;

import javax.servlet.Filter;

import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};	//将DispatcherServlet映射到"/"
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
