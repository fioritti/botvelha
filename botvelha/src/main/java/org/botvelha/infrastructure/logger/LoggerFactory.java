package org.botvelha.infrastructure.logger;

public class LoggerFactory {
	
	public static Logger getLogger(Class<?> clazz){
		org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(clazz);
		Logger logger2 = new Logger(logger);
		return logger2;
	}
}
