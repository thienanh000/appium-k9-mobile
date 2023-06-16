package driver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import platform.Platform;

public class DriverFactory implements MobileCapabilityTypeEx {

	public static AppiumDriver<MobileElement> getDriver(Platform platform) {
		AppiumDriver<MobileElement> appiumDriver = null;
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(PLATFORM_NAME, "Android");
		desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
		desiredCapabilities.setCapability(UDID, "RF8RC1VR7EA");
		desiredCapabilities.setCapability(APP_PACKAGE, "com.wdiodemoapp");
		desiredCapabilities.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
		URL appiumServer = null;
		try {
			appiumServer = new URL("http://localhost:4723/wd/hub");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (appiumServer == null) {
			throw new RuntimeException("[ERR] Can not construct the appium server url @http://localhost:4723/wd/hub");
		}

		switch (platform) {
		case ANDROID:
			appiumDriver = new AndroidDriver<MobileElement>(appiumServer, desiredCapabilities);
			break;
		case IOS:
			appiumDriver = new IOSDriver<MobileElement>(appiumServer, desiredCapabilities);
			break;
		}

		appiumDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		return appiumDriver;
	}
	
	//NOTE: 
	/*
	 * Use the bellow cmd to run again when meeting error of "No Chromedriver found that can automate Chrome" 
	 * appium --allow-insecure chromedriver_autodownload
	 */

}
