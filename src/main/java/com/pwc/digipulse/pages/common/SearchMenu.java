package com.pwc.digipulse.pages.common;

import java.util.logging.Level;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pwc.digipulse.pages.searchresult.SearchPage;

public class SearchMenu extends Component{

	
	@FindBy(id = "search-input")
	private WebElement searchInput;
	
	public SearchMenu(WebDriver driver) {
		super(driver);
	}

	@Override
	protected void waitForVisible() {
		logger.log(Level.INFO, "Wait for visibility of search component");
		wait.waitForVisibiltyOfElement(searchInput);
	}

	@Override
	public boolean isDisplayed() {
		logger.log(Level.INFO, "Check if search is displayed");
		return searchInput.isDisplayed();
	}

	public SearchMenu searchText(String text) {
		logger.log(Level.INFO, "Enter the search text");
		wait.waitForElementToBeClickable(searchInput).sendKeys(text);
		return this;
	}

	public SearchPage submitSearch() {
		logger.log(Level.INFO, "Submit the search");
		wait.waitForElementToBeClickable(searchInput).sendKeys(Keys.ENTER);;
		return new SearchPage(driver);
	}
}
