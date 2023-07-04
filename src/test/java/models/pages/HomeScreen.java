package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavComponent;

public class HomeScreen {

	private final AppiumDriver<MobileElement> appiumDriver;

	public HomeScreen(AppiumDriver<MobileElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
	}

	public BottomNavComponent bottomNavComp() {
		return new BottomNavComponent(appiumDriver);
	}
}
