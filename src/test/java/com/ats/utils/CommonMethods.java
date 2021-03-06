package com.ats.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ats.testbase.PageInitializer;

public class CommonMethods extends PageInitializer {

	/**
	 * 
	 * @param element
	 * @param text
	 */
	public static void sendText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	
	/**
	 * Method that checks if text is there and then selects it
	 *
	 * @param element
	 * @param textToSelect
	 * 
	 *                     public static void clickRadioChecbox(List<WebElement>
	 *                     radioOrcheckbox, String value) {
	 * 
	 *                     String actualValue; for (WebElement el : radioOrcheckbox)
	 *                     { actualValue = el.getAttribute("value").trim(); if
	 *                     (el.isEnabled() && actualValue.equals(value)) {
	 *                     el.click(); break; } } }
	 * 
	 * 
	 */
	public static void selectDDValue(WebElement element, String text) {
		try {
			Select select = new Select(element);
			List<WebElement> options = select.getOptions();
			for (WebElement el : options) {
				if (el.getText().equals(text)) {
					select.selectByVisibleText(text);
					break;
				}
			}
		} catch (UnexpectedTagNameException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that selects value by index
	 *
	 * @param element
	 * @param index
	 */
	public static void selectDDValue(WebElement element, int index) {
		try {
			Select select = new Select(element);
			int size = select.getOptions().size();

			if (size > index) {
				select.selectByIndex(index);
			}
		} catch (UnexpectedTagNameException e) {
			e.printStackTrace();
		}
	}

	public static void acceptAlert() {

		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}

	}

	public static void dismissAlert() {

		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}

	public static String getAlertTextFromPOPUP() {
		String alertTextFromPOPUP = null;
		try {
			Alert alert = driver.switchTo().alert();
			alertTextFromPOPUP = alert.getText();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}

		return alertTextFromPOPUP;
	}

	public static void sendAlertTextTOPOPUP(String text) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(text);
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}

	public static void switchToFrame(String nameOrID) {
		try {
			driver.switchTo().frame(nameOrID);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	public static void switchToFrame(WebElement element) {
		try {
			driver.switchTo().frame(element);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	public static void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	public static void switchToChildWindow() {
		// get window page id
		String ParentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();
		for (String window : windows) {
			if (!window.equals(ParentWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

	}
	
	/**
	 * only one WebElement 
	 * @return
	 */
	public static WebDriverWait getWaitObject() {
		
		WebDriverWait wait =new WebDriverWait(driver, Constants.EXPLICIT_WAIT_TIME);
		return wait;
		
	}
	
	public static WebElement waitForClickability(WebElement element) {
		
		return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static WebElement waitForVisibility(WebElement element ) {
		return getWaitObject().until(ExpectedConditions.visibilityOf(element));
	}
	
    public static void click(WebElement element) {
    	waitForClickability(element);
    }
    
    
    
    
    public static JavascriptExecutor getJavascriptExecuterObject() {
    	JavascriptExecutor js=(JavascriptExecutor)driver;
    	return js;
    }
    
    public static void JavascriptClick(WebElement element) {
    	getJavascriptExecuterObject().executeScript("arguments[0].click();", element);
    }
    
    public static void scrollToElement(WebElement element) {
    	getJavascriptExecuterObject().executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    
    public static void  scrollDown (int pixel) {
    	getJavascriptExecuterObject().executeScript("window.scrollBy(0," + pixel + ")");
    }    
    
    public static void  scrollUp (int pixel) {
    	getJavascriptExecuterObject().executeScript("window.scrollBy(0,-" + pixel + ")");
    }    
    
    
    
    
    
    
    
    
    
    
    
	/**
	 * This method will take a screenshot
	 */

	public static byte[] takeScreenshot(String filename) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		byte[] pictureBytes = ts.getScreenshotAs(OutputType.BYTES);

		File file = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = Constants.SCREENSHOT_FILEPATH + filename + getTimeStemp() + ".png";

		try {
			FileUtils.copyFile(file, new File(destinationFile));
		} catch (Exception e) {
			System.out.println("Cannot take screenshot");
		}
		return pictureBytes;
	}

	public static String getTimeStemp() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		return sdf.format(date.getTime());
	}

	public static void wait(int second) {

		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
