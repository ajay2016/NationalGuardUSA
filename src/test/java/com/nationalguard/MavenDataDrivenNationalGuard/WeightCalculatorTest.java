package com.nationalguard.MavenDataDrivenNationalGuard;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import com.nationalguard.base.BaseTest;

public class WeightCalculatorTest extends BaseTest {

	@Test
	public void weightCalculatorMale() {

		test = extent.createTest("Weight CaLculator Test Male");
		test.log(Status.INFO, "Weight CaLculator Test Male");
		driver.findElement(By.xpath("//button[@class='hamburger tab-focus']")).click();
		driver.findElement(By.xpath("//a[@href='/weight/calculator']")).click();

		// WebDriver wait
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[@for='gender_0' and @class='btn-outline']")));

		driver.findElement(By.xpath("//label[@for='gender_0' and @class='btn-outline']")).click();
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();

		// age
		driver.findElement(By.xpath("//label[@for='age_0' and @class='btn-outline']")).click();
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();

		// Height Selection
		Select s = new Select(driver.findElement(By.xpath("//*[@id='feet']")));
		s.selectByVisibleText("5");

		Select s1 = new Select(driver.findElement(By.xpath("//*[@id='inches']")));
		s1.selectByVisibleText("10");
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
		// Military experience
		driver.findElement(By.xpath("//label[@for='military_exp_1']")).click();
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();

		// click confirm
		driver.findElement(By.xpath("//button[contains(., 'Confirm') ]")).click();

		// takescreenshot
		// takeScreenShots();

		System.out.println("Weight calculator result is displayed");
		test.log(Status.PASS, "Weight calculator result is displayed");

	}

	@Test
	public void weightCalculatorFemale() {
		test = extent.createTest("Weight CaLculator Test Female");
		test.log(Status.INFO, "Weight CaLculator Test Female");
		driver.findElement(By.xpath("//button[@class='hamburger tab-focus']")).click();
		driver.findElement(By.xpath("//a[@href='/weight/calculator']")).click();

		// WebDriver wait
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//label[@for='gender_1' and @class='btn-outline']")));

		driver.findElement(By.xpath("//label[@for='gender_1' and @class='btn-outline']")).click();
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();

		// age
		driver.findElement(By.xpath("//label[@for='age_0' and @class='btn-outline']")).click();
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();

		// Height Selection
		Select s = new Select(driver.findElement(By.xpath("//*[@id='feet']")));
		s.selectByVisibleText("5");

		Select s1 = new Select(driver.findElement(By.xpath("//*[@id='inches']")));
		s1.selectByVisibleText("10");
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();
		// Military experience
		driver.findElement(By.xpath("//label[@for='military_exp_1']")).click();
		driver.findElement(By.xpath("//button[text()=\"Continue\"]")).click();

		// click confirm
		driver.findElement(By.xpath("//button[contains(., 'Confirm') ]")).click();

		// takescreenshot
		// takeScreenShots();

		System.out.println("Weight calculator result is displayed");
		test.log(Status.PASS, "Weight calculator result is displayed");

	}

}
