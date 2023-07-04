package models.components.global;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class BottomNavComponent {

	private final AppiumDriver<MobileElement> appiumDriver;
	private final static By loginIconSel = MobileBy.AccessibilityId("Login");
	private final static By formIconSel = MobileBy.AccessibilityId("Forms");

	public BottomNavComponent(AppiumDriver<MobileElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
	}

	public void clickOnLoginIcon() {
		appiumDriver.findElement(loginIconSel).click();
	}

	public void clickOnFormIcon() {
		appiumDriver.findElement(formIconSel).click();
	}

}
