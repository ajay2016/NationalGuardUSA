package com.nationalguard.MavenDataDrivenNationalGuard;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.nationalguard.MavenDataDrivenNationalGuard.ExcelUtility.Xls_Reader;

public class NationalGuardGetStarted {

	WebDriver driver;
	String browser = "Chrome";

	// hooks with tags
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
	public void getStartedForm() {
		driver.findElement(By.name("button")).click();

		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\testdata\\testdata.xlsx");

		// get row count
		int rowCount = xls.getRowCount("RegTestData");

		// add colummn method
		// xls.addColumn("RegTestData", "Recruiter");

		// we need data from 2 row
		// parametarization
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {

			// store variables
			String firstName = xls.getCellData("RegTestData", "FirstName", rowNum);
			// System.out.println(firstName);
			String lastName = xls.getCellData("RegTestData", "LastName", rowNum);
			// System.out.println(lastName);
			String email = xls.getCellData("RegTestData", "Email", rowNum);
			// System.out.println(email);
			String phonenumber = xls.getCellData("RegTestData", "PhoneNumber", rowNum);
			// System.out.println(phonenumber);
			String zipcode = xls.getCellData("RegTestData", "ZipCode", rowNum);
			// System.out.println(zipcode);
			String birthdate = xls.getCellData("RegTestData", "Birthdate", rowNum);
			// System.out.println(birthdate);
			String phone = xls.getCellData("RegTestData", "Phone", rowNum);
			// System.out.println(phone);
			System.out.println("**********************");

			// pass data to rego form

			driver.findElement(By.name("get-started_first_name")).clear();
			driver.findElement(By.name("get-started_first_name")).sendKeys(firstName);

			driver.findElement(By.name("get-started_last_name")).clear();
			driver.findElement(By.name("get-started_last_name")).sendKeys(lastName);

			driver.findElement(By.name("get-started_email")).clear();
			driver.findElement(By.name("get-started_email")).sendKeys(email);

			driver.findElement(By.name("get-started_phone")).clear();
			driver.findElement(By.name("get-started_phone")).sendKeys(phonenumber);

			driver.findElement(By.name("get-started_zip")).clear();
			driver.findElement(By.name("get-started_zip")).sendKeys(zipcode);

			driver.findElement(By.name("get-started_submit")).click();

			driver.findElement(By.name("get-started_birth_date")).clear();
			driver.findElement(By.name("get-started_birth_date")).sendKeys(birthdate);

			driver.findElement(By.name("get-started_submit")).click();

			driver.findElement(By.name("get-started_phone")).clear();
			driver.findElement(By.name("get-started_phone")).sendKeys(phone);

			driver.findElement(By.name("get-started_submit")).click();

			driver.findElement(By.xpath("//label[@for='gender_1']")).click();
			driver.findElement(By.name("get-started_submit")).click();

			driver.findElement(By.xpath("//label[@for='military_1']")).click();
			driver.findElement(By.name("get-started_submit")).click();

			driver.findElement(By.xpath("//label[@for='high_school_0']")).click();
			driver.findElement(By.name("get-started_submit")).click();

			driver.findElement(By.xpath("//label[@for='college_0']")).click();
			driver.findElement(By.name("get-started_submit")).click();

			driver.findElement(By.xpath("//label[@for='college_degree_1']")).click();
			driver.findElement(By.name("get-started_submit")).click();

			Select s = new Select(driver.findElement(By.xpath("//*[@id='feet']")));
			s.selectByVisibleText("5");

			Select s1 = new Select(driver.findElement(By.xpath("//*[@id='inches']")));
			s1.selectByVisibleText("10");
			driver.findElement(By.name("get-started_submit")).click();

			driver.findElement(By.xpath("//label[@for='weight_1']")).click();
			driver.findElement(By.name("get-started_submit")).click();

			driver.findElement(By.xpath("//label[@for='motivation_1']")).click();
			driver.findElement(By.name("get-started_submit")).click();

			// final submit
			driver.findElement(By.name("get-started_submit")).click();
			String recruiter = driver.findElement(By.xpath("//*[@class='recruiter-contact']/div/h5")).getText();
			if (recruiter.isEmpty()) {
				System.out.println("There is no Recruiter appointed on   " + zipcode);
			}
			System.out.println("The Recruiter appointed to the " + zipcode + " is " + recruiter);

			// check Recruiter
			// write the data in the cell
			// xls.setCellData("RegTestData", "Status", rowNum, "Pass");

			// URL
			driver.get("https://www.nationalguard.com/");
			driver.switchTo().alert().accept();

			driver.findElement(By.name("button")).click();

		}
	}
	
	

	@AfterMethod
	public void closeBrowser() {
		driver.manage().deleteAllCookies();
		driver.quit();
		driver = null;

	}
}
