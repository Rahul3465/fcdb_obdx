package com.jmr.testsuite.obdx;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.jmr.testsuite.obdx.page.common.LoginPageObdx;

import io.github.millij.poi.ss.reader.XlsxReader;

@Configuration
@ComponentScan(basePackages = "com.jmr.testsuite.obdx")
public class SpringConfigurationObdx {

	@Bean(autowire = Autowire.BY_TYPE)
	@Required
	public WebDriver webDriver() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/jrahu/Downloads/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		/*
		 * System.setProperty("webdriver.firefox.marionette","lib/geckodriver.exe");
		 * WebDriver driver = new FirefoxDriver();
		 */
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	@Bean
	@Required
	public XlsxReader xlsxReader() {
		return new XlsxReader();
	}

	
	@Bean
	@Required
	public LoginPageObdx loginPageobdx() throws Exception {
		LoginPageObdx loginPageobdx = null;
		File file = new File(AppConfig.testFileLocation);
		XlsxReader xlsReader = new XlsxReader();
		List<LoginPageObdx> loginList = xlsReader.read(LoginPageObdx.class, file, 0);
		loginPageobdx = loginList.get(0);
		System.out.println(loginPageobdx);
		return loginPageobdx;
	}

	@Bean
	public OBDXData fcubs() {
		return new OBDXData();
	}
	
	/*@Bean
	@Required
	public DriverManagerDataSource driverManagerDataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		driverManagerDataSource.setUrl("jdbc:oracle:thin:@192.168.1.92:1521:fcarb01");
		driverManagerDataSource.setUsername("flexcubeauto");
		driverManagerDataSource.setPassword("flexcubeauto");
		return driverManagerDataSource;
	}*/
}
