package com.pwc.digipulse.core;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import com.pwc.digipulse.pages.DigiPulseApp;

public class Base {

	private static JSONObject config;
	private static JSONObject sutConfig;
	private static JSONObject driverConfig;
	private static DigiPulseApp app;
	private static WebDriver driver;

	
	
	public static WebDriver getDriver() {
		return driver;
	}
	public static void setDriver(WebDriver driver) {
		Base.driver = driver;
	}
	public static DigiPulseApp getApp() {
		return app;
	}
	public static void setApp(DigiPulseApp app) {
		Base.app = app;
	}
	public static JSONObject getConfig() {
		return config;
	}
	public static void setConfig(JSONObject config) {
		Base.config = config;
	}
	public static JSONObject getSutConfig() {
		return sutConfig;
	}
	public static void setSutConfig(JSONObject sutConfig) {
		Base.sutConfig = sutConfig;
	}
	public static JSONObject getDriverConfig() {
		return driverConfig;
	}
	public static void setDriverConfig(JSONObject driverConfig) {
		Base.driverConfig = driverConfig;
	}
	


}
