package api_learning;

import java.time.Duration;
import java.util.List;

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
import utilities.Swipe;

public class SwipeHorizontally {

	public static void main(String[] args) {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

		try {
			MobileElement navSwipeScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Swipe"));
			navSwipeScreenBtnElem.click();

			// Wait until user is on Forms screen
			WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")")));

			// Get Mobile window size
			Dimension windowSize = appiumDriver.manage().window().getSize();
			int screenHeight = windowSize.getHeight();
			int screenWidth = windowSize.getWidth();

			// Calculate touch points
			int xStartPoint = 70 * screenWidth / 100;
			int xEndPoint = 10 * screenWidth / 100;
			int yStartPoint = 70 * screenHeight / 100;
			int yEndPoint = 70 * screenHeight / 100;

			// Convert coordinates -> PointOption
			PointOption<ElementOption> startPoint = new PointOption<ElementOption>().withCoordinates(xStartPoint,
					yStartPoint);
			PointOption<ElementOption> endPoint = new PointOption<ElementOption>().withCoordinates(xEndPoint,
					yEndPoint);

			// Using TouchAction to swipe
			TouchAction touchAction = new TouchAction(appiumDriver);
					
			//TODO: Verify Swipe to see specific String
//			List<MobileElement> targetElems = appiumDriver.findElements(MobileBy.AccessibilityId("slideTextContainer"));
//			String targetStr = targetElems.get(0).getText().trim();
//			Swipe.swipeHorizontally(appiumDriver, targetElems.get(0), "SUPPORT VIDEOS");	
			
			// Swipe from right to left
			for (int time = 0; time < 5; time++) {
				touchAction
				.press(startPoint)
				.waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
				.moveTo(endPoint)
				.release()
				.perform();
			}
			
			// Swipe from left to right
			for (int time = 0; time < 5; time++) {
				touchAction
				.press(endPoint)
				.waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
				.moveTo(startPoint)
				.release()
				.perform();
			}

			// DEBUG
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		appiumDriver.quit();

	}
}
