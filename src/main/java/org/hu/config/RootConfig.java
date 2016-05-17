package org.hu.config;

import org.hu.logservice.LogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/*
 * bean¹¤³§
 */
@Configuration
@Import(DataConfig.class)
@ImportResource("classpath:spring-mybatis.xml")
@ComponentScan(basePackages={"org.hu.data.dao","org.hu.logservice"})
@EnableAspectJAutoProxy
public class RootConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean LogAspect logAspect(){
		return new LogAspect();
	}
}
