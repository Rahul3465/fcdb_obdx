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
import com.jmr.testsuite.obdx.page.TransferMoneyInternational_Page;

import io.github.millij.poi.ss.reader.XlsxReader;

@ContextConfiguration(classes = SpringConfigurationObdx.class)
public class TransferMoneyInternational_Test extends AbstractTestNGSpringContextTests
{
	@Autowired
	private OBDXData fcubs;

	@Autowired
	private XlsxReader xlsReader;
	private String errorMsg = "";

	private final String screenId = "Transfer Money";

	private List<TransferMoneyInternational_Page> dataList;
	private List<TransferMoneyInternational_Page> resultList;
	private String testCaseId;
	@PostConstruct
	public void initSetup() throws Exception {
		fcubs.launchApp();
		dataList = fcubs.loadDataFromExcel(TransferMoneyInternational_Page.class, screenId);
		resultList = new ArrayList<>();
	}

	@Test()
	public void executeTestCase() throws Exception {
		for (TransferMoneyInternational_Page data : dataList) {
			try {
				this.testCaseId = data.getTestCaseId();
				System.out.println("Executing Test Case ====>" + this.testCaseId);
				fcubs.setTestCaseId(testCaseId);
				if (data.getRunMode().equalsIgnoreCase("Yes") || data.getRunMode().equalsIgnoreCase("Y")) {
					fcubs.openMenu(data.getToggleMenu());

					//Transfer Type
					fcubs.selectRadioBtn("//span[@class='oj-radiocheckbox-label-text' and contains(text(),'" + data.getTransferType() + "')]", data.getTransferType());

					//Payee
					fcubs.selectPayee(data.getInternationalPayee(), data.getPayee());

					//Transfer From
					fcubs.clickBtnByXpath("(//a[@role='presentation']/../span[@class='oj-select-chosen'])[1]");
					fcubs.clickBtnByXpath("//ul[@class='oj-listbox-result-sub']//div[contains(text(),'" + data.getTransferFrom() + "')]");

					//Currency Type
					try
					{
						fcubs.clickBtnByXpath("(//a[@role='presentation']/../span[@class='oj-select-chosen'])[2]");
						fcubs.clickBtnByXpath("//ul[@class='oj-listbox-results']//div[contains(text(),'" + data.getCurrency() + "')]");
					}catch(Exception e){}
					// Amount
					fcubs.populateTextByXpath("//input[@data-id='amount']", data.getAmount());

					//Transfer When
					fcubs.selectRadioBtn("//span[@class='oj-radiocheckbox-label-text' and contains(text(),'" + data.getTransferWhen() + "')]", data.getTransferWhen());
					fcubs.populateTextByXpath("//input[@class='oj-inputdatetime-input oj-component-initnode']", data.getTransferDate());

					// Correspondence Charges
					try{
						fcubs.clickBtnByXpath("(//span[text()='Select'])[1]");
						fcubs.clickBtnByXpath("//div[@class='oj-listbox-result-label' and text()='" + data.getCorrespondenceCharges() + "']");
					}catch(Exception e){}

					// Transfer via Intermediary Bank
					try{
						fcubs.selectRadioBtn("//input[@type='radio' and @name='transferViaBank']/../../label", data.getTransferViaIntermediaryBank());
						fcubs.payVia(data.getPayvia(), data.getSwiftCode_NCC(), data.getBankName(), data.getBankAddress(), data.getCountry(), data.getCity());
					}catch(Exception e){}

					// Payment Details
					fcubs.populateTextById("paymentdetails", data.getPaymentDetails());

					//Note(Optional)
					fcubs.populateTextByXpath("//span[contains(text(),'80 Characters Left')]/..//input", data.getNote());

					// Transfer/Cancel
					try{
					fcubs.clickBtnByXpath("//span[text()='"+ data.getTransfer_Cancel() +"']");
					}catch(Exception e){}
					Thread.sleep(2000);

					try
					{
						errorMsg = fcubs.error();
					}
					catch(Exception e){}

					try{
						System.out.println(fcubs.getText("//div[contains(@class,'error')]/div"));
						fcubs.takeScreenShot1(data.getTestCaseId());
						fcubs.clickBtnByXpathJS("//div[contains(@class,'button-container message-box__buttons')]//div/span[2]");
					}catch(Exception e){}

					try{
						//Confirm/Back/Cancel
						fcubs.clickBtnByXpath("//span[contains(text(),'"+ data.getConfirm_Back_Cancel() +"')]");
						Thread.sleep(2000);
					}catch(Exception e){}

					try
					{
						System.out.println(fcubs.getText("//div[contains(@class,'error')]/div"));
						fcubs.takeScreenShot1(data.getTestCaseId());
						fcubs.clickBtnByXpathJS("//span[contains(text(),'Ok')]");
					}catch(Exception e){}

					try{
						System.out.println(fcubs.getText("//div[@class='confirm-screen__confirm-text-heading']/div[@data-id='success-message']"));
					}catch(Exception e){}
					data.setTestCaseResult("SUCCESS");
					resultList.add(data);
					data.setTestCaseResult("SUCCESS");
				}
			}
			catch(Exception e){
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
		for (TransferMoneyInternational_Page data : resultList) {
			System.out.println(data.getTestCaseId() + "\t" + data.getTestCaseResult().replace("\n", ";"));
		}
	}

	@AfterClass
	public void destroy() throws Exception {
		System.out.println("before destroying opened session");
		fcubs.destroy();
	}
}
