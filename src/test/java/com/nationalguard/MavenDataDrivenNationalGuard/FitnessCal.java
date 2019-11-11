package com.nationalguard.MavenDataDrivenNationalGuard;


import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nationalguard.base.BaseTest;


public class FitnessCal extends BaseTest {

	@Test(priority = 1)
	public void fitnessCalculatorMale() throws IOException {

		
		test = extent.createTest("Male Fitness Calculator");
		test.log(Status.INFO, "Male Fitness Calculator Test Started");
		driver.findElement(By.xpath("//button[@class='hamburger tab-focus']")).click();
		driver.findElement(By.xpath("//a[@href='/fitness/calculator']")).click();

		// WebDriver wait
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[@for='gender_0' and @class='btn-outline']")));

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

		driver.findElement(By.xpath("//button[contains(., 'Confirm') ]")).click();
		test.log(Status.PASS, "Fitness result is displayed");
		//takeScreenShots();

		// adding screenshots to log
		//test.pass("Fitness result is displayed", MediaEntityBuilder
				//.createScreenCaptureFromPath(System.getProperty("user.dir") + "\\screenshotsFitnessCal\\"+Helper.getCurrentDateTime()+".png")
				//.build());

		System.out.println("Fitness result is displayed");

	}

	@Test(priority = 2)
	public void fitnessCalculatorFemale() throws IOException {
		// extent.attachReporter(reporter);
		test = extent.createTest("Female Fitness Calculator");
		test.log(Status.INFO, "Female Fitness Calculator Test Started");
		driver.findElement(By.xpath("//button[@class='hamburger tab-focus']")).click();
		driver.findElement(By.xpath("//a[@href='/fitness/calculator']")).click();

		// WebDriver wait
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[@for='gender_1' and @class='btn-outline']")));

		// female
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
		// click confirm
		driver.findElement(By.xpath("//button[contains(., 'Confirm') ]")).click();
		test.log(Status.PASS, "Fitness result is displayed");
		//takeScreenShots();
		// adding screenshots to log
		//test.pass("Result is displayed", MediaEntityBuilder
				//.createScreenCaptureFromPath(System.getProperty("user.dir") + "\\screenshotsFitnessCal\\"+Helper.getCurrentDateTime()+".png")
				//.build());

		System.out.println("Fitness result is displayed");

	}

	/*
	 * public static String takeScreenShots() {
	 * 
	 * String timeStamp;
	 * 
	 * TakesScreenshot scrShot = (TakesScreenshot) driver; File SrcFile =
	 * scrShot.getScreenshotAs(OutputType.FILE); timeStamp = new
	 * SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
	 * String screenshotpath = System.getProperty("user.dir") +
	 * "\\screenshotsFitnessCal\\" + timeStamp + ".png";
	 * 
	 * File DestFile = new File(screenshotpath); try { FileHandler.copy(SrcFile,
	 * DestFile); } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return screenshotpath; }
	 */

}
