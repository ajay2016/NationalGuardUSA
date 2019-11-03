package com.nationalguard.MavenDataDrivenNationalGuard;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.nationalguard.MavenDataDrivenNationalGuard.ExcelUtility.Xls_Reader;

public class NationalGuardTest {

	static WebDriver driver = null;

	public static void main(String[] args) {

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//driver.get("https://reg.ebay.com.au/reg/PartialReg?ru=https%3A%2F%2Fwww.ebay.com.au%2F");
		
		//URL
		driver.get("https://www.nationalguard.com/");
		driver.findElement(By.name("button")).click();

		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir") + "\\src\\main\\java\\com\\testdata\\testdata.xlsx");

		// get row count
		int rowCount = xls.getRowCount("RegTestData");
		
		// add colummn method
		xls.addColumn("RegTestData", "Recruiter");

		// we need data from 2 row
		// parametarization
		for (int rowNum = 2; rowNum <= rowCount; rowNum++) {

			// store variables
			String firstName = xls.getCellData("RegTestData", "FirstName", rowNum);
			//System.out.println(firstName);
			String lastName = xls.getCellData("RegTestData", "LastName", rowNum);
			//System.out.println(lastName);
			String email = xls.getCellData("RegTestData", "Email", rowNum);
			//System.out.println(email);
			String phonenumber = xls.getCellData("RegTestData", "PhoneNumber", rowNum);
			//System.out.println(phonenumber);
			String zipcode = xls.getCellData("RegTestData", "ZipCode", rowNum);
			//System.out.println(zipcode);
			String birthdate = xls.getCellData("RegTestData", "Birthdate", rowNum);
			//System.out.println(birthdate);
			String phone = xls.getCellData("RegTestData", "Phone", rowNum);
			//System.out.println(phone);
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
			
			Select s  = new Select(driver.findElement(By.xpath("//*[@id='feet']")));
			s.selectByVisibleText("5");
			
			Select s1  = new Select(driver.findElement(By.xpath("//*[@id='inches']")));
			s1.selectByVisibleText("10");
			driver.findElement(By.name("get-started_submit")).click();
			
			driver.findElement(By.xpath("//label[@for='weight_1']")).click();
			driver.findElement(By.name("get-started_submit")).click();
			
			driver.findElement(By.xpath("//label[@for='motivation_1']")).click();
			driver.findElement(By.name("get-started_submit")).click();
			
			//final submit
			driver.findElement(By.name("get-started_submit")).click();
			String recruiter = driver.findElement(By.xpath("//*[@class='recruiter-contact']/div/h5")).getText();
			if(recruiter.isEmpty()) {
				System.out.println("There is no Recruiter appointed on   "+zipcode);
			}
			System.out.println("The Recruiter appointed to the "+ zipcode + " is " +recruiter);
			
						
			//check Recruiter
			// write the data in the cell
						xls.setCellData("RegTestData", "Recruiter", rowNum, recruiter);
			
			//URL
			driver.get("https://www.nationalguard.com/");
			driver.switchTo().alert().accept();

			driver.findElement(By.name("button")).click();

			

		}
		driver.quit();
	}

}
