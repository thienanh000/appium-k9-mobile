package models.components.login;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class LoginFormComponent {

	private final AppiumDriver<MobileElement> appiumDriver;
	private final static By usernameSel = MobileBy.AccessibilityId("input-email");
	private final static By incorrectEmailTxtSel = MobileBy.xpath("//*[contains(@text, 'Please enter a valid email address')]");
	private final static By passwordSel = MobileBy.AccessibilityId("input-password");
	private final static By incorrectPasswordTxtSel = MobileBy.xpath("//*[contains(@text, 'Please enter at least 8 characters')]");
	private final static By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
	private final static By successInfoSel = MobileBy.id("android:id/alertTitle");

	public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
	}

	public void inputUsername(String usernameTxt) {
		if (!usernameTxt.isEmpty()) {
			MobileElement usernameElem = appiumDriver.findElement(usernameSel);
			usernameElem.clear();
			usernameElem.sendKeys(usernameTxt);}
	}
	
	public String getInvalidEmailStr() {
		return appiumDriver.findElement(incorrectEmailTxtSel).getText();
	}

	public void inputPassword(String passwordTxt) {
		if (!passwordTxt.isEmpty()) {
			MobileElement passwordElem = appiumDriver.findElement(passwordSel);
			passwordElem.clear();
			passwordElem.sendKeys(passwordTxt);}
	}

	public String getInvalidPasswordStr() {
		return appiumDriver.findElement(incorrectPasswordTxtSel).getText();
	}

	public void clickOnLoginBtn() {
		appiumDriver.findElement(loginBtnSel).click();
	}

	public String getSuccessInfoStr() {
		return appiumDriver.findElement(successInfoSel).getText();
	}

}
