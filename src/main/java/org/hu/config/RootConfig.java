package org.hu.config;

import org.hu.logservice.LogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/*
 * bean¹¤³§
 */
@Configuration
@Import({DataConfig.class,SecurityConfig.class})
@ImportResource("classpath:spring-mybatis.xml")
@ComponentScan(basePackages={"org.hu.data.dao","org.hu.logservice","org.hu.service"})
@EnableAspectJAutoProxy
public class RootConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public JavaMailSenderImpl mailSender(Environment env){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.ym.163.com");
		mailSender.setPort(25);
		mailSender.setUsername("zhanguohuang@szhuarong.com");
		mailSender.setPassword("zzzxxc125");
		return mailSender;
	}
}
