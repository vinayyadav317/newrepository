package com.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.util.DriverUtility;

public class Googlecromehomepage {
	@Test
	public void Testgooglehomepage() {
       //  System.setProperty("webdriver.gecko.driver","C:\\software\\geckodriver.exe");	
         WebDriver driver=DriverUtility.getDriver("ie");
         driver.manage().window().maximize();
        // driver.get("http://www.google.co.in");
        // String title=driver.getTitle();
        // Assert.assertEquals(title,"Google");
         
	}

}
