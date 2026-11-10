package com.qa.retail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.retail.constants.AppConstants;
import com.qa.retail.utils.ElementUtils;

import io.qameta.allure.Step;

public class LoginPage {

	// 1: private webDriver driver reference
	private WebDriver driver;
	private ElementUtils eleUtil;

	// 2: Locators/ repository / page object repository
	// L should be private -- access them public methods -- encapsulation
	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//button[text()='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img.img-fluid");
	
	private By registerLink = By.linkText("Register");

	// 3: Page Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
	}
 
	// 4: Page actions/behavior/Methods
	@Step("Getting Login Page Title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleCompleteValue(AppConstants.LOGIN_PAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login Page title is: " + title);
		return title;
	}
	
	@Step("Getting Login Page URL")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login Page URL is: " + url);
		return url;
	}

	@Step("Checking Forgot Password Link exist")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	@Step("Checking Application Logo exist")
	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}

	@Step("User name is: *** and the user password is: ****")
	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("Credentials are: " + username + " : " + pwd); // Never expose your credentials
		eleUtil.doSendKeysWithWait(userName, username, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new AccountsPage(driver);
	}
	
	@Step("Navigating to the register page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstants.MEDIUM_DEFAULT_WAIT).click();
		return new RegisterPage(driver);
	}
	
	

}
