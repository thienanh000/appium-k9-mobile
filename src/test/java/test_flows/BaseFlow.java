package test_flows;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.pages.HomeScreen;
import models.pages.LoginScreen;

public class BaseFlow {

	protected final AppiumDriver<MobileElement> appiumDriver;

	public BaseFlow(AppiumDriver<MobileElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
	}

	public void goToLoginScreen() {
		new LoginScreen(appiumDriver).bottomNavComp().clickOnLoginIcon();
	}

	public void goToFormScreen() {
		new HomeScreen(appiumDriver).bottomNavComp().clickOnFormIcon();
	}

}
