package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.util.DriverUtility;
import com.util.excel_read;

public class TestMeApp {
	WebDriver driver;
	@BeforeClass
	public void beforeclass()
	{
		driver=DriverUtility.getDriver("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp/login.htm");
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	}
	@AfterClass
	public void afterclass()
	{
		driver.close();
	}
	
	@Test(dataProvider = "Dp1")
	public void Testlogin(String username,String password) throws InterruptedException
	{
		driver.findElement(By.id("userName")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("Login")).click();
		WebDriverWait wait=new WebDriverWait(driver,100);
		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("SignOut")));
		Assert.assertTrue(driver.getTitle().contains("Home"));
		
		driver.findElement(By.partialLinkText("SignOut")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("SignIn")));
		driver.findElement(By.partialLinkText("SignIn")).click();
	}
	
	@DataProvider(name="Dp1")
	public Object[][] getData()
	{
		//Object[][] obj= {
		//		{"Lalitha","Password123"},{"admin","Password456"}
	    // 
	return excel_read.test_excelread("TestMe");
	
		

	}
				
	
}
