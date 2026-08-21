package com.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class LoggerUtility {
	
	//global setup for your logger
	//design pattern helps you to ensure that your are using oo principles right way.
	//1. pojo, 2. page object model 3. Singleton design pattern
	
	//singleton --class will only have one object creation
	
	
	private LoggerUtility() {
		
		//cannot call the constructor outside the class
	}
	
	public static org.apache.logging.log4j.Logger getLogger(Class<?> clazz) {
       org.apache.logging.log4j.Logger logger=null;
		if(logger==null) {
		logger= LogManager.getLogger(clazz);
	}
		return logger;

}
}