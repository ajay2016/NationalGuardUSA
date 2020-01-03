package com.nationalguard.guardpay;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.nationalguard.base.BaseTest;


public class GuardPayTest extends BaseTest {

	// Enlistment type : Enlisted
	@Test(priority = 1)
	public void guardpayEnlisted() throws IOException {

		test = extent.createTest("Guard Pay Enlisted");
		test.log(Status.INFO, "Guard Pay Enlisted");
		
		driver.findElement(By.xpath("//button[@class='hamburger tab-focus']")).click();
		driver.findElement(By.xpath("//a[@href='/pay/calculator']")).click();

		// wait
		wait(3);

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
		test.log(Status.PASS, "Annual total is displayed on the page");
		test.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(driver)).build());
	}

	// Enlistment type : Officer
	@Test(priority = 2)
	public void guardpayOfficer() throws IOException {

		test = extent.createTest("Guard Pay Officer");
		test.log(Status.INFO, "Guard Pay Officer");

		driver.findElement(By.xpath("//button[@class='hamburger tab-focus']")).click();
		driver.findElement(By.xpath("//a[@href='/pay/calculator']")).click();

		//  wait
		wait(3);
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
		test.log(Status.PASS, "Annual total is displayed on the page");
		test.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(captureScreenshot(driver)).build());

	}

}
