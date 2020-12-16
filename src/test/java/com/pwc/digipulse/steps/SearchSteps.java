package com.pwc.digipulse.steps;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;

import com.pwc.digipulse.pages.common.SearchMenu;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps {

	private SharedContext context;
	private SearchMenu searchMenu;
	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public SearchSteps(SharedContext context) {
		this.context = context;
	}

	@When("I click on the {string} icon to perform a search")
	public void clickOnSearchIcon(String iconName) {
		searchMenu = context.homePage.header().searchMenu();
	}

	@When("I enter the text {string}")
	public void enterSearchText(String searchText) {
		searchMenu.searchText(searchText);
	}

	@When("I submit the search")
	public void submitSearch() {
		context.searchPage = searchMenu.submitSearch();

	}

	@Then("I am taken to the search results page")
	public void verifySearchPage() {
		log.log(Level.INFO, "Verify search result page is displayed");
		String actPageLabel = context.searchPage.getSearchHeader();
		log.log(Level.INFO, "Search page label : " + actPageLabel);
		
		Assert.assertEquals(actPageLabel.contains("Showing search results for"), true, "Search result page is not displayed");

	
	}

	@Then("I am presented with at least {int} search result")
	public void verifyNoOfResultsShown(int noOfResults) {
		log.log(Level.INFO, "Verify atleast '" + noOfResults + "' number of search results are displayed");
		List<Map<String, String>> actResults = context.searchPage.searchResult().getSearchResults();

		log.log(Level.INFO, "Actual number of post found: " + actResults.size());

		if (actResults.size() < noOfResults) {
			log.log(Level.SEVERE, "Less number of posts are found on search result page");
			Assert.fail("Less number of posts are found on search result page");
		}
	}
}
