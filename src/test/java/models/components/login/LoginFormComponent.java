package models.components.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;

public class LoginFormComponent {

	private final AppiumDriver<MobileElement> appiumDriver;
	private final static By usernameSel = MobileBy.AccessibilityId("input-email");
	private final static By passwordSel = MobileBy.AccessibilityId("input-password");
	private final static By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
	private final static By successInfoSel = MobileBy.id("android:id/alertTitle");
	private final static By okBtnSel = MobileBy.id("android:id/button1");

	public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(10)), this);
	}

	@Step("Input username as {usernameTxt}")
	public void inputUsername(String usernameTxt) {
		if (!usernameTxt.isEmpty()) {
			MobileElement usernameElem = appiumDriver.findElement(usernameSel);
			usernameElem.clear();
			usernameElem.sendKeys(usernameTxt);
		}
	}

	@AndroidFindBy(xpath = "//*[contains(@text, 'Please enter a valid email address')]")
	@iOSXCUITFindBy(iOSNsPredicate = "label == \"Please enter a valid email address\"")
	private MobileElement incorrectEmailTxtElem;

	@AndroidFindBy(xpath = "//*[contains(@text, 'Please enter at least 8 characters')]")
	@iOSXCUITFindBy(iOSNsPredicate = "label == \"Please enter at least 8 characters\"")
	private MobileElement incorrectPasswordTxtElem;

	public String getInvalidEmailStr() {
		return incorrectEmailTxtElem.getText().trim();
	}

	@Step("Input password as {passwordTxt}")
	public void inputPassword(String passwordTxt) {
		if (!passwordTxt.isEmpty()) {
			MobileElement passwordElem = appiumDriver.findElement(passwordSel);
			passwordElem.clear();
			passwordElem.sendKeys(passwordTxt);
		}
	}

	public String getInvalidPasswordStr() {
		return incorrectPasswordTxtElem.getText().trim();
	}

	@Step("Click on login button")
	public void clickOnLoginBtn() {
		appiumDriver.findElement(loginBtnSel).click();
	}

	public String getSuccessInfoStr() {
		return appiumDriver.findElement(successInfoSel).getText();
	}

	public void clickOnOkBtn() {
		appiumDriver.findElement(okBtnSel).click();
	}

}
