package com.pwc.digipulse.pages.common;

import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pwc.digipulse.pages.contactus.ContactUsPage;

public class HamMenu extends Component {

	@FindBy(xpath = "//a[text()='Contact us']")
	private WebElement contactUsLink;


	public HamMenu(WebDriver driver) {
		super(driver);
	}

	@Override
	protected void waitForVisible() {
		logger.log(Level.INFO, "Wait for visibility of HamMenu component");
		wait.waitForVisibiltyOfElement(contactUsLink);
	}

	@Override
	public boolean isDisplayed() {
		logger.log(Level.INFO, "Check if HamMenu is displayed");
		return contactUsLink.isDisplayed();
	}

	public ContactUsPage goToContactUsPage() {
		logger.log(Level.INFO, "Navigate to Contact Us Page");
		wait.waitForElementToBeClickable(contactUsLink).click();
		return new ContactUsPage(driver);
	}
	
	

}
