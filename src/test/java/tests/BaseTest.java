package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import driver.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import platform.Platform;

public class BaseTest {

	private static final List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<DriverFactory>());
	private static ThreadLocal<DriverFactory> driverThread;
	private String udid;
	private String systemPort;
	
	protected AppiumDriver<MobileElement> getDriver(){
		return driverThread.get().getDriver(Platform.ANDROID, udid, systemPort);
	}

	@BeforeTest
	@Parameters({"udid", "systemPort"})
	public void initAppiumSession(String udid, String systemPort) {
		this.udid = udid;
		this.systemPort = systemPort;
		driverThread = new ThreadLocal<DriverFactory>(){
			@Override
			protected DriverFactory initialValue() {
				DriverFactory driverThread = new DriverFactory();
				driverThreadPool.add(driverThread);
				return driverThread;
			}
		};
	}

	@AfterTest(alwaysRun = true)
	public void quitAppiumSession() {
		driverThread.get().quitAppiumSession();
	}
}
