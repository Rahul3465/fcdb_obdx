package com.jmr.testsuite.obdx.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.jmr.testsuite.obdx.OBDXData;
import com.jmr.testsuite.obdx.SpringConfigurationObdx;
import com.jmr.testsuite.obdx.page.Login_Page;

import io.github.millij.poi.ss.reader.XlsxReader;

@ContextConfiguration(classes = SpringConfigurationObdx.class)
public class Login_Test extends AbstractTestNGSpringContextTests
{
	@Autowired
	private OBDXData fcubs;
	
	private String errorMsg = "";
	private final String screenId = "ScenarioLogin";

	private List<Login_Page> dataList;
	private List<Login_Page> resultList;

	private String testCaseId;
	
	@PostConstruct
	public void initSetup() throws Exception {
		dataList = fcubs.loadDataFromExcel(Login_Page.class, screenId);
		resultList = new ArrayList<>();

	}
	
	
	@Test()
	public void executeTestCase() throws Exception {
		for (Login_Page data : dataList) {
			try {
				this.testCaseId = data.getTestCaseId();
				System.out.println("Executing Test Case ====>" + this.testCaseId);
				fcubs.setTestCaseId(testCaseId);
				if (data.getRunMode().equalsIgnoreCase("Yes") || data.getRunMode().equalsIgnoreCase("Y")) {
					fcubs.verifyinglogin(data.getLink(), data.getUserName(),data.getPassWord());	
				}
			}
			catch(Exception e)
			{
				errorMsg = fcubs.checkError();
				if (fcubs.validInput(errorMsg)) {
					data.setTestCaseResult(errorMsg);
				} else if (fcubs.validInput(e.getMessage())) {
					data.setTestCaseResult(e.getMessage());
				}
				e.printStackTrace();
				resultList.add(data);
			}
		}
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		System.out.println("inside after method");
		if (!result.isSuccess()) {
			fcubs.takeScreenShot1("Error_" + testCaseId);
		}
		System.out.println("Test Case Id\tResult");
		for (Login_Page data : resultList) {
			System.out.println(data.getTestCaseId() + "\t" + data.getTestCaseResult().replace("\n", ";"));
		}
	}

	@AfterClass
	public void destroy() throws Exception {
		System.out.println("before destroying opened session");
		fcubs.destroy1();
	}
}
