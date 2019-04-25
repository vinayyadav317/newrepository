package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.util.DriverUtility;

public class key_actions {
	
	@Test
	public void test_keys() throws InterruptedException
	{
	WebDriver driver=DriverUtility.getDriver("chrome");
		
		driver.get("http://10.232.237.143:443/TestMeApp");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		WebElement search=driver.findElement(By.id("myInput"));
		Actions act1=new Actions(driver);
		act1.keyDown(search,Keys.SHIFT).perform();
		act1.sendKeys("b").pause(3000).sendKeys("a").pause(3000).sendKeys("g").perform();;
		act1.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'Hand bag')]"))).click().perform();
	
		driver.findElement(By.cssSelector("input[value='FIND DETAILS']")).click();
		
		Thread.sleep(5000);
		
		WebElement div=driver.findElement(By.className("row"));
		
		//Assert.assertTrue(condition);
		driver.close();
	}

}
