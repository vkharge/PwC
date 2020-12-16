package com.pwc.digipulse.pages.searchresult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pwc.digipulse.pages.common.Component;

public class SearchResult extends Component {

	// @FindBy(css = "section.main-content div[class*='post-']")
	@FindBy(xpath = "//section[contains(@class, 'main-content')]//div[contains(@class, 'post-')]")
	private List<WebElement> posts;

	@FindBy(css = "section.main-content h2")
	private WebElement noResultsLabel;

	private By articleTypeLoc = By.cssSelector("span.article-type");
	private By articleNameLoc = By.cssSelector("h2 a");
	private By articleAuthorLoc = By.xpath(".//*[contains(@class, 'bt-user')]/parent::span/a");
	private By articleDateLoc = By.xpath(".//*[contains(@class, 'bt-calendar')]/parent::span");
	private By articleDescLoc = By.xpath("p[1]");
	private By articleReadMoreLoc = By.xpath(".//a[text()='Read more']");

	public SearchResult(WebDriver driver) {
		super(driver);
	}

	@Override
	protected void waitForVisible() {
		logger.log(Level.INFO, "Wait for visibility of search result component");
		int count = 1;
		while (count <= 10) {
			if ((posts.size() > 0) || (noResultsLabel != null)) {
				break;
			}
		}
	}

	@Override
	public boolean isDisplayed() {
		logger.log(Level.INFO, "Check if search result is displayed");
		if ((posts.size() > 0) || (noResultsLabel != null)) {
			return true;
		}

		return false;
	}

	public List<Map<String, String>> getSearchResults() {

		List<Map<String, String>> postsData = new ArrayList<>();
		for (WebElement post : posts) {
			Map<String, String> postData = new HashMap<>();
			String articleType = post.findElement(articleTypeLoc).getText();

			WebElement nameElement = post.findElement(articleNameLoc);
			String articleName = nameElement.getText();
			String articleLink = nameElement.getAttribute("href");

			List<WebElement> authorElements = post.findElements(articleAuthorLoc);
			List<String> author = new ArrayList<>();
			// String authorLink = "";
			for (WebElement authorElement : authorElements) {
				author.add(authorElement.getText());
				// authorLink = authorElement.getAttribute("href");
			}

			WebElement articleDate = post.findElement(articleDateLoc);
			String date = articleDate.getText();

			WebElement articleDiscription = post.findElement(articleDescLoc);
			String description = articleDiscription.getText();

			WebElement readMore = post.findElement(articleReadMoreLoc);
			String readMoreLink = readMore.getAttribute("href");

			postData.put("type", articleType);
			postData.put("name", articleName);
			postData.put("link", articleLink);
			postData.put("author", String.join(",", author));
			postData.put("date", date);
			postData.put("description", description);
			postData.put("readmore", readMoreLink);

			postsData.add(postData);

		}
		return postsData;
	}

	private List<Map<String, String>> getFilteredData(String filterName, String keyName) {
		List<Map<String, String>> postsData = getSearchResults();

		List<Map<String, String>> filteredPostsData = new ArrayList<>();

		for (Map<String, String> postData : postsData) {
			if (filterName.equalsIgnoreCase(postData.get(keyName))) {

				filteredPostsData.add(postData);
			}
		}

		return postsData;
	}

	private int getArticleNumber(String filterName, String keyName) {
		List<Map<String, String>> postsData = getSearchResults();
		int count = 1;
		for (Map<String, String> postData : postsData) {
			if (filterName.equalsIgnoreCase(postData.get(keyName))) {
				return count;
			}
			count++;
		}

		return 0;
	}

	public List<Map<String, String>> getSearchResultsByName(String name) {
		return getFilteredData(name, "name");

	}

	public List<Map<String, String>> getSearchResultsByArticleType(String articleType) {

		return getFilteredData(articleType, "type");
	}

	public List<Map<String, String>> getSearchResultsByAuthor(String author) {

		List<Map<String, String>> postsData = getSearchResults();

		List<Map<String, String>> filteredPostsData = new ArrayList<>();

		for (Map<String, String> postData : postsData) {
			if (author.equalsIgnoreCase(postData.get("author")) || author.contains(postData.get("author"))) {

				filteredPostsData.add(postData);
			}
		}

		return postsData;
	}

	public List<Map<String, String>> getSearchResultsByDate(String date) {

		return getFilteredData(date, "date");
	}

	public void goToArticleByName(String name) {
		int articleNo = getArticleNumber(name, "name");

		if (articleNo == 0) {
			throw new RuntimeException("Article with name '" + name + "' not Found");
		}
		posts.get(articleNo).findElement(articleNameLoc).click();
	}

	public String getNoResultsFoundMessage() {
		return wait.waitForVisibiltyOfElement(noResultsLabel).getText();
	}
}
