package com.pwc.digipulse.steps;

import java.util.HashMap;
import java.util.Map;

import com.pwc.digipulse.pages.contactus.ContactUsPage;
import com.pwc.digipulse.pages.home.HomePage;
import com.pwc.digipulse.pages.searchresult.SearchPage;

public class SharedContext {
	
	public HomePage homePage;
	public ContactUsPage contactUsPage;
	public SearchPage searchPage;
	public Map<String, Object> sharedData = new HashMap<>();
	
}
