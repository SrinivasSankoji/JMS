package com.baledung.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedDemo {

	private static final Logger logger 
	  = LoggerFactory.getLogger(ParameterizedDemo.class);

	
	public static void main(String[] args) {
		String message = "This is a String";
		Integer zero = 0;
		try {
			logger.info("Logging message: {}", message);
			logger.info("Going to divide {} by {}", 42, zero);
			int result = 42 / zero;
		} catch (Exception e) {
		    logger.error("Error dividing {} by {} ", 42, zero, e);
		}
	}

}
