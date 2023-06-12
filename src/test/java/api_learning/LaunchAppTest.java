package api_learning;

import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class LaunchAppTest {

	public static void main(String[] args) {
		// Send a request into Appium server > Ask to launch the app
		AppiumDriver<MobileElement> appiumDriver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("automationName", "uiautomator2");
		desiredCapabilities.setCapability("udid", "RF8RC1VR7EA");
		desiredCapabilities.setCapability("appPackage", "com.wdiodemoapp");
		desiredCapabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");
		// Note: use powershell with cmd <adb shell dumpsys window | Select-String "mCurrentFocus">

		try {
			// Init appium session
			URL appiumServer = new URL("http://localhost:4723/wd/hub");
			appiumDriver = new AndroidDriver<MobileElement>(appiumServer, desiredCapabilities);

			// DEBUD PERPOSE ONLY
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (appiumDriver != null) {
			appiumDriver.quit();
		}

	}
}
