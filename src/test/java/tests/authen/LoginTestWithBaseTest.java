package tests.authen;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import platform.Platform;
import test_data.DataObjectBuilder;
import test_flows.authentication.LoginFlow;
import tests.BaseTest;
import tests.authen.LoginTest.LoginCred;

public class LoginTestWithBaseTest extends BaseTest{

	@Test(dataProvider = "loginCredData")
	public void testLogin(LoginCred loginCred) {
		LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred.getEmail(), loginCred.getPassword());
		loginFlow.goToLoginScreen();
		loginFlow.login();
		loginFlow.verifyLogin();
		
	}

	@DataProvider
	public LoginCred[] loginCredData() {
		String filePath = "\\src\\test\\java\\test_data\\authen\\LoginCreds.json";
		return DataObjectBuilder.buildDataObject(filePath, LoginCred[].class);
	}

}
