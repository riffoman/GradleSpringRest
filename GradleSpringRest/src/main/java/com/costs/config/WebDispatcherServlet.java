package com.costs.config;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebDispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

	private final Logger logger = Logger.getLogger(WebDispatcherServlet.class);

	protected Class<?>[] getRootConfigClasses() {
		logger.info("getRootConfigClasses");
		// TODO Auto-generated method stub
		return null;
	}

	protected Class<?>[] getServletConfigClasses() {
		logger.info("getServletConfigClasses");
		// TODO Auto-generated method stub
		return new Class[] { WebConfig.class };
	}

	protected String[] getServletMappings() {
		logger.info("getServletMappings");
		// TODO Auto-generated method stub
		return new String[] { "/" };
	}

}
