package com.pwc.digipulse.steps;

import java.util.logging.Logger;

import com.pwc.digipulse.core.Base;
import com.pwc.digipulse.pages.home.HomePage;

import io.cucumber.java.en.Given;

public class CommonSteps {

	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private SharedContext context;
	
	public CommonSteps(SharedContext context) {
		this.context = context;
	}
	
	@Given("I navigate to the PwC Digital Pulse website")
	public void nagivateToHomePage() {		
		context.homePage = Base.getApp().launch();
	}
}
