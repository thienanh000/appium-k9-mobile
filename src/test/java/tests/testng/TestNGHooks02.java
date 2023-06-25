package tests.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGHooks02 extends BaseTestNG {

	@BeforeClass
	public void beforeClass() {
		System.out.println("\t\t" + this.getClass().getSimpleName() + " | Before Class");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("\t\t\t" + this.getClass().getSimpleName() + " | Before Method");
	}

	@Test
	public void testMethod01() {
		System.out.println(this.getClass().getSimpleName() + " | Test Method 01");
	}

	@Test
	public void testMethod02() {
		System.out.println(this.getClass().getSimpleName() + " | Test Method 02");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("\t\t\t" + this.getClass().getSimpleName() + " | After Method");
	}
}
