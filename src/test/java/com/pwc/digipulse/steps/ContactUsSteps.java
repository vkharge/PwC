package com.pwc.digipulse.steps;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactUsSteps {

	private SharedContext context;
	private Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public ContactUsSteps(SharedContext context) {
		this.context = context;
	}

	@When("I select {string} from the hamburger menu")
	public void clickOnOptionFromHamburgerMenu(String menuName) {
		context.contactUsPage = context.homePage.header().hamMenu().goToContactUsPage();
	}

	@Then("I am taken to the {string} page")
	public void verifyContactusPage(String pageName) {
		log.log(Level.INFO, "Verify " + pageName + " is displayed");
		String actPageLabel = context.contactUsPage.getPageLabel();
		Assert.assertEquals(actPageLabel, pageName, "Contact Us page level is not matched");

	}

	@Then("I am presented with the below options for contacts")
	public void verifyContactOptions(io.cucumber.datatable.DataTable dataTable) {

		log.log(Level.INFO, "Verify different contact options are displayed");

		Map<String, Map<String, String>> actContactOptions = context.contactUsPage.contactOptions().getContactOptions();
		log.log(Level.INFO, "Contact options fetched from application: " + actContactOptions);

		List<Map<String, String>> expContactOptions = dataTable.asMaps();
		for (Map<String, String> expContactOption : expContactOptions) {

			log.log(Level.INFO, "Expected contact option: " + expContactOption);

			String expHeading = expContactOption.get("Heading");
			String expContactLink = expContactOption.get("Contact Link");

			Assert.assertEquals(actContactOptions.containsKey(expHeading), true,
					"Contact option '" + expHeading + "' is not present");

			String actContactLink = actContactOptions.get(expHeading).get("contactlink");
			Assert.assertEquals(actContactLink, expContactLink, "Contact link name is not matching");

		}

	}

}
