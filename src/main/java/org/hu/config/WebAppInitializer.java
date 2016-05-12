package org.hu.config;

import javax.servlet.Filter;

import org.hu.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
 * �������web.xml�ļ�
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
	
	//ע�����������ӳ�䵽DispatcherServlet��
//	@Override
//	protected Filter[] getServletFilters() {
//		return new Filter[] {new CharacterEncodingFilter()};
//	}
}
