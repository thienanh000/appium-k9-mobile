package api_learning;

import org.openqa.selenium.Capabilities;

import driver.DriverFactory;
import driver.MobileCapabilityTypeEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.internal.CapabilityHelpers;
import platform.Platform;

public class HandleVariantBehaviour implements MobileCapabilityTypeEx{

	public static void main(String[] args) {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

		try {
			// Get platformName
			Capabilities caps = appiumDriver.getCapabilities();
			String platformName = CapabilityHelpers.getCapability(caps, PLATFORM_NAME, String.class);
			System.out.println("[INFO] Testing on: " + platformName);
			

			// DEBUG
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
		}

		appiumDriver.quit();
	}
}
