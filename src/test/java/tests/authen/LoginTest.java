package tests.authen;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import platform.Platform;
import test_flows.authentication.LoginFlow;

public class LoginTest {

	@Test
	public void testLogin() {
		AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
		List<LoginCred> loginCreds = new ArrayList<LoginTest.LoginCred>();
		loginCreds.add(new LoginCred("teo", "12345678"));
		loginCreds.add(new LoginCred("teo@sth.com", "1234567"));
		loginCreds.add(new LoginCred("teo@sth.com", "12345678"));

		try {
			for (LoginCred loginCred : loginCreds) {
				LoginFlow loginFlow = new LoginFlow(appiumDriver, loginCred.getEmail(), loginCred.getPassword());
				loginFlow.goToLoginScreen();
				loginFlow.login();
				loginFlow.verifyLogin();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		appiumDriver.quit();
	}

	public static class LoginCred {
		private String email;
		private String password;

		public LoginCred(String email, String password) {
			this.email = email;
			this.password = password;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public String getPassword() {
			return password;
		}

		@Override
		public String toString() {
			return "LoginCred [email=" + email + ", password=" + password + "]";
		}

	}

}
