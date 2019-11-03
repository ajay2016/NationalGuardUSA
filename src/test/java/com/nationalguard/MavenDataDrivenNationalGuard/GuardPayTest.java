package com.nationalguard.MavenDataDrivenNationalGuard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GuardPayTest {

	static WebDriver driver;
	String browser = "Chrome";

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

	// Enlistment type : Enlisted
	@Test
	public void guardpayEnlisted() {
		driver.findElement(By.xpath("//button[@class='hamburger tab-focus']")).click();
		driver.findElement(By.xpath("//a[@href='/pay/calculator']")).click();

		// WebDriver wait
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[@for='current_status_1' and @class='btn-outline']")));

		// select enlisted
		driver.findElement(By.xpath("//label[@for='current_status_1' and @class='btn-outline']")).click();
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
		// select rank
		Select s = new Select(driver.findElement(By.xpath("//select[@name='rank']")));
		s.selectByValue("E-1");

		// select years
		Select s1 = new Select(driver.findElement(By.xpath("//select[@name='years']")));
		s1.selectByValue("1");

		// continue
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
		// pay calculator page
		driver.findElement(By.xpath("//*[text()=\"Annual Total:\"]")).getText();
		System.out.println("Annual total is displayed on the page");
		takeScreenShots();

	}

	// Enlistment type : Officer
	@Test
	public void guardpayOfficer() {
		driver.findElement(By.xpath("//button[@class='hamburger tab-focus']")).click();
		driver.findElement(By.xpath("//a[@href='/pay/calculator']")).click();

		// WebDriver wait
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[@for='current_status_2' and @class='btn-outline']")));
		// select enlisted
		driver.findElement(By.xpath("//label[@for='current_status_2' and @class='btn-outline']")).click();
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
		// select rank
		Select s = new Select(driver.findElement(By.xpath("//select[@name='rank']")));
		s.selectByValue("O-7");

		// select years
		Select s1 = new Select(driver.findElement(By.xpath("//select[@name='years']")));
		s1.selectByValue("1");

		// continue
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
		// pay calculator page
		driver.findElement(By.xpath("//*[text()=\"Annual Total:\"]")).getText();
		System.out.println("Annual total is displayed on the page");
		takeScreenShots();

	}

	@AfterMethod
	public void closeBrowser() {
		driver.manage().deleteAllCookies();
		driver.quit();
		driver = null;

	}

	public static void takeScreenShots() {
		String timeStamp;
		TakesScreenshot scrShot = (TakesScreenshot) driver;
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		File DestFile = new File(System.getProperty("user.dir") + "\\screenshotsGuardPay\\" + timeStamp + ".png");
		try {
			FileHandler.copy(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
