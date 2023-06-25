package test_flows.authentication;

import org.apache.commons.validator.EmailValidator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
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
		}
		if (!isEmailValid) {
			verifyIncorrectEmail(loginFormComp);
		}
		if (!isPasswordValid) {
			verifyIncorrectPassword(loginFormComp);
		}
	}

	// TODO: Homework
	private void verifyCorrectLoginCreds(LoginFormComponent loginFormComp) {
		String actualSucessInfo = loginFormComp.getSuccessInfoStr();
		String expectedSucessInfo = "Success";

		System.out.println("actualSucessInfo: " + actualSucessInfo);
		System.out.println("expectedSucessInfo: " + expectedSucessInfo);

	}

	private void verifyIncorrectEmail(LoginFormComponent loginFormComp) {
		String actualInvalidEmailStr = loginFormComp.getInvalidEmailStr();
		String expectedInvalidEmailStr = "Please enter a valid email address";

		System.out.println("actualInvalidEmailStr: " + actualInvalidEmailStr);
		System.out.println("expectedInvalidEmailStr: " + expectedInvalidEmailStr);

	}

	private void verifyIncorrectPassword(LoginFormComponent loginFormComp) {
		String actualInvalidPasswordStr = loginFormComp.getInvalidPasswordStr();
		String expectedInvalidPasswordStr = "Please enter at least 8 characters";

		System.out.println("actualInvalidPasswordStr: " + actualInvalidPasswordStr);
		System.out.println("expectedInvalidPasswordStr: " + expectedInvalidPasswordStr);

	}

}
