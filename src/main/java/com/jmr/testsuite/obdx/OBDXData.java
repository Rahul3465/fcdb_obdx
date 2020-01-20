package com.jmr.testsuite.obdx;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.ITestResult;

import com.jmr.testsuite.obdx.action.Login_Test;
import com.jmr.testsuite.obdx.page.Login_Page;
import com.jmr.testsuite.obdx.page.common.LoginPageActionObdx;
import com.jmr.testsuite.obdx.page.common.LoginPageObdx;
import com.jmr.testsuite.obdx.page.common.ScreenShotUtil;
import com.jmr.testsuite.obdx.page.common.UIFormatException;

import io.github.millij.poi.ss.reader.XlsxReader;

@Component
public class OBDXData {

	@Autowired
	private  WebDriver driver;
	private WebDriverWait waitDriver;
	@Autowired
	private LoginPageObdx loginPageobdx;
	private Login_Page loginpage;
	@Autowired
	private LoginPageActionObdx loginactionobdx;
	private Login_Test logintest;
	@Autowired
	private ScreenShotUtil screenShotUtil;
	/*@Autowired
		private TransactionManager txManager;*/
	@Autowired
	private XlsxReader xlsReader;

	private Actions actions;
	private WebElement element;

	private String screenName;
	private String screenId;
	private String testCaseId;
	private boolean isChildScreenOpened = false;
	private String subScreenName;
	private String dateFormat;
	private JavascriptExecutor jsExecutor;


	@PostConstruct
	public void postConstruct() {
		actions = new Actions(driver);
		waitDriver = new WebDriverWait(driver, 5);
		jsExecutor = (JavascriptExecutor) driver;
	}


	private boolean checkElementNValue(String elementId, String value) {
		if ((value != null && !value.equalsIgnoreCase("")) && (elementId != null && !elementId.equalsIgnoreCase(""))) {
			return true;
		}
		return false;
	}

	/* @param elementId
	 *            - html id of button
	 */
	public void clickButtonById(String elementId) {
		if (validInput(elementId)) {
			element = driver.findElement(By.id(elementId));
			actions.moveToElement(element).build().perform();
			jsExecutor.executeScript("arguments[0].click();", element);
		}
	}

	/**
	 * Click on any available button in active screen using html xpath of the
	 * element
	 * 
	 * @param elementId
	 *            - html id of button
	 */
	public void clickButtonByXpath(String xpath) {
		if (validInput(xpath)) {
			element = driver.findElement(By.xpath(xpath));
			/*actions.moveToElement(element).build().perform();
				jsExecutor.executeScript("arguments[0].click();", element);*/
			element.click();
		}
	}

	/**
	 * destroy the current session opened
	 */
	public void destroy() {
		clickButtonByXpath("//span[contains(text(),'Logout')]");
		driver.quit();
	}

	/**
	 * Logout from application
	 */
	public void logout()
	{
		clickButtonByXpath("//span[contains(text(),'Logout')]");
	}

	public void destroy1() {	
		driver.quit();
	}

	public void focusToElement(WebElement element) {
		actions.moveToElement(element).perform();
	}

	/**
	 * Launch ZipBank application and do login
	 * 
	 * @throws Exception
	 */
	public void launchApp() throws Exception {
		driver.get(loginPageobdx.getLink());
		loginactionobdx.doLogin();
		//getdateFormat();
	}

	/**
	 * Launch ZipBank application and test login scenario's
	 * @return 
	 * 
	 * @throws Exception
	 */
	public String verifyinglogin(String url, String username, String password) throws Exception {
		String message = "";
		driver.get(url);
		populateTextById("login_username", username);
		populateTextById("login_password", password);
		clickButtonById("login-button");
		Thread.sleep(4000);
		try{
			logout();
		}
		catch(Exception e)
		{

			message = driver.findElement(By.xpath(("//span[contains(text(), 'Authentication Failed')]"))).getText();
			if (validInput(message) || message != null) {
				screenShotUtil.takeScreenShot1(this.testCaseId);
				return "Authentication Failed";
			}
			message = "Authentication Failed";
			updateTestCaseExecutionAsFailed();

		}
		return  message;
	}

	/**
	 * To input any text/lov field
	 * 
	 * @param elementId
	 *            - html attribute id of element
	 * @param text
	 *            - value to be input
	 */
	public void populateTextById(String elementId, String text) throws UIFormatException {
		if (checkElementNValue(elementId, text)) {
			element = driver.findElement(By.id(elementId));
			focusToElement(element);
			if (validInput(element.getAttribute("value"))) {
				element.click();
				element.clear();
				element.sendKeys(text);
				element.sendKeys(Keys.TAB);
			} else {
				element.click();
				element.sendKeys(text);
				element.sendKeys(Keys.TAB);
			}
		}
	}

	public void populateTextByXpath(String elementId, String text) throws UIFormatException {
		if (checkElementNValue(elementId, text)) {
			element = driver.findElement(By.xpath(elementId));
			focusToElement(element);
			if (validInput(element.getAttribute("class"))) {
				element.sendKeys(text);
			} else {
				element.sendKeys(text);
			}
		}
	}

