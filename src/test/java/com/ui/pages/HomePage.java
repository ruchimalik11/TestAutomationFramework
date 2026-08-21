package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.BROWSER;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

import static com.utility.PropertiesUtil.*;

public class HomePage extends BrowserUtility{
	
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(), \"Sign in\")]");
    org.apache.logging.log4j.Logger logger= LoggerUtility.getLogger(this.getClass());

	
	public HomePage(BROWSER browserName, boolean isHeadless) {
		super(browserName, isHeadless);
		//goToWebsite(readProperty(QA, "URL"));
		goToWebsite(JSONUtility.readJSON(QA).getUrl());

	}

	
	public HomePage(WebDriver lambdaDriver) {
		super(lambdaDriver);
		goToWebsite(JSONUtility.readJSON(QA).getUrl());

	}


	public LoginPage goToLoginPage() {
		logger.info("Trying to perform click to go to Sign in Page");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage= new LoginPage(getDriver());
		return loginPage;
		
	}
	
	
	
	
}

