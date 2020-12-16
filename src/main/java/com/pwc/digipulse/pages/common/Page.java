package com.pwc.digipulse.pages.common;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.pwc.digipulse.core.UIWaits;

public abstract class Page {
	
	protected WebDriver driver;
	protected UIWaits wait;
	protected Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	public Page(WebDriver driver) {
		this.driver = driver;
		this.wait = new UIWaits(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
	}
	
	public abstract void waitForPageToLoad();
	
}
