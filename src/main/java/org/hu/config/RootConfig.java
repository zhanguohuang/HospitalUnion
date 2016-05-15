package org.hu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/*
 * bean¹¤³§
 */
@Configuration
@Import(DataConfig.class)
@ImportResource("classpath:spring-mybatis.xml")
@ComponentScan("org.hu.data.dao")
public class RootConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
}
