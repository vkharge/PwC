package com.pwc.digipulse.pages.home;

import org.openqa.selenium.WebDriver;

import com.pwc.digipulse.pages.common.Header;
import com.pwc.digipulse.pages.common.Page;

public class HomePage extends Page{

	private Header header;
	private Carousel carousel;

	public HomePage(WebDriver driver) {
		super(driver);
		waitForPageToLoad();
	}

	public void waitForPageToLoad() {
		header = new Header(driver);
	}

	public Header header() {
		if (header == null) {
			header = new Header(driver);
		}
		return header;
	}

	public Carousel carousel() {
		if (carousel == null) {
			carousel = new Carousel(driver);
		}
		return carousel;
	}
	
}
