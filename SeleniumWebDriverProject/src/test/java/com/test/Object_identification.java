package com.test;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.util.DriverUtility;

public class Object_identification {
	
	WebDriver driver;
	 @BeforeMethod
	 public void beforemethod()
	 {
		 driver=DriverUtility.getDriver("ie");
		 driver.manage().window().maximize();
	 }
	 @AfterMethod
	 public void aftermethod()
	 {
		 //driver.close();
	 }
	 @Test
	 public void testDemoWebShop()
	 {
		 driver.get("http://demowebshop.tricentis.com/login");
		 String ulr=driver.getCurrentUrl();
		 Assert.assertTrue(ulr.contains("webshop"));
		 WebElement email=driver.findElement(By.id("Email"));
		 email.sendKeys("askmail@gmail.com");
		 
		 driver.findElement(By.id("Password")).sendKeys("abc123");
		 driver.findElement(By.cssSelector("input[value='Log in']")).click();
	 }

}
