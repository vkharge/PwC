package com.pwc.digipulse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.pwc.digipulse.core.SeleniumDriver;
import com.pwc.digipulse.pages.DigiPulseApp;
import com.pwc.digipulse.pages.contactus.ContactUsPage;
import com.pwc.digipulse.pages.home.HomePage;
import com.pwc.digipulse.pages.searchresult.SearchResult;

public class TestScenarios {
	
	/* Test Code for POM
	 * 
	 * Please ignore this code
	 * 
	 * 
	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private JSONObject sutConfig;
	private JSONObject driverConfig;
	private WebDriver driver;


	@BeforeSuite
	public void beforSuite() {
		JSONObject config;
		
		Path fileName = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "local.json");
		try {
	        
	        String contents = new String(Files.readAllBytes(fileName));
            config = new JSONObject(contents);
            System.out.println(config);
		}  catch (Exception e) {
			log.log(Level.SEVERE, "Error occured during readin a json file '" + fileName);
			throw new RuntimeException("Error occured during readin a json file '" + fileName + "'\n" + e.getMessage());
        } 
		
		
		sutConfig = config.getJSONObject("sut");
		driverConfig =  config.getJSONObject("driver");
				 
		
	}
	
	
	@Test
	public void testFeaturedArticles() {
		WebDriver driver = SeleniumDriver.getInstance(driverConfig);
		DigiPulseApp app = new DigiPulseApp(driver, sutConfig);
				
		HomePage homePage = app.launch();
		List<String> carousel = homePage.carousel().getFeaturedArticles();
		
		for(String article : carousel) {
			System.out.println(article);
		}
		
		app.close();
		
		
	}

	@Test
	public void testContactUs() {
	
		WebDriver driver = SeleniumDriver.getInstance(driverConfig);
		DigiPulseApp app = new DigiPulseApp(driver, sutConfig);
				
		HomePage homePage = app.launch();
		ContactUsPage contactPage = homePage.header().hamMenu().goToContactUsPage();
		
		System.out.println(contactPage.getPageLabel());
		
		Map<String, Map<String, String>> contactData = contactPage.contactOptions().getContactOptions();
		
		for(String key: contactData.keySet()) {
			System.out.println(key);
			System.out.println(contactData.get(key));

		}
		
		app.close();
	}
	
	
	
	@Test
	public void testSearch() {
		WebDriver driver = SeleniumDriver.getInstance(driverConfig);
		DigiPulseApp app = new DigiPulseApp(driver, sutConfig);
				
		HomePage homePage = app.launch();
		SearchResult searchResult = homePage.header().searchMenu().searchText("Single");
		
		List<Map<String, String>> searchData = searchResult.getSearchResults();
		
		
		for(Map<String, String> post: searchData) {
			System.out.println(post);
		}
		
		app.close();
	}
	
	*/
}
