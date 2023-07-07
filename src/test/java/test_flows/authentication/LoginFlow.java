package test_flows.authentication;

import org.apache.commons.validator.EmailValidator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreen;
import test_flows.BaseFlow;

public class LoginFlow extends BaseFlow {

	private String username;
	private String password;

	public LoginFlow(AppiumDriver<MobileElement> appiumDriver, String username, String password) {
		super(appiumDriver);
		this.username = username;
		this.password = password;
	}

	public void login() {
		LoginScreen loginScreen = new LoginScreen(appiumDriver);
		LoginFormComponent loginFormComp = loginScreen.loginFormComp();
		if (!username.isEmpty()) {
			loginFormComp.inputUsername(username);
		}
		if (!password.isEmpty()) {
			loginFormComp.inputPassword(password);
		}
		loginFormComp.clickOnLoginBtn();

	}

	public void verifyLogin() {
		boolean isEmailValid = EmailValidator.getInstance().isValid(username);
		boolean isPasswordValid = password.length() >= 8;
		LoginFormComponent loginFormComp = new LoginFormComponent(appiumDriver);

		if (isEmailValid && isPasswordValid) {
			WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
			verifyCorrectLoginCreds(loginFormComp);
			loginFormComp.clickOnOkBtn();
		}
		if (!isEmailValid) {
			verifyIncorrectEmail(loginFormComp);
		}
		if (!isPasswordValid) {
			verifyIncorrectPassword(loginFormComp);
		}

	}

	@Step("Verify login with correct creds")
	private void verifyCorrectLoginCreds(LoginFormComponent loginFormComp) {
		String actualSucessInfo = loginFormComp.getSuccessInfoStr();
		String expectedSucessInfo = "Success";
		Assert.assertEquals(actualSucessInfo, expectedSucessInfo, "[ERR] Correct login message is correct!");

	}

	@Step("Verify login with incorrect email")
	private void verifyIncorrectEmail(LoginFormComponent loginFormComp) {
		String actualInvalidEmailStr = loginFormComp.getInvalidEmailStr();
		String expectedInvalidEmailStr = "Please enter a valid email address";
		Assert.assertEquals(actualInvalidEmailStr, expectedInvalidEmailStr,
				"[ERR] Invalid email message is not correct!");

	}

	@Step("Verify login with incorrect password")
	private void verifyIncorrectPassword(LoginFormComponent loginFormComp) {
		String actualInvalidPasswordStr = loginFormComp.getInvalidPasswordStr();
		String expectedInvalidPasswordStr = "Please enter at least 8 characters";
		Assert.assertEquals(actualInvalidPasswordStr, expectedInvalidPasswordStr,
				"[ERR] Invalid password message is not correct!");

	}

}
