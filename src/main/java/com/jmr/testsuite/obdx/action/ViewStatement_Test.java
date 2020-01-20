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
import com.jmr.testsuite.obdx.page.ViewStatement_Page;

import io.github.millij.poi.ss.reader.XlsxReader;


@ContextConfiguration(classes = SpringConfigurationObdx.class)
public class ViewStatement_Test extends AbstractTestNGSpringContextTests{

	@Autowired
	private OBDXData fcubs;

	@Autowired
	private XlsxReader xlsReader;
	private String errorMsg = "";
	private final String screenId = "View Statement";

	private List<ViewStatement_Page> dataList;
	private List<ViewStatement_Page> resultList;

	private String testCaseId;
	@PostConstruct
	public void initSetup() throws Exception {
		fcubs.launchApp();
		dataList = fcubs.loadDataFromExcel(ViewStatement_Page.class, screenId);
		resultList = new ArrayList<>();

	}

	@Test()
	public void executeTestCase() throws Exception {
		for (ViewStatement_Page data : dataList) {
			try {
				this.testCaseId = data.getTestCaseId();
				System.out.println("Executing Test Case ====>" + this.testCaseId);
				fcubs.setTestCaseId(testCaseId);
				if (data.getRunMode().equalsIgnoreCase("Yes") || data.getRunMode().equalsIgnoreCase("Y")) {
					fcubs.openMenu(data.getToggleMenu());
					fcubs.clickBtnByXpath("//span[@class='oj-select-chosen']");// for clicking account dropdown
					fcubs.selectAccountNo(data.getAccountNumber());// for selecting account
					fcubs.clickProceedBtn();
					fcubs.changeStatementFilter(data.getPeriod(),data.getStartDate(), data.getEndDate(), data.getTransactionType());
					try{
						String m="Error";
						if(m.equals(fcubs.getTextByXpath())){
							System.out.println(fcubs.getText("//div[contains(text(),'specified period.')]"));
							fcubs.clickBtnByXpathJS("//span[contains(text(),'Ok')]");
						}
					}
					catch(Exception ex)
					{
						fcubs.clickBtnByXpath("//span[contains(text(),'Download Statement')]");
						fcubs.clickBtnByXpath("//a[contains(text(),'" + data.getFileFormat() + "')]");
						try{
							fcubs.clickBtnByXpathJS("//span[contains(text(),'Ok')]");
						}
						catch(Exception e){}
						Thread.sleep(4000);
					}
					data.setTestCaseResult("SUCCESS");
					resultList.add(data);
					data.setTestCaseResult("SUCCESS");
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
			fcubs.takeScreenShot("Error_" + testCaseId);
		}
		System.out.println("Test Case Id\tResult");
		for (ViewStatement_Page data : resultList) {
			System.out.println(data.getTestCaseId() + "\t" + data.getTestCaseResult().replace("\n", ";"));
		}
	}

	@AfterClass
	public void destroy() throws Exception {
		System.out.println("before destroying opened session");
		fcubs.destroy();
	}

}
