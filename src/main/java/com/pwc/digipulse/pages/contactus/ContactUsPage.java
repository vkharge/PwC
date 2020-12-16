package com.pwc.digipulse.pages.contactus;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pwc.digipulse.pages.common.Header;
import com.pwc.digipulse.pages.common.Page;
import com.pwc.digipulse.pages.home.Carousel;

public class ContactUsPage extends Page{


	@FindBy(css="section.container.intro h1")
	private WebElement headingLabel;
	
	private ContactOptions contactOptions;
	private Header header;

	public ContactUsPage(WebDriver driver) {
		super(driver);
		waitForPageToLoad();
	}

	@Override
	public void waitForPageToLoad() {
		contactOptions = new ContactOptions(driver);
	}
	
	public String getPageLabel() {
		return wait.waitForVisibiltyOfElement(headingLabel).getText();
	}

	public ContactOptions contactOptions(){
		if (contactOptions == null) {
			contactOptions = new ContactOptions(driver);
		}
		return contactOptions;
	}		

	public Header header() {
		if (header == null) {
			header = new Header(driver);
		}
		return header;
	}

}
