package com.jmr.testsuite.obdx;

import javax.annotation.PreDestroy;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.stereotype.Component;
import org.testng.Reporter;
import org.testng.ReporterConfig;
import org.testng.ReporterConfig.Property;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

//@Component
public class Log {

	private ExtentReports report;
	private ExtentTest test;
	private Property property;

	public Log() {
		DOMConfigurator.configure("log4j.xml");
		property.setName("outputDirectory");
		property.setValue(AppConfig.logLocation);
		ReporterConfig conf = new ReporterConfig();
		conf.addProperty(property);
		
	}

	@PreDestroy
	public void destroy() {
		Reporter.clear();
	}
	
	public static void log(String msg) {
		Reporter.log(msg);
	}
}
