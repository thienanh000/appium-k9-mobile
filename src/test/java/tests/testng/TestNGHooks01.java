package tests.testng;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGHooks01 extends BaseTestNG {

	@BeforeClass
	public void beforeClass() {
		System.out.println("\t\t" + this.getClass().getSimpleName() + " | Before Class");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("\t\t\t" + this.getClass().getSimpleName() + " | Before Method");
	}

	@Test(priority = 2)
	public void testMethod01() {
//		throw new RuntimeException("FAILED");
		System.out.println(this.getClass().getSimpleName() + " | Test Method 01");
	}

	@Test(priority = 1, dependsOnMethods = { "testMethod01" })
	public void testMethod02() {
		System.out.println(this.getClass().getSimpleName() + " | Test Method 02");
	}

	@Test()
	public void testMethod03() {
		System.out.println(this.getClass().getSimpleName() + " | Test Method 03");

		String expectedResult = "a";
		String actualResult = "b";

//		Verifier.verifyEquals(actualResult, expectedResult);
		// Hard Assertion
//		Assert.assertEquals(actualResult, expectedResult);
		Assert.assertEquals(actualResult, expectedResult, "[ERR] This is error!");
		System.out.println("Something else....!");

	}

	@Test()
	public void testMethod04() {
		String expectedResult = "a";
		String actualResult = "b";
		
		// Soft Assertion
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actualResult, expectedResult, "[ERR] This is error 1!");
		softAssert.assertEquals("abc", "xyz", "[ERR] This is error 2!");
//		softAssert.assertAll();



	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("\t\t\t" + this.getClass().getSimpleName() + " | After Method");
	}
}
