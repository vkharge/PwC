package com.pwc.digipulse.pages;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import com.pwc.digipulse.pages.home.HomePage;

public class DigiPulseApp {

	private WebDriver driver;
	private JSONObject sutConfig;
	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public DigiPulseApp(WebDriver driver, JSONObject sutConfig) {
		this.driver = driver;
		this.sutConfig = sutConfig;
	}

	public HomePage launch() {
		String url = sutConfig.getString("url");
		log.log(Level.INFO, "Launching Application : " + url);
		driver.get(url);
		return new HomePage(driver);
	}

	public void quit() {
		log.log(Level.INFO, "Quiting Application");
		if (driver != null) {
			driver.quit();
		}

	}

	public void close() {
		log.log(Level.INFO, "Closing Application");
		if (driver != null) {
			driver.close();
		}
	}
}
