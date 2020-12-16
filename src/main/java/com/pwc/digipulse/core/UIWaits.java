package com.pwc.digipulse.core;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIWaits {

	private long TIMEOUT = 10;
	private long POLLTIME = 500;

	private WebDriver driver;
	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);;

	public UIWaits(WebDriver driver) {
		this.driver = driver;
	}

	public UIWaits(WebDriver driver, long timeout, long pollTime) {
		this(driver);
		this.TIMEOUT = timeout;
		this.POLLTIME = pollTime;
	}

	public WebElement waitForVisibiltyOfElement(WebElement element) {
		log.log(Level.INFO,
				"Wait for the visibilty of element : " + element.getTagName() + " -> " + element.toString());

		try {
			WebElement webElement = new WebDriverWait(driver, TIMEOUT, POLLTIME)
					.until(ExpectedConditions.visibilityOf(element));
			log.log(Level.INFO, "WebElement " + element + " is visible on the page.");
			return webElement;

		} catch (TimeoutException e) {
			log.log(Level.WARNING, "WebElement is not visible on the page. Waited for '" + TIMEOUT + "' seconds.");
			throw e;

		}
	}

	public WebElement waitForElementToBeClickable(WebElement element) {
		log.log(Level.INFO,
				"Wait for the element to be clickable : " + element.getTagName() + " -> " + element.toString());

		try {
			WebElement webElement = new WebDriverWait(driver, TIMEOUT, POLLTIME)
					.until(ExpectedConditions.elementToBeClickable(element));
			log.log(Level.INFO, "WebElement " + element + " is clickable.");
			return webElement;

		} catch (TimeoutException e) {
			log.log(Level.WARNING, "WebElement is not clickable on the page. Waited for '" + TIMEOUT + "' seconds.");
			throw e;

		}
	}

}
