package tests.form;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Issue;
import test_flows.form.FormFlow;
import tests.BaseTest;

public class FormTest extends BaseTest {

	@Test
	@Issue("JIRA-321")
	public void testFormInput() {
		System.out.println("SessionID: " + getDriver().getSessionId());
		Assert.fail();
		FormFlow formFlow = new FormFlow(getDriver());
		formFlow.goToFormScreen();
		formFlow.fillTheForm();
		formFlow.verifyFormDisplay();
	}

}
