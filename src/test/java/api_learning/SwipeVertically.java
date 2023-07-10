package api_learning;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import platform.Platform;

public class SwipeVertically {

	public static void main(String[] args) {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

		try {
			MobileElement navFormScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
			navFormScreenBtnElem.click();

			// Wait until user is on Forms screen
			WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form components\")")));

			// Get Mobile window size
			Dimension windowSize = appiumDriver.manage().window().getSize();
			int screenHeight = windowSize.getHeight();
			int screenWidth = windowSize.getWidth();

			// Calculate touch points
			int xStartPoint = 50 * screenWidth / 100;
			int xEndPoint = 50 * screenWidth / 100;
			int yStartPoint = 50 * screenHeight / 100;
			int yEndPoint = 10 * screenHeight / 100;

			// Convert coordinates -> PointOption
			PointOption<ElementOption> startPoint = new PointOption<ElementOption>().withCoordinates(xStartPoint,
					yStartPoint);
			PointOption<ElementOption> endPoint = new PointOption<ElementOption>().withCoordinates(xEndPoint,
					yEndPoint);

			// Using TouchAction to swipe
			TouchAction touchAction = new TouchAction(appiumDriver);
			touchAction
					.press(startPoint)
					.waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
					.moveTo(endPoint)
					.release()
					.perform();
			
//			MobileElement activeBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
//			activeBtnElem.click();

			// Swipe down
			touchAction
				.longPress(endPoint)
				.moveTo(startPoint)
				.release()
				.perform();

			// DEBUG
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		appiumDriver.quit();
	}
}
