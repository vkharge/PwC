package com.pwc.digipulse.steps;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomeSteps {

	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private SharedContext context;
	
	public HomeSteps(SharedContext context) {
		this.context = context;
	}
	
	@When("I am viewing the {string} page")
	public void verifyHomePage(String pageName) {
		log.log(Level.INFO, "Verify " + pageName + " is displayed");
		boolean isDisplayed = context.homePage.carousel().isDisplayed();
		Assert.assertEquals(isDisplayed, true, "Home Page is not displayed");
	}

	@Then("I am presented with a carousel displaying {int} featured articles")
	public void verifyCarouselDisplayingFeaturedArticles(int noOfArticles) {
		
		log.log(Level.INFO, "Verify carousel displaying are showing " + noOfArticles + " featured articles");
		List<String> featuredArticles = context.homePage.carousel().getFeaturedArticles();
		 
		int actualNoOfArticles = featuredArticles.size();
		Assert.assertEquals(actualNoOfArticles, noOfArticles, "No of featured articles are not matching");
		
		context.sharedData.put("articles", featuredArticles);
		
	}

	@Then("Clicking the {string} button on the carousel will load the next {int} featured articles")
	public void clickOnNextButtonAndVerifyFeaturedArticles(String action, int noOfArticles) {
	
		context.homePage.carousel().next();
		
		log.log(Level.INFO, "Verify carousel is displaying next " + noOfArticles + " featured articles");
		List<String> featuredArticles = context.homePage.carousel().getFeaturedArticles();
	
		int actualNoOfArticles = featuredArticles.size();
		Assert.assertEquals(actualNoOfArticles, noOfArticles, "No of featured articles are not matching");
		

		log.log(Level.INFO, "Verify current featured articles names are not same as previous articles");
		List<String> prvFeaturedArticles = (List<String>) context.sharedData.get("articles");
		
		Set<String> prvArticles = new HashSet<>(prvFeaturedArticles);
		Set<String> curArticles = new HashSet<>(featuredArticles);
		
		log.log(Level.INFO, "Previous articles Contents: \n" + prvArticles);
		log.log(Level.INFO, "Current articles Contents: \n" + curArticles);

		Assert.assertEquals(Collections.disjoint(prvArticles, curArticles), true, "Current and previous articles are same");
		
		
	}

	@Then("Clicking the {string} button on the carousel will load the previous {int} featured articles")
	public void clickOnPreviosButtonAndVerifyFeaturedArticles(String string, int noOfArticles) {

		context.homePage.carousel().previous();
		
		log.log(Level.INFO, "Verify carousel is displaying next " + noOfArticles + " featured articles");
		List<String> featuredArticles = context.homePage.carousel().getFeaturedArticles();
	
		int actualNoOfArticles = featuredArticles.size();
		Assert.assertEquals(actualNoOfArticles, noOfArticles, "No of featured articles are not matching");
		

		log.log(Level.INFO, "Verify current featured articles names");
		List<String> prvFeaturedArticles = (List<String>) context.sharedData.get("articles");
		
		Set<String> oldArticles = new HashSet<>(prvFeaturedArticles);
		Set<String> curArticles = new HashSet<>(featuredArticles);
		
		log.log(Level.INFO, "Old articles Contents: \n" + oldArticles);
		log.log(Level.INFO, "Current articles Contents: \n" + curArticles);

		Assert.assertEquals(oldArticles.equals(curArticles), true, "Current and Old articles are not same");
				
		
	}

}
