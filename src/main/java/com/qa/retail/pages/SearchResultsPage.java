package com.qa.retail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.retail.constants.AppConstants;
import com.qa.retail.utils.ElementUtils;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtils eleUtil;
	
	// Cons ...
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
	}
	
	// Page Actions
	public ProductInfoPage selectProducts(String productName) {
		eleUtil.waitForVisibilityOfElement(By.linkText(productName), AppConstants.LONG_DEFAULT_WAIT).click();
		
		return new ProductInfoPage(driver); // TDD
	}
	
	
}
