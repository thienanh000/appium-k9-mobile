package test_flows.form;

import java.time.Duration;

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
import test_flows.BaseFlow;

public class FormFlow extends BaseFlow {

	public FormFlow(AppiumDriver<MobileElement> appiumDriver) {
		super(appiumDriver);
	}

	public void fillTheForm() {
		// Wait until user is on Forms screen
		WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form components\")")));

		// Swipe
		Dimension windowSize = appiumDriver.manage().window().getSize();
		int screenHeight = windowSize.getHeight();
		int screenWidth = windowSize.getWidth();

		int xStartPoint = 50 * screenWidth / 100;
		int xEndPoint = 50 * screenWidth / 100;
		int yStartPoint = 50 * screenHeight / 100;
		int yEndPoint = 10 * screenHeight / 100;

		PointOption<ElementOption> startPoint = new PointOption<ElementOption>().withCoordinates(xStartPoint,
				yStartPoint);
		PointOption<ElementOption> endPoint = new PointOption<ElementOption>().withCoordinates(xEndPoint, yEndPoint);

		TouchAction touchAction = new TouchAction(appiumDriver);
		touchAction.press(startPoint).waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
				.moveTo(endPoint).release().perform();

		// Click
		MobileElement activeBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
		activeBtnElem.click();

	}

	public void verifyFormDisplay() {

		
	}
}
