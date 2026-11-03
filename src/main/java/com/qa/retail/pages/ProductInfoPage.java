package com.qa.retail.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.retail.constants.AppConstants;
import com.qa.retail.utils.ElementUtils;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtils eleUtil;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtils(this.driver);
	}

	// Locator
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("div.image.magnific-popup img");
	private By productMetaData = By
			.xpath("(//div[@class='col-sm']//descendant::ul[@class='list-unstyled'])[1]//child::li");
	private By proructPriceData = By
			.xpath("(//div[@class='col-sm']//descendant::ul[@class='list-unstyled'])[2]//child::li");

	// Actions
	// M: 01
	public String getProductHeaderName() {
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println("The product header is: " + productHeaderVal);
		return productHeaderVal;
	}

	// M: 02 -- product images count
	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForVisibilityOfElements(productImages, AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("Product: " + getProductHeaderName() + " images count: " + imagesCount);
		return imagesCount;
	}

	private Map<String, String> productMap = new HashMap<String, String>();

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.waitForVisibilityOfElements(productMetaData,
				AppConstants.MEDIUM_DEFAULT_WAIT);
		for (WebElement e : metaDataList) {
			String metaData = e.getText();
			String metaKey = metaData.split(":")[0].trim();
			String metaValue = metaData.split(":")[1].trim();
			// Fill the Map productMap
			productMap.put(metaKey, metaValue);
		}
	}

//$2,000.00
//Ex Tax: $2,000.00

	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.waitForVisibilityOfElements(proructPriceData,
				AppConstants.MEDIUM_DEFAULT_WAIT);
		String productPrice = metaPriceList.get(0).getText();
		String productExTaxPrice = metaPriceList.get(1).getText().split(":")[1].trim(); // $2,000.00
		
		productMap.put("price", productPrice);
		productMap.put("External Tax Price", productExTaxPrice);
	}
	
	public Map<String, String> getProductDetail() {
		productMap.put("Product Name: ", getProductHeaderName());
		getProductMetaData();
		getProductPriceData();
		System.out.println(productMap);
		return productMap;
	}
	
}
