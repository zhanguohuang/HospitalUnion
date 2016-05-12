package org.hu.config;

import javax.servlet.Filter;

import org.hu.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * 用来替代web.xml文件
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	@Override
	protected String[] getServletMappings(){
		return new String[] { "/" };
	}
	
	@Override
	protected Class<?>[] getRootConfigClasses(){
		return new Class<?>[] { RootConfig.class };
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses(){
		return new Class<?>[] { WebConfig.class };
	}
	
	//注册过滤器，会映射到DispatcherServlet上
//	@Override
//	protected Filter[] getServletFilters() {
//		return new Filter[] {new CharacterEncodingFilter()};
//	}
}
