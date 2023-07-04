package tests.form;

import org.testng.annotations.Test;

import test_flows.form.FormFlow;
import tests.BaseTest;

public class FormTest extends BaseTest {

	@Test
	public void testFormInput() {
		System.out.println("SessionID: " + getDriver().getSessionId());
		FormFlow formFlow = new FormFlow(getDriver());
		formFlow.goToFormScreen();
		formFlow.fillTheForm();
		formFlow.verifyFormDisplay();
	}

}
