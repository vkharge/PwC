package com.pwc.digipulse.hooks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import com.pwc.digipulse.core.Base;
import com.pwc.digipulse.core.SeleniumDriver;
import com.pwc.digipulse.pages.DigiPulseApp;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import io.cucumber.plugin.event.TestStep;

public class Hooks {

	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private int stepNo;

	@Before
	public void beforeScenario(Scenario scenario) {
		String scName = scenario.getName();
		log.log(Level.INFO, "===========  START SCENARIO EXECUTION: " + scName + "  ================");
		stepNo = 0;
		
		JSONObject driverConfig = Base.getDriverConfig(); 
		JSONObject sutConfig = Base.getSutConfig();

		WebDriver driver = SeleniumDriver.getInstance(driverConfig);
		Base.setDriver(driver);
		System.out.println(driver);
		System.out.println(sutConfig);

		DigiPulseApp app = new DigiPulseApp(driver, sutConfig);
		Base.setApp(app);
	}

	@After
	public void afterScenario(Scenario scenario) {
		
		Base.getApp().quit();
		
		String scName = scenario.getName();
		log.log(Level.INFO, "END SCENARIO EXECUTION: " + scName);
		if (scenario.isFailed()) {
			log.log(Level.INFO, "SCENARIO FAILED: " + scName);
		} else {
			log.log(Level.INFO, "SCENARIO PASSED: " + scName);
		}
	}

	@BeforeStep
	public void beforeStep(Scenario scenario) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String stepName = getStepName(scenario);
		//log.log(Level.INFO, "START STEP EXECUTION: " + stepName);

	}

	@AfterStep
	public void afterStep(Scenario scenario) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		String stepName = getStepName(scenario);
		//log.log(Level.INFO, "END STEP EXECUTION: " + stepName);

		if (scenario.isFailed()) {
			log.log(Level.INFO, "STEP FAILED: Taking Screenshot");
			log.log(Level.INFO, "To Be Supported");

		}
		stepNo++;

	}

	private String getStepName(Scenario scenario) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field f = scenario.getClass().getDeclaredField("delegate");
		f.setAccessible(true);
		TestCaseState tcs = (TestCaseState) f.get(scenario);

		Field f2 = tcs.getClass().getDeclaredField("testCase");
		f2.setAccessible(true);
		TestCase r = (TestCase) f2.get(tcs);

		List<TestStep> stepDefs = r.getTestSteps();

		List<String> steps = new ArrayList<>();
		for (TestStep step : stepDefs) {
			if (step instanceof PickleStepTestStep) {
				PickleStepTestStep testStep = (PickleStepTestStep) step;
				String stepName = testStep.getStep().getText();
				steps.add(stepName);
			}
		}
		
		return steps.get(stepNo);
	}
}
