package org.hu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/*
 * bean¹¤³§
 */
@Configuration
@Import(DataConfig.class)
@ImportResource("classpath:spring-mybatis.xml")
@ComponentScan("org.hu.data.dao")
public class RootConfig {
	
}
