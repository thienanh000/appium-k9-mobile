package utilities;

import java.time.Duration;

import org.openqa.selenium.Dimension;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class Swipe {

	// Swipe up from 90 -> 10
	public static void swipeVerically(AppiumDriver<MobileElement> appiumDriver) {
		Dimension windowSize = appiumDriver.manage().window().getSize();
		int screenHeight = windowSize.getHeight();
		int screenWidth = windowSize.getWidth();

		int xStartPoint = 50 * screenWidth / 100;
		int xEndPoint = 50 * screenWidth / 100;
		int yStartPoint = 90 * screenHeight / 100;
		int yEndPoint = 10 * screenHeight / 100;

		PointOption<ElementOption> startPoint = new PointOption<ElementOption>().withCoordinates(xStartPoint,
				yStartPoint);
		PointOption<ElementOption> endPoint = new PointOption<ElementOption>().withCoordinates(xEndPoint, yEndPoint);

		TouchAction touchAction = new TouchAction(appiumDriver);
		touchAction.press(startPoint).waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
				.moveTo(endPoint).release().perform();
	}

	// Swipe up | step 10%, 5 times
	public static void swipeVerically(AppiumDriver<MobileElement> appiumDriver, int percent, int times) {
		Dimension windowSize = appiumDriver.manage().window().getSize();
		int screenHeight = windowSize.getHeight();
		int screenWidth = windowSize.getWidth();

		int xStartPoint = 50 * screenWidth / 100;
		int xEndPoint = 50 * screenWidth / 100;
		int yStartPoint = 50 * screenHeight / 100;
		int yEndPoint = (yStartPoint - percent) * screenHeight / 100;

		PointOption<ElementOption> startPoint = new PointOption<ElementOption>().withCoordinates(xStartPoint,
				yStartPoint);
		PointOption<ElementOption> endPoint = new PointOption<ElementOption>().withCoordinates(xEndPoint, yEndPoint);

		for (int time = 0; time < times; time++) {
			TouchAction touchAction = new TouchAction(appiumDriver);
			touchAction.press(startPoint).waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
					.moveTo(endPoint).release().perform();
		}
	}

	// TODO: Swipe to see specific String
	public static void swipeHorizontally(AppiumDriver<MobileElement> appiumDriver, MobileElement element,
			String targetStr) {
		Dimension windowSize = appiumDriver.manage().window().getSize();
		int screenHeight = windowSize.getHeight();
		int screenWidth = windowSize.getWidth();

		int xStartPoint = 70 * screenWidth / 100;
		int xEndPoint = 5 * screenWidth / 100;
		int yStartPoint = 70 * screenHeight / 100;
		int yEndPoint = 70 * screenHeight / 100;

		PointOption<ElementOption> startPoint = new PointOption<ElementOption>().withCoordinates(xStartPoint,
				yStartPoint);
		PointOption<ElementOption> endPoint = new PointOption<ElementOption>().withCoordinates(xEndPoint, yEndPoint);

		TouchAction touchAction = new TouchAction(appiumDriver);

		// Swipe from right to left
		for (int time = 0; time < 6; time++) {
			touchAction.longPress(startPoint).moveTo(endPoint).release().perform();
			String elementText = element.getText().trim();
			System.out.println("Title: " + elementText);
			if (elementText.equals(targetStr)) {
				System.out.println("Found the target string!");
				break;
			}
		}
	}
}
