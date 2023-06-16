package api_learning;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import platform.Platform;

public class TakingScreenshot {

	public static void main(String[] args) {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

		try {
			MobileElement navLoginScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
			navLoginScreenBtnElem.click();
			
			// Wait until user is on Login screen
			MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));
			WebDriverWait wait = new WebDriverWait(appiumDriver, 5);
			wait.until(ExpectedConditions.visibilityOf(loginBtnElem));
			
			// Whole screen
			File base64ScreenshotData = appiumDriver.getScreenshotAs(OutputType.FILE);
			String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginScreen.png");
			FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));
			
			// An Area
			MobileElement loginFormElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login-screen"));
			base64ScreenshotData = loginFormElem.getScreenshotAs(OutputType.FILE);
			fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginForm.png");
			FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

			// An element
			base64ScreenshotData = loginBtnElem.getScreenshotAs(OutputType.FILE);
			fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginBtn.png");
			FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

			// DEBUG
			Thread.sleep(1000);

		} catch (Exception e) {
			e.printStackTrace();
		}

		appiumDriver.quit();
	}
}
