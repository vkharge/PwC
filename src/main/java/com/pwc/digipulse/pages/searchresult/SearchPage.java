package com.pwc.digipulse.pages.searchresult;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pwc.digipulse.pages.common.Header;
import com.pwc.digipulse.pages.common.Page;
import com.pwc.digipulse.pages.contactus.ContactOptions;

public class SearchPage extends Page {

	@FindBy(css = "section.container.intro div.inner")
	private WebElement headingLabel;

	private SearchResult searchResult;
	private Header header;


	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public void waitForPageToLoad() {
		wait.waitForVisibiltyOfElement(headingLabel);

	}

	public SearchResult searchResult() {
		if (searchResult == null) {
			searchResult = new SearchResult(driver);
		}
		return searchResult;
	}

	public String getSearchHeader() {
		return wait.waitForVisibiltyOfElement(headingLabel).getText();
	}
	
	public Header header() {
		if (header == null) {
			header = new Header(driver);
		}
		return header;
	}
}
