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
	 * 配置输出内容导航类
	 * 存在的缺陷，只能判定发什么给客户端，而不能知道客户端发什么样的内容给控制器，比如json,xml时，服务就挂了
	 * 可以在控制器中用@RequestBody和@RequestParam解决接受json的问题
	 * 一旦涉及json,此class一定要配置,而且要导入jackson的core包和databind包
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
			configurer.defaultContentType(MediaType.TEXT_HTML);  //默认为HTML
		}
		
		@Bean
		public ViewResolver beanNameViewResovler(){
			return new BeanNameViewResolver();        //以bean的形式查找视图
		}
		
		@Bean
		public View alluser(){
			return new MappingJackson2JsonView();    //将alluser定义为json视图
		}
	}
}
