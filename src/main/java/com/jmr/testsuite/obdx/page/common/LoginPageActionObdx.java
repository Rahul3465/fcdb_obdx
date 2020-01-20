package com.jmr.testsuite.obdx.page.common;

import javax.annotation.PostConstruct;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginPageActionObdx {
	
	private WebDriverWait waitDriver;

	@Autowired
	private WebDriver driver;
	@Autowired
	private LoginPageObdx loginPageobdx;
	
	@FindBy(id = "login_username")
	private WebElement userName;

	@FindBy(id = "login_password")
	private WebElement paswd;

	@FindBy(id = "login-button")
	private WebElement okBTN;

/*	@FindBy(xpath = "//input[@type='submit']")
	private WebElement btnLogin;*/
	
	@PostConstruct
	public void init() {
		waitDriver = new WebDriverWait(driver,5);
	}

	public void doLogin() throws Exception {
		PageFactory.initElements(driver, this);
		userName.sendKeys(loginPageobdx.getUserName());
		paswd.sendKeys(loginPageobdx.getPassWord());
		okBTN.click();
		/*driver.findElement(By.id("login-button")).submit();
		waitDriver.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		okBTN.click();*/
	}

}
