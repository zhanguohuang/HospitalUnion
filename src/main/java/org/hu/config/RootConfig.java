package org.hu.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/*
 * bean¹¤³§
 */
@Configuration
@Import({DataConfig.class,SecurityConfig.class})
@ImportResource("classpath:spring-mybatis.xml")
@ComponentScan(basePackages={"org.hu.data.dao","org.hu.logservice","org.hu.service"})
@PropertySource("classpath:email.properties")
@EnableAspectJAutoProxy
public class RootConfig {
	
	@Autowired
	Environment env;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public JavaMailSenderImpl mailSender(Environment env){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(env.getProperty("email.host"));
		mailSender.setPort(25);
		mailSender.setUsername(env.getProperty("email.username"));
		mailSender.setPassword(env.getProperty("email.password"));
		return mailSender;
	}
	
	@Bean
	public MultipartResolver multipartResolver() throws IOException{
		return new StandardServletMultipartResolver();
	}
}
