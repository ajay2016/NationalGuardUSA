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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FitnessCal {

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

	@Test
	public void fitnessCalculatorMale() {
		driver.findElement(By.xpath("//button[@class='hamburger tab-focus']")).click();
		driver.findElement(By.xpath("//a[@href='/fitness/calculator']")).click();

		// WebDriver wait
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[@for='gender_0' and @class='btn-outline']")));

		// gender Male
		// WebElement maleButton = driver.findElement(By.xpath("//label[@for='gender_0'
		// and @class='btn-outline']"));
		// Actions action =new Actions(driver);
		// action.moveToElement(maleButton).click().perform();

		driver.findElement(By.xpath("//label[@for='gender_0' and @class='btn-outline']")).click();
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
		// Birthdate
		driver.findElement(By.id("birth-date")).sendKeys("11-30-2000");
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
		// Fitness Statistics
		driver.findElement(By.id("push-ups")).sendKeys("40");
		driver.findElement(By.id("sit-ups")).sendKeys("40");
		driver.findElement(By.id("two-mile")).sendKeys("10:05");
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();

		// Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir") +
		// "\\src\\com\\testdata\\testdata.xlsx");

		// get row count
		// int rowCount = xls.getRowCount("FitnessCalculatorTestData");

		// for (int rowNum = 2; rowNum <= rowCount; rowNum++) {
		// store variables
		// String genderdata = xls.getCellData("FitnessCalculatorTestData", "Gender",
		// rowNum);
		// System.out.println(genderdata);
		// Review
		// String gender = driver.findElement(By.id("gender")).getText();
		// Assert.assertEquals(gender, genderdata);
		// }
		/*
		 * String gender = driver.findElement(By.id("gender")).getText();
		 * System.out.println("Gender is " + gender);
		 */
		driver.findElement(By.xpath("//button[contains(., 'Confirm') ]")).click();
		takeScreenShots();
		System.out.println("Fitness result is displayed");

	}
	@Test
	public void fitnessCalculatorFemale() {
		driver.findElement(By.xpath("//button[@class='hamburger tab-focus']")).click();
		driver.findElement(By.xpath("//a[@href='/fitness/calculator']")).click();

		// WebDriver wait
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[@for='gender_1' and @class='btn-outline']")));

		
		//female
		driver.findElement(By.xpath("//label[@for='gender_1' and @class='btn-outline']")).click();
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
		
		
		// Birthdate
		driver.findElement(By.id("birth-date")).sendKeys("11-30-2000");
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
		// Fitness Statistics
		driver.findElement(By.id("push-ups")).sendKeys("40");
		driver.findElement(By.id("sit-ups")).sendKeys("40");
		driver.findElement(By.id("two-mile")).sendKeys("10:05");
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
		//click confirm
		driver.findElement(By.xpath("//button[contains(., 'Confirm') ]")).click();
		takeScreenShots();
		System.out.println("Fitness result is displayed");

	}

	
	
	@AfterMethod
	public void closeBrowser() {
		driver.manage().deleteAllCookies();
		driver.quit();
		driver = null;

	}
	
	public static  void takeScreenShots() {
		String timeStamp;
		TakesScreenshot scrShot =(TakesScreenshot)driver;
		File SrcFile= scrShot.getScreenshotAs(OutputType.FILE);
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()); 
		File DestFile=new File(System.getProperty("user.dir")+"\\screenshotsFitnessCal\\"+timeStamp+".png");
		try {
			FileHandler.copy(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
