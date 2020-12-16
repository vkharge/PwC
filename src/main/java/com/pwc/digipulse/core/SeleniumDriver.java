package com.pwc.digipulse.core;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.google.common.base.Throwables;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumDriver {

	private static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


	public static WebDriver getInstance(JSONObject driverConfig) {
		log.log(Level.INFO, "Browser launch initiated");
		WebDriver driver = initWebDriver(driverConfig);
		return driver;
	}

	private static WebDriver initWebDriver(JSONObject driverConfig) {
		WebDriver driver = null;

		String connectType = driverConfig.getString("connect").toLowerCase();

		if ("standalone".equalsIgnoreCase(connectType)) {
			driver = launchBrowserStandaloneMode(driverConfig);
		} else {
			log.log(Level.SEVERE, "Connect mode not supported. Only supported mode is 'standalone'");
			throw new RuntimeException("Connect mode not supported. Only supported mode is 'standalone'");
		}

		setBrowserConfig(driver, driverConfig);

		return driver;

	}

	private static WebDriver launchBrowserStandaloneMode(JSONObject driverConfig) {

		WebDriver driver = null;
		JSONObject browser = driverConfig.getJSONObject("browser");
		String browserName = browser.getString("name").toLowerCase();

		log.log(Level.INFO, "Launching " + browserName + " Browser");

		if ("firefox".equalsIgnoreCase(browserName)) {
			driver = launchBrowser(getFirefoxOptions(browser));
		} else if ("chrome".equalsIgnoreCase(browserName)) {
			driver = launchBrowser(getChromeOptions(browser));
		} else {
			log.log(Level.SEVERE, browserName + " browser not supported");
			throw new RuntimeException(browserName + " browser not supported");
		}

		return driver;
	}

	private static ChromeOptions getChromeOptions(JSONObject browser) {
		ChromeOptions options = new ChromeOptions();

		return options;
	}

	private static FirefoxOptions getFirefoxOptions(JSONObject browser) {
		FirefoxOptions options = new FirefoxOptions();

		return options;

	}

	private static WebDriver launchBrowser(FirefoxOptions firefoxOptions) {
		WebDriver driver = null;

		try {
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
			driver = new FirefoxDriver();
		} catch (Exception e) {
			log.log(Level.SEVERE,
					"Error occured during initiating webdriver instance\n" + Throwables.getStackTraceAsString(e));
			throw e;
		}
		return driver;
	}

	private static WebDriver launchBrowser(ChromeOptions chromeOptions) {
		WebDriver driver = null;

		try {
			WebDriverManager.chromedriver().setup();
			System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "/dev/null");
			driver = new ChromeDriver();
		} catch (Exception e) {
			log.log(Level.SEVERE,
					"Error occured during initiating webdriver instance\n" + Throwables.getStackTraceAsString(e));
			throw e;
		}
		return driver;

	}

	private static void setBrowserConfig(WebDriver driver, JSONObject driverConfig) {

		if (driverConfig.has("timeouts")) {

			JSONObject timeouts = driverConfig.getJSONObject("timeouts");

			if (timeouts.has("implicit")) {
				long implicitWait = timeouts.getLong("implicit");
				driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
			}

			if (timeouts.has("pageload")) {
				long pageWait = timeouts.getLong("pageload");
				driver.manage().timeouts().pageLoadTimeout(pageWait, TimeUnit.SECONDS);
			}
		}

		driver.manage().window().maximize();

	}
}
