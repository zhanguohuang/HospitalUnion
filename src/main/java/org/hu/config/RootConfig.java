package org.hu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
 * bean����
 */
@Configuration
@Import(DataConfig.class)
public class RootConfig {
	
}
