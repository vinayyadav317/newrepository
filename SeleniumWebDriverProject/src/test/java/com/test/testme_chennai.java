package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.util.DriverUtility;

public class testme_chennai {
	
	@Test
	public void test_testme() throws InterruptedException
	{
		WebDriver driver=DriverUtility.getDriver("chrome");
		
		driver.get("http://10.232.237.143:443/TestMeApp");
		driver.manage().window().maximize();
		 
		
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.partialLinkText("AboutUs"))).perform();
		act.moveToElement(driver.findElement(By.partialLinkText("Our Offices"))).perform();
		act.moveToElement(driver.findElement(By.partialLinkText("Chennai"))).click().perform();;
		
				
	}

}
