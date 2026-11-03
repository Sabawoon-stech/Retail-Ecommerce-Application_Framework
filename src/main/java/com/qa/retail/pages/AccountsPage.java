package com.qa.retail.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.retail.constants.AppConstants;
import com.qa.retail.utils.ElementUtils;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtils eleUtil;
	
	// Locator
	private By logoutLink = By.xpath("//a[text()='Logout' and @class='list-group-item']");
	private By search = By.name("search");
	private By accHeaders = By.cssSelector("div#content > h1, div#content > h2"); // 4  
	private By searchIcon = By.xpath("(//button[@type='submit'])[1]");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;  
		eleUtil = new ElementUtils(this.driver);
	}
	
	// Page behaviors/actions/methods
	
	public String getAccPageTitle() {
		String actPageTitle =  eleUtil.waitForTitleCompleteValue(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.MEDIUM_DEFAULT_WAIT);
		System.out.println("Account Page title is: " + actPageTitle);
		return actPageTitle;
	}
	
	public String getAccPageURL() {
		String actPageURL = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, AppConstants.MEDIUM_DEFAULT_WAIT);
		System.out.println(actPageURL);
		return actPageURL;
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForVisibilityOfElement(logoutLink, AppConstants.MEDIUM_DEFAULT_WAIT).isDisplayed();
	}
	
	public void logout() {
		if(isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}
	}
	
	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(search, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public List<String> getAccountsPageHeaders() {
		List<WebElement> headersList = eleUtil.waitForVisibilityOfElements(accHeaders, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> headersValList = new ArrayList<>();
		for(WebElement e : headersList) {
			String text = e.getText();
			headersValList.add(text);
		}
		return headersValList;
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.waitForVisibilityOfElement(search, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(search, AppConstants.LONG_DEFAULT_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchIcon);
		return new SearchResultsPage(driver); // TDD
	}
	
	/*				TDD -- Test-Driven Development
	 	1: TDD is a software development approach -- where tests are written before the actual code is written
	 
	 */
	
	
	
}
