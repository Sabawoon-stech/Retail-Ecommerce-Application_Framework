package com.qa.retail.base;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.chaintest.service.ChainPluginService;
import com.qa.retail.factory.DriverFactory;
import com.qa.retail.listeners.TestAllureListener;
import com.qa.retail.pages.AccountsPage;
import com.qa.retail.pages.LoginPage;
import com.qa.retail.pages.ProductInfoPage;
import com.qa.retail.pages.RegisterPage;
import com.qa.retail.pages.SearchResultsPage;

// @Listeners(ChainTestListener.class)
@Listeners(TestAllureListener.class)
public class BaseTest {
	
	protected WebDriver driver;
	DriverFactory df;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected Properties prop;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softAssert;
	protected RegisterPage registerPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) { 
		
		// ChainTest Custom System Information
		ChainPluginService.getInstance().addSystemInfo("Builds", "1.0");
		ChainPluginService.getInstance().addSystemInfo("Owner Name", "Sabawoon Yuonus");
		ChainPluginService.getInstance().addSystemInfo("Sr. Tester", "Brishna Nazari");
		ChainPluginService.getInstance().addSystemInfo("Manager", "Mohadese");
		ChainPluginService.getInstance().addSystemInfo("Team Lead", "Ezat");
		
		df = new DriverFactory();
		prop = df.initProp();
		 // If a browser parameter is provided from the TestNG XML, it will override the browser value from the config.properties file
		if(browserName != null) {
			prop.setProperty("browser", browserName);
		}
		
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterMethod
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) {
			 takeScreenshot();
			ChainTestListener.embed(takeScreenshot(), "image/png");
		}
	}
	
	public byte[] takeScreenshot() {
		return ((TakesScreenshot)(driver)).getScreenshotAs(OutputType.BYTES);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	

}
