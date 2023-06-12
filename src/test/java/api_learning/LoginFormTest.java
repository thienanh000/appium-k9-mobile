package api_learning;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import platform.Platform;

public class LoginFormTest {

	public static void main(String[] args) {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

		try {
			MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
			navLoginScreenBtnElem.click();

			MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
			MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
			MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

			emailInputElem.sendKeys("teo@sth.com");
			passwordInputElem.sendKeys("12345678");
			loginBtnElem.click();

			WebDriverWait wait = new WebDriverWait(appiumDriver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("android:id/alertTitle")));
			MobileElement loginDialogTitleElem = appiumDriver.findElement(MobileBy.id("android:id/alertTitle"));
			System.out.println(loginDialogTitleElem.getText());

			// DEBUG
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		appiumDriver.quit();

	}
}
