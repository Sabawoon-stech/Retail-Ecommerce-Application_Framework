package com.qa.retail.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.retail.base.BaseTest;
import com.qa.retail.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 119: Retail Login Page Feature")
@Story("User Story 101: Registration Flow")
@Story("User Story 102: Front End UI Style")
@Feature("F12: Login Page main Feature")
public class LoginPageTest extends BaseTest {

	@Description("Login Page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actPageTitle = loginPage.getLoginPageTitle();
		ChainTestListener.log("Actual page title is: " + actPageTitle);
		Assert.assertEquals(actPageTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
  
	@Description("Login Page URL Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void loginPageURLTest() {
		String actPageURL = loginPage.getLoginPageURL();
		ChainTestListener.log("Actual page URL is: " + actPageURL);
		Assert.assertTrue(actPageURL.contains(AppConstants.LOGIN_PAGE_URL_FRACTION));
		// ChainTestListener.embed(new File("C:/Users/DMVTEK/Downloads/Listener.png"), "image/png");
	}

	@Description("Verifying Forgot Password Link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void forgotPwdLinkExistTest() {
		ChainTestListener.log("Checking if the forgot password link exist or not");
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
		// ChainTestListener.embed(new File("C:/Users/DMVTEK/Downloads/Listener.png"), "image/png");
	}

	@Description("Verifying Application Logo exist Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void appLogoExistTest() {
		ChainTestListener.log("Checking if the Application logo exist or not");
		Assert.assertTrue(loginPage.isLogoExist());
		// ChainTestListener.embed(new File("C:/Users/DMVTEK/Downloads/Listener.png"), "image/png");
	}

	@Description("Verifying that User is able to login with correct credentials test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

}
