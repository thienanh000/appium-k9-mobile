package api_learning;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.pages.LoginScreenMod02;
import platform.Platform;

public class LoginWithPOMMod02 {

	public static void main(String[] args) {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

		try {
			MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
			navLoginScreenBtnElem.click();

			LoginScreenMod02 loginScreenMod02 = new LoginScreenMod02(appiumDriver);
			loginScreenMod02.inputUsername("teo@sth.com");
			loginScreenMod02.inputPassword("12345678");
			loginScreenMod02.clickOnLoginBtn();

		} catch (Exception e) {
			e.printStackTrace();
		}

		appiumDriver.quit();
	}
}
