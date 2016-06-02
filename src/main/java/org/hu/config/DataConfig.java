package org.hu.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/*
 * DataSource bean
 */
@Configuration
@PropertySource("classpath:db.properties")
public class DataConfig {

	@Autowired
	Environment env;
	
	@Bean
	public DataSource dataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		//dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		dataSource.setInitialSize(20);
		dataSource.setMaxActive(30);
		return dataSource;
	}
}
