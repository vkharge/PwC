package com.pwc.digipulse.pages.contactus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pwc.digipulse.pages.common.Component;

public class ContactOptions extends Component {

	@FindBy(css = "section.container.contacts > div")
	private WebElement contactContainer;

	public ContactOptions(WebDriver driver) {
		super(driver);
	}

	@Override
	protected void waitForVisible() {
		logger.log(Level.INFO, "Wait for visibility of contact container component");
		wait.waitForVisibiltyOfElement(contactContainer);
	}

	@Override
	public boolean isDisplayed() {
		logger.log(Level.INFO, "Check if contact container is displayed");
		return contactContainer.isDisplayed();
	}

	public Map<String, Map<String, String>> getContactOptions() {
		List<WebElement> contactsElements = contactContainer.findElements(By.cssSelector("div"));

		Map<String, Map<String, String>> contacts = new HashMap<>();
		for (WebElement contactElement : contactsElements) {
			Map<String, String> contact = new HashMap<>();

			String heading = contactElement.findElement(By.tagName("h2")).getText().replace("\n", " ");

			List<WebElement> pTags = contactElement.findElements(By.tagName("p"));
			for (WebElement pTag : pTags) {
				List<WebElement> links = pTag.findElements(By.tagName("a"));
				if (links.size() == 1) {
					contact.put("contactlink", links.get(0).getAttribute("href"));
					contact.put("contactlinkname", links.get(0).getText());
				} else {

					contact.put("description", pTag.getText());

				}
			}
			contacts.put(heading, contact);

		}

		return contacts;
	}


}
