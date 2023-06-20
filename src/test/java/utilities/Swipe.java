package utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

public class Swipe {

//	private static TouchAction touchAction;

	// Swipe up from 90 -> 10
	public static void swipeVerically(AppiumDriver<MobileElement> appiumDriver) {
		PointOption<ElementOption> startPoint = defintPoint(appiumDriver, 50, 90);
		PointOption<ElementOption> endPoint = defintPoint(appiumDriver, 50, 10);

		TouchAction touchAction = new TouchAction(appiumDriver);
		touchAction.press(startPoint).waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
				.moveTo(endPoint).release().perform();
	}

	// Swipe up | step 10%, 5 times
	public static void swipeVerically(AppiumDriver<MobileElement> appiumDriver, int percent, int times) {
		PointOption<ElementOption> startPoint = defintPoint(appiumDriver, 50, 50);
		PointOption<ElementOption> endPoint = defintPoint(appiumDriver, 50, 50 - percent);

		for (int time = 0; time < times; time++) {
			TouchAction touchAction = new TouchAction(appiumDriver);
			touchAction.press(startPoint).waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
					.moveTo(endPoint).release().perform();
		}
	}

	// Swipe to see specific String
	public static void swipeHorizontally(AppiumDriver<MobileElement> appiumDriver, By selector, String targetStr) {
		PointOption<ElementOption> startPoint = defintPoint(appiumDriver, 70, 70);
		PointOption<ElementOption> endPoint = defintPoint(appiumDriver, 5, 70);

		TouchAction touchAction = new TouchAction(appiumDriver);
		List<MobileElement> titleElems = appiumDriver.findElements(selector);

		if (titleElems.isEmpty()) {
			throw new RuntimeException("[ERR] There is no list element!");
		}

		// Swipe from right to left
		for (int time = 0; time < 6; time++) {
			String elementText = "NOT FOUND!";
			for (MobileElement element : titleElems) {
				if (element.getText().trim().equals(targetStr)) {
					elementText = element.getText().trim();
					break;
				}
			}
			System.out.println("[SEARCH INFO]: " + elementText);
			if (elementText.equals(targetStr)) {
				System.out.println("Found the target string!");
				break;
			} else {
				touchAction.longPress(startPoint).moveTo(endPoint).release().perform();
				titleElems = appiumDriver.findElements(selector);
			}
		}
	}

	private static PointOption<ElementOption> defintPoint(AppiumDriver<MobileElement> appiumDriver, int xPoint,
			int yPoint) {
		Dimension windowSize = appiumDriver.manage().window().getSize();
		int screenHeight = windowSize.getHeight();
		int screenWidth = windowSize.getWidth();

		int xPointPercent = xPoint * screenWidth / 100;
		int yPointPercent = yPoint * screenHeight / 100;

		return new PointOption<ElementOption>().withCoordinates(xPointPercent, yPointPercent);
	}

}
