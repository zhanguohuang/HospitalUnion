package org.hu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
/*
 * controller bean and viewresolver
 */
@Configuration
@EnableWebMvc
@ComponentScan("org.hu.web.Controller")
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	public ViewResolver viewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
		configurer.enable();
	}
	
	/**
	 * ����������ݵ�����
	 * ���ڵ�ȱ�ݣ�ֻ���ж���ʲô���ͻ��ˣ�������֪���ͻ��˷�ʲô�������ݸ�������������json,xmlʱ������͹���
	 * �����ڿ���������@RequestBody��@RequestParam�������json������
	 * һ���漰json,��classһ��Ҫ����,����Ҫ����jackson��core����databind��
	 */
	@Configuration
	public static class ContentNegotiationConfig extends WebMvcConfigurerAdapter{
		@Bean
		public ViewResolver cnViewResolver(ContentNegotiationManager cnm){
			ContentNegotiatingViewResolver cnvr = new ContentNegotiatingViewResolver();
			cnvr.setContentNegotiationManager(cnm);
			return cnvr;
		}
		
		@Override
		public void configureContentNegotiation(ContentNegotiationConfigurer configurer){
			configurer.defaultContentType(MediaType.TEXT_HTML);  //Ĭ��ΪHTML
		}
		
		@Bean
		public ViewResolver beanNameViewResovler(){
			return new BeanNameViewResolver();        //��bean����ʽ������ͼ
		}
		
		@Bean
		public View alluser(){
			return new MappingJackson2JsonView();    //��alluser����Ϊjson��ͼ
		}
	}
}
