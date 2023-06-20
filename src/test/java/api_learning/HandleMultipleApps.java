package api_learning;

import java.time.Duration;

import org.openqa.selenium.By;

import driver.AppPackages;
import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import platform.Platform;

public class HandleMultipleApps {

	public static void main(String[] args) {

		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

		try {
			// Navigate to Login form and fill out all fields
			MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
			navLoginScreenBtnElem.click();

			MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
			MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
			MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

			emailInputElem.sendKeys("teo@sth.com");
			passwordInputElem.sendKeys("12345678");
			loginBtnElem.click();

			// Put the app under test to background in a certain time | simulate pressing
			// home button > relaunch
			// appiumDriver.runAppInBackground(Duration.ofSeconds(3));

			// Put the app under test to background till we call it back
			appiumDriver.runAppInBackground(Duration.ofSeconds(-1));

			// Switch to another app | Go to Settings toggle wifi
			appiumDriver.activateApp(AppPackages.SETTINGS);

			// Navigate to network list
			By wifiLabelSel = MobileBy.xpath("//*[@text='Kết nối']");
			appiumDriver.findElement(wifiLabelSel).click();

			// Toggle ON/OFF
			By wifiStatusSel = MobileBy.AccessibilityId("Wi-Fi");
			MobileElement wifiStatusElem = appiumDriver.findElement(wifiStatusSel);
			String wifiStatusStr = wifiStatusElem.getAttribute("checked");
			boolean isWifiOn = wifiStatusStr.equalsIgnoreCase("true");
			if (isWifiOn) {
				wifiStatusElem.click();
			}

			// Come back to the app > interact with other elements
			appiumDriver.activateApp(AppPackages.WEBDRIVER_IO);
			appiumDriver.findElement(MobileBy.xpath("//*[@text='OK']")).click();

			// DEBUG
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}

		appiumDriver.quit();
	}
}
