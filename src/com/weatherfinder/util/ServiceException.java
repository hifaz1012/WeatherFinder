package com.weatherfinder.util;

import org.apache.log4j.Logger;

/**
 * @author hifaz
 * Generic Exception class to handle ServiceLayer Exception
 */

public class ServiceException extends Exception{

	
	private static final long serialVersionUID = -460536234242342737L;
	
	private static final Logger logger = Logger.getLogger(ServiceException.class);
	
	
	public ServiceException(String message, Exception e) {
		super(message, e);
		logger.error(message, e);
	}
	
	public ServiceException(Exception e) {
		super(e.getMessage(), e);
		logger.error(e.getMessage(), e);
	}
	
}
