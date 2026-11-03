package com.qa.retail.tests;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.retail.base.BaseTest;
import com.qa.retail.constants.AppConstants;
import com.qa.retail.utils.ExcelUtils;

public class ProductResutlsPageTest extends BaseTest{

	@BeforeClass
	public void productInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	/*				Data provider
	 	1: DP lets one test method run multiple times with different input data -- Data-Driven Approaches
	 	2: In a DP, we use the "@DataProvider" annotation
	 	3: This annotation returns a two-dimensional object arrays "Object[][]"
	 */
	
//	@DataProvider
//	public Object[][] getSearchExcelTestData() {
//		return ExcelUtils.getTestData(AppConstants.PRODUCT_DATA_SHEET_NAME);
//	}
//	
//	@Test(dataProvider = "getSearchExcelTestData")
//	public void productImagesTest(String searchKey, String productName, String imageCount) {
//		searchResultsPage = accPage.doSearch(searchKey);
//		productInfoPage= searchResultsPage.selectProducts(productName);
//		Assert.assertEquals(String.valueOf(productInfoPage.getProductImagesCount()), imageCount);
//	}
	
	// byte, short, int, long, char, boolean, float, double --> Primitives ==> String.valueOf()
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", 4},
			{"MacBook", "MacBook Air", 4},
			{"iMac", "iMac", 3},
			{"Samsung", "Samsung SyncMaster 941BW", 1}
		};
	}
	
	@Test(dataProvider = "getSearchData")
	public void productImagesTest(String searchKey, String productName, int imageCount) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage= searchResultsPage.selectProducts(productName);
		Assert.assertEquals(productInfoPage.getProductImagesCount(), imageCount);
	}
	
//	@Test (priority = 1)
//	public void productImagesTest() {
//		searchResultsPage = accPage.doSearch("MacBook");
//		productInfoPage= searchResultsPage.selectProducts("MacBook Pro");
//		Assert.assertEquals(productInfoPage.getProductImagesCount(), 4);
//	}
	
//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
//$2,000.00
//Ex Tax: $2,000.00
	@Test(priority = 2)
	public void productInfoTest() {
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProducts("MacBook Pro");
		Map<String, String> productDetailsMap = productInfoPage.getProductDetail();
		
		// Hard Assertions
//		Assert.assertEquals(productDetailsMap.get("Brand"), "Apple");
//		Assert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
//		Assert.assertEquals(productDetailsMap.get("Reward Points"), "800");
//		Assert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
//		Assert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
//		Assert.assertEquals(productDetailsMap.get("External Tax Price"), "$2,000.00");
		
		// Soft Assertion
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "800");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productDetailsMap.get("price"), "$2,000.00");
		softAssert.assertEquals(productDetailsMap.get("External Tax Price"), "$2,000.00");
		
		softAssert.assertAll(); // Failed assertion
	}
}
