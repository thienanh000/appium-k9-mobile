package tests.parallel;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import test_data.DataObjectBuilder;
import test_flows.authentication.LoginFlow;
import tests.BaseTest;
import tests.authen.LoginTest.LoginCred;

public class Login extends BaseTest{

	@Description("Login test with data driven")
	@Test(dataProvider = "loginCredData", description = "Login Test")
	@TmsLink("JIRA-123")
	public void testLogin(LoginCred loginCred) {
		System.out.println("SessionID: " + getDriver().getSessionId());
		LoginFlow loginFlow = new LoginFlow(getDriver(), loginCred.getEmail(), loginCred.getPassword());
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
