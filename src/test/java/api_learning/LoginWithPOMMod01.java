package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenMod01;
import platform.Platform;

public class LoginWithPOMMod01 {

	public static void main(String[] args) {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

		try {
			MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
			navLoginScreenBtnElem.click();
			
			LoginScreenMod01 loginScreenMod01 = new LoginScreenMod01(appiumDriver);
			loginScreenMod01.usernameElem().sendKeys("teo@sth.com");
			loginScreenMod01.passwordElem().sendKeys("12345678");
			loginScreenMod01.loginBtnElem().click();

		} catch (Exception e) {
			e.printStackTrace();
		}

		appiumDriver.quit();
	}
}