	public void takeScreenShot(String fileName) throws Exception {
		screenShotUtil.takeScreenShot(fileName);
	}

	public void takeScreenShot1(String fileName) throws Exception {
		screenShotUtil.takeScreenShot1(fileName);
	}
	/**
	 * Validate input for Null and Empty String
	 * 
	 * @param value
	 * @return
	 */
	public boolean validInput(String value) {
		if (value != null && !value.equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}


	public void updateTestCaseExecutionAsFailed() throws SQLException {
		//txManager.updateTestProgCaseExecStatAsProceedAndResultAsFailed(this.testCaseId);
	}
	//
	@SuppressWarnings({ "unchecked", "resource", "rawtypes" })
	public <T> List<T> loadDataFromExcel(Class clazz, String sheetName) throws Exception {
		FileInputStream file = new FileInputStream(new File(AppConfig.testFileLocation));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		int sheetIndex = workbook.getSheetIndex(sheetName);
		file.close();
		List<T> list = (List<T>) xlsReader.read(clazz, new File(AppConfig.testFileLocation), sheetIndex);
		return list;
	}

	public void openMenu(String actionName) throws Exception {
		if (CommonUtils.validInput(actionName)) {

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(2000);
			waitDriver.until(
					ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@alt='Toggle Menu']"))))
			.click();
			if (actionName.contains(">")) {
				for (String menu : actionName.split(">")) {
					waitDriver.until(
							ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[contains(text(),'" + menu + "')]"))))
					.click();
					//driver.findElement(By.xpath("//span[contains(text(),'" + menu + "')]")).click();
				}
			} else {
				waitDriver.until(
						ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[contains(text(),'" + actionName + "')]"))))
				.click();
				//driver.findElement(By.xpath("//span[contains(text(),'" + actionName + "')]")).click();
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void inputByXpath(String xpath, String value) throws Exception {
		if (CommonUtils.validInput(xpath) && CommonUtils.validInput(value)) {
			Thread.sleep(2000);
			driver.findElement(By.xpath(xpath)).sendKeys(value);
		}
	}

	public void clickBtnByXpath(String xpath) {
		if (CommonUtils.validInput(xpath)) {
			waitDriver.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpath)))).click();
		}
	}

	public void changeStatmentPeriod(String period) {

	}

	public void changeStatmentTxnType(String txnType) {

	}

	public void clickProceedBtn() {
		clickBtnByXpath("//span[contains(text(),'Proceed')]");
		try{
			Thread.sleep(2000);
			clickBtnByXpathJS("//span[contains(text(),'Ok')]");
		}catch(Exception e){}
	}


	public void selectAccountNo(String accNo) {
		if (CommonUtils.validInput(accNo)) {
			clickBtnByXpath("//div[@aria-label='" + accNo + "']");
		}
	}

	/*	public void changeStatementFilter(String period, String startDate, String endDate, String txnType ){
		if (CommonUtils.validInput(period)) {
			if (period.equalsIgnoreCase("Current Period")) {
				return;
			} else {
				clickBtnByXpath("//span[contains(text(),'Current Period')]");
				clickBtnByXpath("//div[contains(text(),'" + period + "')]");
			}
		}
		if (CommonUtils.validInput(txnType)) {
			if (txnType.equalsIgnoreCase("All")) {
				return;
			} else {
				clickBtnByXpath("//span[contains(text(),'All')]");
				clickBtnByXpath("//div[contains(text(),'" + txnType + "')]");
			}
		}
		if (CommonUtils.validInput(startDate) && CommonUtils.validInput(endDate)) {
			inputByXpath("(//input[@placeholder='dd mmm yyyy'])[1]", startDate);
			inputByXpath("(//input[@placeholder='dd mmm yyyy'])[2]", endDate);
		}
		if (!period.equalsIgnoreCase("Current Period") || !txnType.equalsIgnoreCase("All")
				|| (CommonUtils.validInput(startDate) && CommonUtils.validInput(endDate)))
			clickBtnByXpath("//span[contains(text(),'Apply Filter')]");
		try
		{
			clickBtnByXpathJS("//span[contains(text(),'Ok')]");
		}catch(Exception e){}
	}*/

	/*
	 * 
	 * Rahul
	 */
	public void clickBtnByXpathJS(String xpath) {
		if (CommonUtils.validInput(xpath)) {
			element = driver.findElement(By.xpath(xpath));
			jsExecutor.executeScript("arguments[0].click();", element);
		}
	}

	public void setTestCaseId(String testCaseId) {
		this.testCaseId = testCaseId;
	}


	public String getText(String xpath )
	{

		String msg = "";
		msg = driver.findElement(By.xpath(xpath)).getText();
		return msg;
	}

	public void changeStatementFilter(String period, String startDate, String endDate, String txnType) throws Exception{
		if (CommonUtils.validInput(period)) {
			if (period.equalsIgnoreCase("Current Period") || !period.equalsIgnoreCase("Current Period")) {
				clickBtnByXpath("//span[contains(text(),'Current Period')]");
				clickBtnByXpath("//div[contains(text(),'" + period + "')]");
			}
		}
		if (CommonUtils.validInput(txnType)) {
			if (txnType.equalsIgnoreCase("All") ||!txnType.equalsIgnoreCase("All")) {
				clickBtnByXpath("//span[contains(text(),'All')]");
				clickBtnByXpath("//div[contains(text(),'" + txnType + "')]");
			}
		}
		if (CommonUtils.validInput(startDate) && CommonUtils.validInput(endDate)) {
			inputByXpath("(//input[@placeholder='dd mmm yyyy'])[1]", startDate);
			inputByXpath("(//input[@placeholder='dd mmm yyyy'])[2]", endDate);
		}
		if (!period.equalsIgnoreCase("Current Period") || !txnType.equalsIgnoreCase("All")
				|| (CommonUtils.validInput(startDate) && CommonUtils.validInput(endDate)))
			clickBtnByXpath("//span[contains(text(),'Apply Filter')]");
		try
		{
			clickBtnByXpathJS("//span[contains(text(),'Ok')]");
		}catch(Exception e){}
	}


	public String getTextByXpath()
	{
		String msg="";
		msg = driver.findElement(By.xpath(("//span[contains(text(),'Error')]"))).getText();
		return msg;
	}

	/**
	 * to select radio button
	 * 
	 * @param tagName
	 *            - html attribute name of radio button
	 * @param keyValue
	 *            - html attribute label value of element
	 */
	public void selectRadioBtn(String tagName, String keyValue) {
		if (checkElementNValue(tagName, keyValue)) {
			List<WebElement> elementList = driver.findElements(By.xpath(tagName));
			for (WebElement element : elementList) {
				if (element.getText().equalsIgnoreCase(keyValue) && !element.isSelected()) {
					// moveToElement(element).click().build().perform();
					focusToElement(element);
					jsExecutor.executeScript("arguments[0].click();", element);
				}
			}
		}
	}

	//Select and click Payee
	public void selectPayee(String internationalPayee, String payee) throws Exception
	{
		if(internationalPayee.equalsIgnoreCase("Yes")){
		clickBtnByXpath("//span[contains(text(),'Please Select')]");
		clickBtnByXpath("//div[text()='" + payee + "']");
		clickBtnByXpath("//div[@class='dualdropdown']//li");
		Thread.sleep(2000);
		}
	}

	public void payVia(String payvia, String swift_NCC,String bankName, String bankAddress, String country, String city) throws Exception
	{
		String m = "Error";
		if(payvia.equalsIgnoreCase("SWIFT Code"))
		{
			selectRadioBtn("//input[@name='payvia']/../../label/span", payvia);
			inputByXpath("//input[@id='transferViaCode_international']", swift_NCC);
			clickBtnByXpath("//span[text()='Verify']");
			try{
				if(m.equalsIgnoreCase(getText("//span[text()='Error']")))
				{
					clickBtnByXpathJS("//span[contains(text(),'Ok')]");
				}
			}catch(Exception e){}
		}
		if(payvia.equalsIgnoreCase("NCC"))
		{
			selectRadioBtn("//input[@name='payvia']/../../label/span", payvia);
			inputByXpath("//input[@id='transferViaCode_international']", swift_NCC);
			clickBtnByXpath("//span[text()='Verify']");
			try{
				if(m.equalsIgnoreCase(getText("//span[text()='Error']")))
				{
					clickBtnByXpathJS("//span[contains(text(),'Ok')]");
				}
			}catch(Exception e){}
		}
		if(payvia.equalsIgnoreCase("Bank Details"))
		{
			selectRadioBtn("//input[@name='payvia']/../../label/span", payvia);
			inputByXpath("//input[@id='networkTransferViaBankName']", bankName);
			inputByXpath("//input[@id='networkTransferViaBankAddress']", bankAddress);
			clickBtnByXpath("//span[text()='Please Select']");
			clickBtnByXpath("//li/div[@class='oj-listbox-result-label' and text()='" + country + "']");
			inputByXpath("//input[@id='networkTransferViaCity']", city);
		}
	}

	public String checkError() throws Exception
	{
		String msg = "";
		try{
			jsExecutor.executeScript("window.scrollBy(0,-500)");
			msg = driver.findElement(By.xpath(("//div[contains(@class, 'error')]/span/div[2]/span"))).getText();
			if (validInput(msg) || msg != null) {
				screenShotUtil.takeScreenShot(this.testCaseId);
			}
		}
		catch(Exception e)
		{
			screenShotUtil.takeScreenShot(this.testCaseId);
		}
		updateTestCaseExecutionAsFailed();
		return msg;
	}
	public String error() throws Exception
	{
		String msg = "";

		jsExecutor.executeScript("window.scrollBy(0,-500)");
		msg = driver.findElement(By.xpath(("//div[contains(@class, 'error')]/span/div[2]/span"))).getText();
		if (validInput(msg) || msg != null) {
			screenShotUtil.takeScreenShot(this.testCaseId);
		}
		return msg;
	}
}




