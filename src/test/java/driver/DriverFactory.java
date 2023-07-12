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

	// NOTE:
	/*
	 * Use the bellow cmd to run again when meeting error of
	 * "No Chromedriver found that can automate Chrome" appium --allow-insecure
	 * chromedriver_autodownload
	 */

	private AppiumDriver<MobileElement> appiumDriver;

	public static AppiumDriver<MobileElement> getDriver(Platform platform) {
		AppiumDriver<MobileElement> appiumDriver = null;

		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability(PLATFORM_NAME, "Android");
		desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
		desiredCapabilities.setCapability(UDID, "RF8RC1VR7EA");
		desiredCapabilities.setCapability(APP_PACKAGE, "com.wdiodemoapp");
		desiredCapabilities.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
		URL appiumServer = null;

		String targetServer = "http://localhost:4723/wd/hub";
//		String targetServer = "http://10.10.62.130:4444/wd/hub";
		try {
			appiumServer = new URL(targetServer);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (appiumServer == null) {
			throw new RuntimeException("[ERR] Can not construct the appium server url " + targetServer);
		}

		switch (platform) {
		case android:
			appiumDriver = new AndroidDriver<MobileElement>(appiumServer, desiredCapabilities);
			break;
		case ios:
			appiumDriver = new IOSDriver<MobileElement>(appiumServer, desiredCapabilities);
			break;
		}

		appiumDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		return appiumDriver;
	}

	public AppiumDriver<MobileElement> getDriver(Platform platform, String udid, String systemPort,
			String platformVersion) {
		String remoteInfoViaEnvVar = System.getenv("env");
		String remoteInfoViaCommandVar = System.getProperty("env");
		String isRemote = remoteInfoViaEnvVar == null ? remoteInfoViaCommandVar : remoteInfoViaEnvVar;

		if (remoteInfoViaEnvVar == null) {
			throw new IllegalArgumentException("Please provide env variable [env]!");
		}
		
		String targetServer = "http://localhost:4723/wd/hub";
		if (isRemote.equals("true")) {
			String hubIpAddress = System.getenv("hub");
			if (hubIpAddress == null) {
				hubIpAddress = System.getProperty("hub");
			}
			if (hubIpAddress == null) {
				throw new IllegalArgumentException("Please provide hub ip address via env variable [hub]!");
			}
			targetServer = "http://" + hubIpAddress + ":4444/wd/hub";

		}

		if (appiumDriver == null) {
			URL appiumServer = null;

			try {
				appiumServer = new URL(targetServer);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			if (appiumServer == null) {
				throw new RuntimeException(
						"[ERR] Can not construct the appium server url @http://localhost:4723/wd/hub");
			}

			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability(PLATFORM_NAME, platform);
			switch (platform) {
			case android:
				desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
				desiredCapabilities.setCapability(UDID, udid);
				desiredCapabilities.setCapability(APP_PACKAGE, "com.wdiodemoapp");
				desiredCapabilities.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
				desiredCapabilities.setCapability(SYSTEM_PORT, systemPort);
				appiumDriver = new AndroidDriver<MobileElement>(appiumServer, desiredCapabilities);
				break;
			case ios:
				desiredCapabilities.setCapability(AUTOMATION_NAME, "XCUITest");
				desiredCapabilities.setCapability(DEVICE_NAME, udid);
				desiredCapabilities.setCapability(PLATFORM_VERSION, platformVersion);
				desiredCapabilities.setCapability(BUNDLE_ID, "org.wdioNativeDemoApp");
				desiredCapabilities.setCapability(WDA_LOCAL_PORT, systemPort);
				appiumDriver = new IOSDriver<MobileElement>(appiumServer, desiredCapabilities);
				break;
			}

			appiumDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		}
		return appiumDriver;
	}

	public void quitAppiumSession() {
		if (appiumDriver != null) {
			appiumDriver.quit();
			appiumDriver = null;
		}
	}

}
