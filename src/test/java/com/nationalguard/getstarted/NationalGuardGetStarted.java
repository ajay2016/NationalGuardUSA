package com.nationalguard.getstarted;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import com.nationalguard.base.BaseTest;
import com.nationalguard.utility.Xls_Reader;

public class NationalGuardGetStarted extends BaseTest {

	@Test
	public void getStartedForm() throws IOException {

		test = extent.createTest("Get Started Form");
		test.log(Status.INFO, "Get Started Form");

		Xls_Reader xls = new Xls_Reader(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\testdata\\testdata.xlsx");

		// get row count
		int rowCount = xls.getRowCount("RegTestData");

		// add colummn method
		// xls.addColumn("RegTestData", "Recruiter");

		// we need data from 2 row
		// parametarization
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {

			// store variables
			String firstName = xls.getCellData("RegTestData", "FirstName", rowNum);
			System.out.println(firstName);
			String lastName = xls.getCellData("RegTestData", "LastName", rowNum);
			System.out.println(lastName);
			String email = xls.getCellData("RegTestData", "Email", rowNum);
			System.out.println(email);
			String phonenumber = xls.getCellData("RegTestData", "PhoneNumber", rowNum);
			System.out.println(phonenumber);
			String zipcode = xls.getCellData("RegTestData", "ZipCode", rowNum);
			System.out.println(zipcode);
			String birthdate = xls.getCellData("RegTestData", "Birthdate", rowNum);
			System.out.println(birthdate);
			String phone = xls.getCellData("RegTestData", "Phone", rowNum);
			System.out.println(phone);
			String browser = xls.getCellData("RegTestData", "Browser", rowNum);
			System.out.println("**********************");

			openBrowser(browser);
			test.log(Status.INFO, "Opening browser  " + browser);

			test.log(Status.INFO, "https://www.nationalguard.com/");
			driver.get("https://www.nationalguard.com/");

			test.log(Status.INFO, "Clicking on Get Started button.");
			driver.findElement(By.name("button")).click();

			// pass data to rego form

			test.log(Status.INFO, "FirstName:  " + firstName);
			driver.findElement(By.name("get-started_first_name")).sendKeys(firstName);

			test.log(Status.INFO, "LastName:   " + lastName);
			driver.findElement(By.name("get-started_last_name")).sendKeys(lastName);

			test.log(Status.INFO, "Email:  " + email);
			driver.findElement(By.name("get-started_email")).sendKeys(email);

			test.log(Status.INFO, "PhoneNumber:  " + phonenumber);
			driver.findElement(By.name("get-started_phone")).sendKeys(phonenumber);

			test.log(Status.INFO, "Zipcode:   " + zipcode);
			driver.findElement(By.name("get-started_zip")).sendKeys(zipcode);

			test.log(Status.INFO, "Clicking on Submit button.");
			driver.findElement(By.name("get-started_submit")).click();

			test.log(Status.INFO, "Date of Birth:   " + birthdate);
			driver.findElement(By.name("get-started_birth_date")).sendKeys(birthdate);
			test.log(Status.INFO, "Clicking on Submit button.");
			driver.findElement(By.name("get-started_submit")).click();

			test.log(Status.INFO, "Phone:   " + phone);
			driver.findElement(By.name("get-started_phone")).sendKeys(phone);
			test.log(Status.INFO, "Clicking on Submit button.");
			driver.findElement(By.name("get-started_submit")).click();

			test.log(Status.INFO, "Clicking on Gender.");
			driver.findElement(By.xpath("//label[@for='gender_1']")).click();
			test.log(Status.INFO, "Clicking on Submit button.");
			driver.findElement(By.name("get-started_submit")).click();

			test.log(Status.INFO, "Clicking on Military Experience.");
			driver.findElement(By.xpath("//label[@for='military_1']")).click();
			test.log(Status.INFO, "Clicking on Submit button.");
			driver.findElement(By.name("get-started_submit")).click();

			test.log(Status.INFO, "Clicking on High School Diploma.");
			driver.findElement(By.xpath("//label[@for='high_school_0']")).click();
			test.log(Status.INFO, "Clicking on Submit button.");
			driver.findElement(By.name("get-started_submit")).click();

			test.log(Status.INFO, "Clicking on College.");
			driver.findElement(By.xpath("//label[@for='college_0']")).click();
			test.log(Status.INFO, "Clicking on Submit button.");
			driver.findElement(By.name("get-started_submit")).click();

			test.log(Status.INFO, "Clicking on College Degree.");
			driver.findElement(By.xpath("//label[@for='college_degree_1']")).click();
			test.log(Status.INFO, "Clicking on Submit button.");
			driver.findElement(By.name("get-started_submit")).click();

			test.log(Status.INFO, "Selecting height.");
			Select s = new Select(driver.findElement(By.xpath("//*[@id='feet']")));
			s.selectByVisibleText("5");

			Select s1 = new Select(driver.findElement(By.xpath("//*[@id='inches']")));
			s1.selectByVisibleText("10");
			test.log(Status.INFO, "Clicking on Submit button.");
			driver.findElement(By.name("get-started_submit")).click();

			test.log(Status.INFO, "Selecting Weight.");
			driver.findElement(By.xpath("//label[@for='weight_1']")).click();
			test.log(Status.INFO, "Clicking on Submit button.");
			driver.findElement(By.name("get-started_submit")).click();

			test.log(Status.INFO, "Selecting Motivation..");
			driver.findElement(By.xpath("//label[@for='motivation_1']")).click();
			test.log(Status.INFO, "Clicking on Submit button.");
			driver.findElement(By.name("get-started_submit")).click();

			// final submit
			test.log(Status.INFO, "Clicking on Final Submit button.");
			driver.findElement(By.name("get-started_submit")).click();
			String recruiter = driver.findElement(By.xpath("//*[@class='recruiter-contact']/div/h5")).getText();
			if (recruiter.isEmpty()) {
				System.out.println("There is no Recruiter appointed on   " + zipcode);
				test.log(Status.FAIL, "There is no Recruiter appointed on" + zipcode);
			}
			System.out.println("The Recruiter appointed to the " + zipcode + " is " + recruiter);
			test.log(Status.INFO, "The Recruiter appointed to the " + zipcode + " is " + recruiter);
			test.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(driver)).build());

			closeBrowser();

		}

	}

}
