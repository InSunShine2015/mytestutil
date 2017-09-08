package com.sun.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Client {
	// 参考博客http://blog.csdn.net/autfish/article/details/51203709
	public static void main(String[] args) {
		Logger log = LogManager.getLogger(Log4j2Client.class);
		log.info("this is a info log");

		// Logger logger = LogManager.getLogger("mylog");
		// logger.trace("mylog trace level");
		// logger.debug("mylog debug level");
		// logger.info("mylog info level");
		// logger.warn("mylog warn level");
		// logger.error("mylog error level");
		// logger.fatal("mylog fatal level");

		Logger logger = LogManager.getLogger("mylog");
		for (int i = 0; i < 50000; i++) {
			logger.trace("trace level");
			logger.debug("debug level");
			logger.info("info level");
			logger.warn("warn level");
			logger.error("error level");
			logger.fatal("fatal level");
		}
		try {
			Thread.sleep(1000 * 61);
		} catch (InterruptedException e) {
		}
		logger.trace("trace level");
		logger.debug("debug level");
		logger.info("info level");
		logger.warn("warn level");
		logger.error("error level");
		logger.fatal("fatal level");

		log.info("test end");
	}
}
