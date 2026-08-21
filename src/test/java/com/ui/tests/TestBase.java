package com.ui.tests;

import static com.constants.BROWSER.CHROME;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.BROWSER;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {

	protected HomePage homePage;
	org.apache.logging.log4j.Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;
    @Parameters({"browser", "isLambdaTest", "isHeadless"})
	@BeforeMethod(description = "Load the homepage of the website")
	public void setup(@Optional("chrome")String browser,
			@Optional("false")boolean isLambdaTest,
			@Optional("false")boolean isHeadless, ITestResult result) throws MalformedURLException {
		WebDriver lambdaDriver;
        this.isLambdaTest=isLambdaTest;
        
        
		if (isLambdaTest) {
			lambdaDriver=	LambdaTestUtility.initializeLambdaTestSession(browser,result.getMethod().getMethodName());
			homePage = new HomePage(lambdaDriver);
		} else

		{
			logger.info("Load the homepage of the website");
			logger.info("Loads the homepage of the website");
			homePage = new HomePage(BROWSER.valueOf(browser.toUpperCase()), isHeadless);

		}
	}

	public BrowserUtility getInstance() {
		return homePage;
	}

	public void tearDown() {
		
		if(isLambdaTest) {
			
			LambdaTestUtility.quitSession();//quit or close the browser session on Lambda Test
		}
		else {
			homePage.quit();
		}
	}
}
