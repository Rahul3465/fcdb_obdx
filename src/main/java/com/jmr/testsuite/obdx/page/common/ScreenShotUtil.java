package com.jmr.testsuite.obdx.page.common;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jmr.testsuite.obdx.AppConfig;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

@Component
public class ScreenShotUtil {

	@Autowired
	private WebDriver driver;
	private String datePattern = "dd-MM-yyyy hh:mm:ss";

	public void takeScreenShot1(String fileName) {
		String location = AppConfig.reportLocation;
		String outputFile = "";
		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
			Date date = new Date();
			fileName = fileName + "_" + dateFormat.format(date).replaceAll(" ", "_").replaceAll(":", "_")+ AppConfig.sreenShotType;
			outputFile = location + "/" + fileName;
			System.out.println("file name===>" + fileName);
			System.out.println("file name and location ==========>" + outputFile);
			FileUtils.copyFile(sourceFile, new File(outputFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void takeScreenShot(String fileName) {
		String location = AppConfig.reportLocation;
		String outputFile = "";
		try {
		WebElement element = driver.findElement(By.xpath("(//div[contains(@class,'header-container')])[1]")); 
		((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility='hidden'", element);
		Screenshot fpScreenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(500)).takeScreenshot(driver);
		
			SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
			Date date = new Date();
			fileName = fileName + "_" + dateFormat.format(date).replaceAll(" ", "_").replaceAll(":", "_")+ AppConfig.sreenShotType;
			outputFile = location + "/" + fileName;
			System.out.println("file name===>" + fileName);
			System.out.println("file name and location ==========>" + outputFile);
			ImageIO.write(fpScreenshot.getImage(),"PNG",new File(outputFile));
			((JavascriptExecutor) driver).executeScript("arguments[0].style.visibility='visible'", element);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
