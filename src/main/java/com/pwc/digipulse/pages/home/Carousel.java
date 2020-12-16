package com.pwc.digipulse.pages.home;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.pwc.digipulse.pages.common.Component;

public class Carousel extends Component {

	@FindBy(css = "div#banners a.flex-next")
	private WebElement nextBtn;

	@FindBy(css = "div#banners a.flex-prev")
	private WebElement prevBtn;

	@FindBy(css = "div#banners li.flex-active-slide")
	private WebElement activeSlide;
	
	@FindBy(css="div#banners li.flex-active-slide > div")
	private List<WebElement> featuredArticles;
	
	private By articleText = By.cssSelector(".inner h2");

	public Carousel(WebDriver driver) {
		super(driver);
	}

	@Override
	public void waitForVisible() {
		logger.log(Level.INFO, "Wait for visibility of carousel");
		wait.waitForVisibiltyOfElement(activeSlide);
	}

	@Override
	public boolean isDisplayed() {
		logger.log(Level.INFO, "Check if carousel is displayed");
		return activeSlide.isDisplayed();
	}

	public void next() {
		logger.log(Level.INFO, "Click on the next button");
		wait.waitForElementToBeClickable(nextBtn).click();
		sleep(1000);
	}

	public void previous() {
		logger.log(Level.INFO, "Click on the previous button");
		wait.waitForElementToBeClickable(prevBtn).click();
		sleep(1000);
		
	}

	public List<String> getFeaturedArticles(){
		 List<String> articles = new ArrayList<>();
		 
		for(WebElement article: featuredArticles) {
			String articleName = article.findElement(articleText).getText();
			articles.add(articleName);
		}
		
		return articles;
		 
	}
	
	private void sleep(long milli){
		try {
			Thread.sleep(milli);
		} catch (InterruptedException e) {
		}
	}

}
