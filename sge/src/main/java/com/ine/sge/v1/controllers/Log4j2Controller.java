package com.ine.sge.v1.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Log4j2Controller {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping("/log")
	String index(){
		logger.debug("Debug message");
		logger.info("Info message");
		logger.warn("Warn message");
		logger.error("Error message");
		return "Done";
	}
	public void doSomething() {
		logger.info("some message");
	}
}
