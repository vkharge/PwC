package com.pwc.digipulse.pages.common;

import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends Component {

	private HamMenu hamMenu;
	private SearchMenu searchMenu;

	@FindBy(css = "header.container.header i.btr.bt-bars.trigger")
	private WebElement hamburgerIcon;

	@FindBy(css = "header.container.header i.btr.bt-search")
	private WebElement searchIcon;

	public Header(WebDriver driver) {
		super(driver);
	}

	@Override
	public void waitForVisible() {
		logger.log(Level.INFO, "Wait for visibility of Header");
		System.out.println(driver);
		System.out.println(searchIcon);

		wait.waitForVisibiltyOfElement(searchIcon);
		wait.waitForVisibiltyOfElement(hamburgerIcon);
	}

	@Override
	public boolean isDisplayed() {
		logger.log(Level.INFO, "Check if header is displayed");
		return hamburgerIcon.isDisplayed() & searchIcon.isDisplayed();
	}

	public HamMenu hamMenu() {
		logger.log(Level.INFO, "Click on hamburger menu");
		wait.waitForElementToBeClickable(hamburgerIcon).click();
		return hamMenu == null ? new HamMenu(driver) : hamMenu;
	}

	public SearchMenu searchMenu() {
		logger.log(Level.INFO, "Click on search menu");
		wait.waitForElementToBeClickable(searchIcon).click();
		return searchMenu == null ? new SearchMenu(driver) : searchMenu;
	}
	
	

}
