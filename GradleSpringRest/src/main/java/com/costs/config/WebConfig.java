package com.costs.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.costs")
public class WebConfig {

	private final Logger logger = Logger.getLogger(WebConfig.class);

	@Bean
	public InternalResourceViewResolver viewResolver() {

		logger.info("Ušao u WebConfig metodu");

		InternalResourceViewResolver view = new InternalResourceViewResolver();
		view.setPrefix("/WEB-INF/views/");
		view.setSuffix(".jsp");

		logger.info("Napušta WebConfig metodu");

		return view;
	}
}
