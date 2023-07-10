package api_learning;

import java.util.List;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import platform.Platform;

public class XPathLearning {

	public static void main(String[] args) {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

		try {
			MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
			navLoginScreenBtnElem.click();

			List<MobileElement> credFieldsElem = appiumDriver.findElements(MobileBy.xpath("//android.widget.EditText"));
			final int EMAIL_INDEX = 0;
			final int PASSWORD_INDEX = 1;
			credFieldsElem.get(EMAIL_INDEX).sendKeys("teo@sth.com");
			credFieldsElem.get(PASSWORD_INDEX).sendKeys("12345678");

			// Find login info text by UiSelector
			MobileElement loginInstructionElem = appiumDriver
					.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"When the device\")"));
			System.out.println(loginInstructionElem.getText());

			// DEBUG
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		appiumDriver.quit();

	}
}
