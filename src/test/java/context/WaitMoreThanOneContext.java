package context;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.functions.ExpectedCondition;

public class WaitMoreThanOneContext implements ExpectedCondition<Boolean> {

	private final AppiumDriver<MobileElement> appiumDriver;

	public WaitMoreThanOneContext(AppiumDriver<MobileElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
	}

	public Boolean apply(WebDriver webDriver) {
		return appiumDriver.getContextHandles().size() > 1;
	}

}
