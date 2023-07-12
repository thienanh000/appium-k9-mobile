package tests;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.google.common.reflect.ClassPath;

import driver.MobileCapabilityTypeEx;
import platform.Platform;

public class Main implements MobileCapabilityTypeEx {

	public static void main(String[] args) throws IOException {
		// TODO: search tại sao info.load()
		/**
		 * testsClasses.add(info.load());
		 * 
		 * đoạn sau là gì: final ClassLoader loader =
		 * Thread.currentThread().getContextClassLoader(); đoạn for nghĩa là gì
		 */

		// Get all test classes		
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> testClasses = new ArrayList<>();

        for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            String classInfoName = info.getName();
            boolean startWithTestDot = classInfoName.startsWith("tests.");
            boolean isBaseTestClass = classInfoName.startsWith("tests.BaseTest");
            boolean isMainClass = classInfoName.startsWith("tests.Main");

            if(startWithTestDot && !isBaseTestClass && !isMainClass){
                testClasses.add(info.load());
            }
        }

		// Get platform
		String platformName = System.getProperty("platform");
		if (platformName == null) {
			throw new IllegalArgumentException("[ERR] Please provide platform via -Dplatform");
		}

		try {
			Platform.valueOf(platformName);
		} catch (Exception e) {
			throw new IllegalArgumentException("[ERR] We don't support platform " + platformName + ", support platform " + Arrays.toString(Platform.values()));
		}

		// Devices under test
		List<String> iPhoneDeviceList = Arrays.asList("iPhone 12", "iPhone 13");
		List<String> androidDeviceList = Arrays.asList("RF8RC1VR7EA", "emulator-6974");
		List<String> deviceList = platformName.equalsIgnoreCase("ios") ? iPhoneDeviceList : androidDeviceList;

		// Assign test classes into devices
		final int testNumEachDevice = testClasses.size() / deviceList.size();
		Map<String, List<Class<?>>> desiredCaps = new HashMap<String, List<Class<?>>>();

		for (int deviceIndex = 0; deviceIndex < deviceList.size(); deviceIndex++) {
			int startIndex = deviceIndex * testNumEachDevice;
			boolean isTheLastDevice = deviceIndex == deviceList.size() - 1;
			int endIndex = isTheLastDevice ? testClasses.size() : (startIndex + testNumEachDevice);
			List<Class<?>> subTestList = testClasses.subList(startIndex, endIndex);
			desiredCaps.put(deviceList.get(deviceIndex), subTestList);
		}

		// Build dynamic test suite
		TestNG testNG = new TestNG();
		XmlSuite suite = new XmlSuite();
		suite.setName("Regression");

		List<XmlTest> allTests = new ArrayList<XmlTest>();
		for (String deviceName : desiredCaps.keySet()) {
			XmlTest test = new XmlTest(suite);
			test.setName(deviceName);
			List<XmlClass> xmlClasses = new ArrayList<XmlClass>();
			List<Class<?>> dedicatedClasses = desiredCaps.get(deviceName);
			for (Class<?> dedicatedClass : dedicatedClasses) {
				xmlClasses.add(new XmlClass(dedicatedClass.getName()));
			}

			test.setXmlClasses(xmlClasses);
			test.addParameter(UDID, deviceName);
			test.addParameter(PLATFORM_NAME, platformName);
			test.addParameter(PLATFORM_VERSION, "15.0");
			test.addParameter(SYSTEM_PORT, String.valueOf(new SecureRandom().nextInt(1000) + 8300));
			allTests.add(test);
		}

		suite.setTests(allTests);
		suite.setParallel(XmlSuite.ParallelMode.TESTS);
		suite.setThreadCount(10);

		System.out.println(suite.toXml());

//		// Add test suit into suite list
//		List<XmlSuite> suites = new ArrayList<XmlSuite>();
//		suites.add(suite);
//
//		// Involve run method
//		testNG.setXmlSuites(suites);
//		testNG.run();
	}
}
