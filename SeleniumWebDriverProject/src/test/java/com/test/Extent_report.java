package com.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.record.common.ExtendedColor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.util.DriverUtility;

public class Extent_report {
	WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports reports;
	ExtentTest logger;
	
	@BeforeClass
	public void beforeclass()
	{
		driver=DriverUtility.getDriver("chrome");
		driver.get("http://demowebshop.tricentis.com/login");
		driver.manage().window().maximize();
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-hh-mm");
		
		String path=System.getProperty("user.dir")+"/extent-report/"+sdf.format(new Date())+".html";
		htmlreporter=new ExtentHtmlReporter(path);
		reports=new ExtentReports();
		reports.attachReporter(htmlreporter);
		reports.setSystemInfo("username","vinay");
		reports.setSystemInfo("hostname","localhost");
		reports.setSystemInfo("environment","test environment");
		
		htmlreporter.config().setReportName("test me app");
		htmlreporter.config().setTheme(Theme.DARK);
	}
	
	
	
	
	@Test
	public void passtest()
	{
	  logger=reports.createTest("passtest");
	  logger.log(Status.INFO,"passtest started");
	  Assert.assertTrue(true);
	}
	
	
	@Test
	public void failtest()
	{
		logger=reports.createTest("failtest");
		  logger.log(Status.INFO,"failtest started");
		  Assert.assertTrue(false);
	}
	
	@Test
	public void skiptest()
	{
		logger=reports.createTest("skiptest");
		  logger.log(Status.INFO,"skiptest started");
		  throw new SkipException("skip");
	}
	
	
	@AfterMethod
	public void aftermethod(ITestResult result)
	{
		
		if (result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(Status.FAIL,"THE TEST IS FAILED");
			
			TakesScreenshot ts=(TakesScreenshot) driver;
			File srcFile=ts.getScreenshotAs(OutputType.FILE);
			String imgpath=System.getProperty("user.dir")+"/extent-report/images/"+result.getMethod().getMethodName()+".png";
			
			try {
				FileUtils.copyFile(srcFile, new File(imgpath));
				logger.log(Status.INFO,result.getThrowable());
				logger.addScreenCaptureFromPath(imgpath,"failtest image");
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		else if (result.getStatus()==ITestResult.SUCCESS)
		{
			
			logger.log(Status.PASS, "THE TEST IS PASSED");
			logger.log(Status.INFO, MarkupHelper.createLabel("the test is passed", ExtentColor.GREEN));
		}
		else if (result.getStatus()==ITestResult.SKIP)
		{
			logger.log(Status.SKIP, "THE TEST IS SKIPPED");
			
		}
		
	}
	
	
	@AfterClass
	public void afterclass()
	{
		reports.flush();
	}
	
}
