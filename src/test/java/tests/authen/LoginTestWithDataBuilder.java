package tests.authen;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import platform.Platform;
import test_data.DataObjectBuilder;
import test_flows.authentication.LoginFlow;
import tests.authen.LoginTest.LoginCred;

public class LoginTestWithDataBuilder {

	@Test(dataProvider = "loginCredData")
	public void testLogin(LoginCred loginCred) {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);

		try {
			LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred.getEmail(), loginCred.getPassword());
			loginFlow.goToLoginScreen();
			loginFlow.login();
			loginFlow.verifyLogin();

		} catch (Exception e) {
			e.printStackTrace();
		}

		appiumDriver.quit();
	}

	@DataProvider
	public LoginCred[] loginCredData() {
		String filePath = "\\src\\test\\java\\test_data\\authen\\LoginCreds.json";
		return DataObjectBuilder.buildDataObject(filePath, LoginCred[].class);
	}

}
