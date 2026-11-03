package com.qa.retail.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.retail.base.BaseTest;
import com.qa.retail.constants.AppConstants;

public class AccountsPageTest extends BaseTest{

	
	@BeforeClass
	public void accsetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test 
	public void accPageTitleTest() {
		// String actTitle = accPage.getAccPageTitle();
		// Assert.assertEquals(actTitle, "My Account");
		Assert.assertEquals(accPage.getAccPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test 
	public void accPageURLTest() {
		Assert.assertTrue(accPage.getAccPageURL().contains(AppConstants.ACC_PAGE_URL_FRACTION));
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void isSearchFieldExistTest() {
		Assert.assertTrue(accPage.isSearchFieldExist());
	}
	
	@Test 
	public void accPageHeadersCountTest() {
		Assert.assertEquals(accPage.getAccountsPageHeaders().size(), AppConstants.ACC_PAGE_HEADERS_COUNT);
	}
	
	@Test 
	public void accPageHeadersTest() {
		List<String> actAccPageHeadersList = accPage.getAccountsPageHeaders();
		System.out.println(actAccPageHeadersList);
		Assert.assertEquals(actAccPageHeadersList, AppConstants.ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	@Test
	public void searchTest() {
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProducts("MacBook Pro");
		String actProductHeader = productInfoPage.getProductHeaderName(); // TDD
		Assert.assertEquals(actProductHeader, "MacBook Pro");
	}
}
