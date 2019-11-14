package com.nationalguard.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.nationalguard.utility.Helper;

public class BaseTest {

	public ExtentTest test;
	public ExtentReports extent;
	public static WebDriver driver;
	public String browser = "Chrome";
	
	
	/* @BeforeSuite: The annotated method will be run before all tests in this suite have run.
	 * 
@AfterSuite: The annotated method will be run after all tests in this suite have run.

@BeforeTest: The annotated method will be run before any test method belonging to the classes inside the <test> tag is run.

@AfterTest: The annotated method will be run after all the test methods belonging to the classes inside the <test> tag have 
run.
@BeforeGroups: The list of groups that this configuration method will run before. This method is guaranteed to run 
shortly before the first test method that belongs to any of these groups is invoked.

@AfterGroups: The list of groups that this configuration method will run after. This method is guaranteed to run
 shortly after the last test method that belongs to any of these groups is invoked.
 
@BeforeClass: The annotated method will be run before the first test method in the current class is invoked.
@AfterClass: The annotated method will be run after all the test methods in the current class have been run.

@BeforeMethod: The annotated method will be run before each test method.

@AfterMethod: The annotated method will be run after each test method. */

	@BeforeTest

	public void setupSuite() {

		ExtentHtmlReporter reporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\Reports\\" + Helper.getCurrentDateTime() + ".html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);

	}

	@BeforeMethod
	public void setup() {

		

		if (browser.equals("Mozilla")) {
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "null");
			driver = new FirefoxDriver();

		} else if (browser.equals("Chrome")) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "null");
			driver = new ChromeDriver();

		} else if (browser.equals("IE")) {
			System.setProperty(InternetExplorerDriver.LOG_FILE, "null");
			driver = new InternetExplorerDriver();

		} else if (browser.equals("Edge")) {
			System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, "null");
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// URL
		driver.get("https://www.nationalguard.com/");

	}

	
	@AfterMethod
	// runs after every testcases
	// after test is finished ItestResults has all the information
	public void tearDownMethod(ITestResult result) throws IOException {

		// caputure screenshot on failure
		if (result.getStatus() == ITestResult.FAILURE) {
			// Helper.captureScreenshot(driver);
			test.fail("Test Failed",
					MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());

		} /*
			 * else if (result.getStatus() == ITestResult.SUCCESS) { //
			 * Helper.captureScreenshot(driver); test.pass("Test Passed",
			 * MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(
			 * driver)).build());
			 * 
			 * }
			 */

		driver.manage().deleteAllCookies();
		driver.quit();
		driver = null;

		extent.flush();

	}

}