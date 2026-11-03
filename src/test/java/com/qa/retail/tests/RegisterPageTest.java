package com.qa.retail.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.retail.base.BaseTest;
import com.qa.retail.constants.AppConstants;
import com.qa.retail.utils.ExcelUtils;

public class RegisterPageTest extends BaseTest{

	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmalID(String userFirstName) {
		 return userFirstName + "dmv" + System.currentTimeMillis() + "@gmail.com";
		// UUID Class
		// return "Selenium" + UUID.randomUUID() + "@gmail.com";
	}
	
//	@DataProvider
//	public Object[][] getUserRegData() {
//		return new Object[][] {
//			{"Dave", "Smith", getRandomEmalID("Dave"), "Test123"},
//			{"John", "Bruce", getRandomEmalID("John"), "Test123"},
//			{"Sarah", "Mcternan", getRandomEmalID("Sarah"), "Test123"},
//			{"Brishna", "Nazari", getRandomEmalID("Brishna"), "Test123"},
//			{"AM", "AM", getRandomEmalID("AM"), "Test123"},
//			{"Ezat", "Masiha", getRandomEmalID("Ezat"), "Test123"},
//			{"Sahar", "Sahar", getRandomEmalID("Sahar"), "Test123"}
//		};
//	}
//	
//	@Test(dataProvider = "getUserRegData")
//	public void userRegTest(String firstName, String lastName, String email, String password) {
//		boolean isRegDone = registerPage.userRegistration(firstName, lastName, getRandomEmalID(firstName), password);
//		Assert.assertTrue(isRegDone);
//	}
	
//	@Test
//	public void userRegTest() {
//		boolean isRegDone = registerPage.userRegistration("Dave", "Smith", getRandomEmalID("Dave"), "Test123");
//		Assert.assertTrue(isRegDone);
//	}
	
	/*							Reading Excel Data
	 	1: Selenium + testNG + Java -- they cannot directly read the data form the excel
	 		Solution: 3rd party library --> Apache POI Download -- Update the Maven Project
	 	2: Libraries
	 		POI-OOXML
		 		POI
		 		POI-OOXML-Schemas
		 		POI-Scratchpad
		 		OOXML-Schemas
		 		OpenXML4J
	 */
	
	@DataProvider
	public Object[][] getUserRegTestExcelData() {
		Object[][] regData = ExcelUtils.getTestData(AppConstants.REGISTRATION_DATA_SHEET_NAME);
		return regData;
	}
	
	@Test(dataProvider = "getUserRegTestExcelData")
	public void userRegTest(String firstName, String lastName, String password) {
		boolean isRegDone = registerPage.userRegistration(firstName, lastName, getRandomEmalID(firstName), password);
		Assert.assertTrue(isRegDone);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
