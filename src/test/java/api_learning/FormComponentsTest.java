package api_learning;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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

public class FormComponentsTest {
	
	private static By textInputSel = MobileBy.AccessibilityId("text-input");
	private static By inputTextResultSel = MobileBy.AccessibilityId("input-text-result");
	private static By switchBtnSel = MobileBy.AccessibilityId("switch");
	private static By switchTextSel = MobileBy.AccessibilityId("switch-text");
	private static By dropdownSel = MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Dropdown\"]/android.view.ViewGroup/android.widget.EditText");
	private static By dropdownTextSel = MobileBy.className("android.widget.CheckedTextView");
	private static By activeBtnSel = MobileBy.AccessibilityId("button-Active");
	private static By activeBtnMsgSel = MobileBy.id("android:id/message");
	private static By okBtnSel = MobileBy.id("android:id/button1");

	public static void main(String[] args) {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

		try {
			MobileElement navFormScreenBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Forms"));
			navFormScreenBtnElem.click();
			
			// Fill out form
			appiumDriver.findElement(textInputSel).sendKeys("abc123");
			
			String typedStr = appiumDriver.findElement(inputTextResultSel).getText().trim();
			System.out.println("You have typed: " + typedStr);
			
			String defaultSwitchStatus = appiumDriver.findElement(switchTextSel).getText().trim();
			System.out.println("Default switch status: " + defaultSwitchStatus);
			appiumDriver.findElement(switchBtnSel).click();
			String changedSwtichStatus = appiumDriver.findElement(switchTextSel).getText().trim();
			System.out.println("Changed switch status: " + changedSwtichStatus);
			
			appiumDriver.findElement(dropdownSel).click();
			List<MobileElement> dropdownTextElems = appiumDriver.findElements(dropdownTextSel);
			for (MobileElement mobileElement : dropdownTextElems) {
				System.out.println(mobileElement.getText().trim());
			}
			int index = new SecureRandom().nextInt(3) + 1;
			System.out.println("Index: " + index);
			dropdownTextElems.get(index).getText().trim();
			dropdownTextElems.get(index).click();	
			String selectedDropdownText = appiumDriver.findElement(dropdownSel).getText().trim();
			System.out.println("Selected dropdown text is: " + selectedDropdownText);
			
			appiumDriver.findElement(activeBtnSel).click();
			String activeMsg = appiumDriver.findElement(activeBtnMsgSel).getText().trim();
			System.out.println("Active message: " + activeMsg);
			
			appiumDriver.findElement(okBtnSel).click();
			
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
			PointOption<ElementOption> endPoint = new PointOption<ElementOption>().withCoordinates(xEndPoint,
					yEndPoint);

			TouchAction touchAction = new TouchAction(appiumDriver);
			touchAction
					.press(startPoint)
					.waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
					.moveTo(endPoint)
					.release()
					.perform();
			
			// Click
			MobileElement activeBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-Active"));
			activeBtnElem.click();

			// DEBUG
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		appiumDriver.quit();
	}

}
