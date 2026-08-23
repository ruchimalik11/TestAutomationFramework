package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.constants.BROWSER;

public abstract class BrowserUtility {
	
	private static ThreadLocal<WebDriver> driver= new ThreadLocal<WebDriver>();
    org.apache.logging.log4j.Logger logger= LoggerUtility.getLogger(this.getClass());

	public WebDriver getDriver() {
		return driver.get();
	}
	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);//initialize the instance variable driver
	}
	
	public BrowserUtility(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			logger.info("Launching browser for "+ browserName);
			driver.set(new ChromeDriver());
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			
			driver.set(new EdgeDriver());
		}
else {
	logger.error("Invalid browser name...please select chrome or edge only");
	System.err.print("Please select chrome or edge only");
}
	}
	
public BrowserUtility(BROWSER browserName, boolean isHeadless) {
		logger.info("Launching browser for " +browserName);
		if(browserName==BROWSER.CHROME ) {
			if(isHeadless) {
			ChromeOptions options= new ChromeOptions();
			options.addArguments("--headless=old");
			options.addArguments("--window-size=1920,1080");
			driver.set(new ChromeDriver(options));
		}else {
			driver.set(new ChromeDriver());

		}	
		}
		else if(browserName==BROWSER.EDGE) {
			
			if(isHeadless) {
			EdgeOptions options= new EdgeOptions();
			options.addArguments("--headless=old");
			options.addArguments("disable-gpu");
			driver.set(new EdgeDriver(options));
		}
else {
	
	driver.set(new EdgeDriver());

}
	System.err.print("Please select chrome or edge only");
}
	}

public void goToWebsite(String url) {
	logger.info("Visiting the website "+url);
	driver.get().get(url);
}

public void maximizeWindow() {
	logger.info("Maximizing the browser window");
	driver.get().manage().window().maximize();

}
public void clickOn(By locator) {
	logger.info("Finding element with the locator "+ locator);
	WebElement element=driver.get().findElement(locator);//find the element
	logger.info("Element found and now performing click");
	element.click();

}

public void enterText(By locator, String textToEnter) {
	logger.info("Finding element with the locator "+ locator);
	WebElement element= driver.get().findElement(locator);
	logger.info("Element found and now enter text "+textToEnter);
	element.sendKeys(textToEnter);
}

public String getVisibleText(By locator) {
	logger.info("Finding element with the locator "+ locator);
	WebElement element= driver.get().findElement(locator);
	logger.info("Element found and now returning the visible text" +element.getText());

	return element.getText();
	
}

public static void quit() {
	
	if(driver.get()!=null) {
		
		driver.get().quit();
		
	}
}

public String takeScreenShot(String name) {
	
	TakesScreenshot screenshot=(TakesScreenshot)driver.get();
	File screenshortData=screenshot.getScreenshotAs(OutputType.FILE);
	Date date= new Date();
	SimpleDateFormat format= new SimpleDateFormat("HH-mm-ss");
	String timeStamp= format.format(date);
	String path=System.getProperty("user.dir")+"/screenshots/"+name+"-"+timeStamp+".png";
	File screenshotFile= new File(path);
	try {
		FileUtils.copyFile(screenshortData, screenshotFile);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return path;
}


}
