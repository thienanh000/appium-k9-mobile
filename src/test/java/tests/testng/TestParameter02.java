package tests.testng;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParameter02 {

	@Test
	@Parameters({"udid", "systemPort"})
	public void testParameter(String udid, String systemPort){
		System.out.printf("Udid: %s | systemPort: %s\n", udid, systemPort);
	}
}
