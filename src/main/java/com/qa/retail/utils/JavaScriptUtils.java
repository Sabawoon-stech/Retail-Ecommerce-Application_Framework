package com.qa.retail.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtils {

	private WebDriver driver; // null
	private JavascriptExecutor js;

	public JavaScriptUtils(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) this.driver;
	}

	public String getPageTitleWithJS() {
		String pageTitle = js.executeScript("return document.title").toString();
		System.out.println("Page title: " + pageTitle);
		return pageTitle;
	}

	public String getPageURLWithJS() {
		String pageurl = js.executeScript("return document.URL").toString();
		System.out.println("Page URL: " + pageurl);
		return pageurl;
	}

	public void generateJSAlert(String alertMessage) {
		js.executeScript("alert('" + alertMessage + "')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().alert().accept();
	}

	public void generateJSConfirm(String confirmMessage) {
		js.executeScript("confirm('" + confirmMessage + "')");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

		}
		driver.switchTo().alert().accept();
	}

	public void generateJSPrompt(String promptMessage, String valueToSend) {
		js.executeScript("prompt('" + promptMessage + "')");
		Alert alrt = driver.switchTo().alert();
		alrt.sendKeys(valueToSend);

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		alrt.accept();
	}

	public void goBackWithJS() {
		js.executeScript("history.go(-1)");
	}

	public void goForwardWithJS() {
		js.executeScript("history.go(1)");
	}

	public void pageRefreshWithJS() {
		js.executeScript("history.go(0)");
	}

	public String getPageInnterText() {
		return js.executeScript("return document.documentElement.innerText").toString();
	}

	public void doSendKeysWithJS(String locator, String valueToSend) {
		js.executeScript("document.getElementById('" + locator + "').value='" + valueToSend + "';");
	}

	public void scrollPageDown() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollPageDownWithMesures(String height) {
		js.executeScript("window.scrollTo(0, '" + height + "')");
	}

	public void scrollPageDownToTheMiddle(String height) {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/2)");
	}

	public void scrollPageUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}

	public void scrollIntoViewWithJS(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(false)", element);
	}

	public void zoomChromeEdgeSafari(String zoomPercentage) {
		js.executeScript("document.body.style.zoom='" + zoomPercentage + "%'");
	}

	public void zoomFirefox(String zoomPercentage) {
		js.executeScript("document.body.style.MozTransform='scale(" + zoomPercentage + "%)'");
	}

	public void drawborder(WebElement element) {
		js.executeScript("arguments[0].style.border = '2px solid red'", element);
	}

	public void clickElementWithJS(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}

}
