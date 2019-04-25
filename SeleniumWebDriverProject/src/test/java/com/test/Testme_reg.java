package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.util.DriverUtility;

public class Testme_reg {
	
	WebDriver driver;
	
	@Test
	public void  Reg_page()
	{
	WebDriver driver=DriverUtility.getDriver("chrome");
	
	driver.get("http://10.232.237.143:443/TestMeApp/RegisterUser.htm");
	driver.manage().window().maximize();
	
//	driver.findElement(By.linkText("RegisterUser.htm")).click();
	
	driver.findElement(By.id("userName")).sendKeys("vinayyadav");
	String text=driver.findElement(By.id("err")).getText();
	Assert.assertEquals(text,"New");
	
		
			
		
		
		driver.findElement(By.cssSelector("input[data-validation-error-msg-required='Please enter First Name']")).sendKeys("vinay");
		driver.findElement(By.id("lastName")).sendKeys("A");
		//password
		driver.findElement(By.cssSelector("input[data-validation-length='6-15']")).sendKeys("abc123");
		
		driver.findElement(By.id("pass_confirmation")).sendKeys("abc123");
		driver.findElement(By.cssSelector("input[value='Male']")).click();
		
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("vinayayaduv@gmail.com");
		driver.findElement(By.cssSelector("input[placeholder='Mobile Number']")).sendKeys("7411899651");
		
		
		driver.findElement(By.className("ui-datepicker-trigger")).click();
		
		Select month=new  Select(driver.findElement(By.className("ui-datepicker-month")));
		month.selectByIndex(8);
		Select year=new Select(driver.findElement(By.className("ui-datepicker-year")));
		year.selectByValue("1996");
		
		driver.findElement(By.linkText("16")).click();
		Select ques=new Select(driver.findElement(By.id("securityQuestion")));
		ques.selectByIndex(0);

		
		driver.findElement(By.id("address")).sendKeys("malur ,karnataka");
		driver.findElement(By.id("answer")).sendKeys("malur");
		driver.findElement(By.cssSelector("input[value='Register']")).click();
		
		
	
	}
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
		


