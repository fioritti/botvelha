package org.botvelha.infrastructure.logger;

public class Logger {

	private org.slf4j.Logger logger;
	private final static org.slf4j.Logger stacktraceLogger = org.slf4j.LoggerFactory.getLogger("stacktrace");

	public Logger(org.slf4j.Logger logger) {
		super();
		this.logger = logger;
	}
	
	public void error(Throwable e) {
		stacktraceLogger.trace("Exception", e);
	}
	
	public void info(String format, Object... arguments) {
		logger.info(format, arguments);
	}


	public void warn(String message) {
		logger.warn(message);
	}

	public void debug(String format, Object... arguments) {
		logger.debug(format, arguments);
	}

	public void warn(String message, Throwable e) {
		logger.warn(message, e);
	}


}
