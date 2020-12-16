package com.pwc.digipulse.runner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.pwc.digipulse.core.Base;
import com.pwc.digipulse.core.ReaderUtils;
import com.pwc.digipulse.core.SeleniumDriver;
import com.pwc.digipulse.pages.DigiPulseApp;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features={"src/test/java/com/pwc/digipulse/features"},
		glue={"com.pwc.digipulse.steps", "com.pwc.digipulse.hooks"},
		dryRun=false,
		monochrome=true,
		plugin = {
				"pretty",
                "html:target/cucumber-reports/basic/basic.html",
                "json:target/cucumber-reports/cucumber.json",
		}
)

public class TestRunner extends AbstractTestNGCucumberTests {

	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@BeforeSuite
	public void beforeSuite() {

		log.log(Level.INFO, "SUITE EXECUTION STARTED");

		log.log(Level.INFO, "Read the config data");
		Path fileName = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "local.json");
		JSONObject config = ReaderUtils.readJSONFile(fileName);
		JSONObject sutConfig = (JSONObject) config.get("sut");
		;
		JSONObject driverConfig = (JSONObject) config.get("driver");

		log.log(Level.INFO, "Set the config data");
		Base.setConfig(config);
		Base.setDriverConfig(driverConfig);
		Base.setSutConfig(sutConfig);
	}

	@AfterSuite
	public void afterSuite() {
		log.log(Level.INFO, "SUITE EXECUTION ENDED");

	}
}
