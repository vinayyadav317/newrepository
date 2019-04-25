package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.util.DriverUtility;

public class drag_drop {
	
	@Test
	public void Test_dragdrop() throws InterruptedException
	{
		WebDriver driver=DriverUtility.getDriver("chrome");
		driver.get("https://demos.telerik.com/aspnet-ajax/treeview/examples/overview/defaultcs.aspx");
		driver.manage().window().maximize();
		
	
		WebElement from=driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_RadTreeView1']/ul/li/ul/li[3]/ul/li[1]/div/div/span"));
		WebElement to=driver.findElement(By.id("ctl00_ContentPlaceholder1_priceChecker"));
		
		Actions act=new Actions(driver);
		act.dragAndDrop(from, to).perform();
		//Thread.sleep(5000);
		
		
		WebDriverWait wait=new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("ctl00_ContentPlaceholder1_Label1"),"Drop a package here to check price"));
		
		
	    String result=driver.findElement(By.id("ctl00_ContentPlaceholder1_priceChecker")).getText();
	    System.out.println("the result="+result);
	    Assert.assertTrue(result.contains("$3999"));
	    driver.close();
	}
	
}
