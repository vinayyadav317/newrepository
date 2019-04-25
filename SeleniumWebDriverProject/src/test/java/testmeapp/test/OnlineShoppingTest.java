package testmeapp.test;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testmeapp.utility.Drivers;

public class OnlineShoppingTest {
	
	WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports extent;
	ExtentTest logger;
	
	@AfterTest
	 public void endReportAfterTest()
	 {
	
		extent.flush();
		driver.findElement(By.linkText("SignOut")).click();
		driver.close();
		
	 }
	 
	@AfterMethod
	public void  getResultAfterMethod(ITestResult result)
	 {
        
		if (result.getStatus()==ITestResult.FAILURE)
		{
			logger.log(Status.FAIL,"THE TEST IS FAILED");
			
		
		}
		else if (result.getStatus()==ITestResult.SUCCESS)
		{
			
			logger.log(Status.PASS, "THE TEST IS PASSED");
			TakesScreenshot ts=(TakesScreenshot) driver;
			File srcFile=ts.getScreenshotAs(OutputType.FILE);
			String imgpath=System.getProperty("user.dir")+"/case-study/images/"+result.getMethod().getMethodName()+".png";
			
			try {
				FileUtils.copyFile(srcFile, new File(imgpath));
				logger.addScreenCaptureFromPath(imgpath,"passtest image");
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		
		
		else if (result.getStatus()==ITestResult.SKIP)
		{
			logger.log(Status.SKIP, "THE TEST IS SKIPPED");
			
		}
		
	 }
	@BeforeTest
	public void startReportBeforeTest()
	 {
		driver=Drivers.getDriver("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp");
		driver.manage().window().maximize();
		
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd-hh-mm");
		
		String path=System.getProperty("user.dir")+"/case-study/"+sdf.format(new Date())+".html";
		htmlreporter=new ExtentHtmlReporter(path);
		extent=new ExtentReports();
		extent.attachReporter(htmlreporter);
		extent.setSystemInfo("username","vinay");
		extent.setSystemInfo("hostname","localhost");
		extent.setSystemInfo("environment","test environment");
		
		htmlreporter.config().setReportName("test me app");
		htmlreporter.config().setTheme(Theme.DARK);
		
        
		
	 }
    @Test(priority = 3)
	public void testCart() throws InterruptedException
	{
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(By.partialLinkText("All Categories"))).perform();
		act.moveToElement(driver.findElement(By.partialLinkText("Electronics"))).click().perform();
		act.moveToElement(driver.findElement(By.partialLinkText("Travel Kit"))).click().perform();
		driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div/div/div/div[2]/center/a")).click();
		driver.findElement(By.partialLinkText("Cart")).click();
		driver.findElement(By.partialLinkText("Checkout")).click();
		driver.findElement(By.cssSelector("input[value='Proceed to Pay']")).click();
		Thread.sleep(5000);
		Assert.assertEquals(driver.getTitle(),"Payment Gateway");
		logger=extent.createTest("Adding to cart");
		logger.log(Status.INFO,"Adding cart is successful");
	}
	@Test(priority = 2)
	public void testLogin()
	{
		driver.findElement(By.id("userName")).sendKeys("aaaaa24");
		driver.findElement(By.id("password")).sendKeys("password");

		driver.findElement(By.cssSelector("input[value='Login']")).click();
		
		Assert.assertEquals(driver.getTitle(), "Home");
		
		logger=extent.createTest("Login");
		logger.log(Status.INFO,"Login is successful");
	}
	
	
	
	@Test(priority = 4)
	public void testPayment()
	{
	    driver.findElement(By.xpath("//div[@id='swit']/div/div/label/i")).click();
		driver.findElement(By.id("btn")).click();
		driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[1]")).sendKeys("123456");
		driver.findElement(By.xpath("//*[@id=\"horizontalTab\"]/div[2]/div/div/div/div/form/input[2]")).sendKeys("Pass@456");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
		driver.findElement(By.cssSelector("input[value='PayNow']")).click();
		
		boolean result=driver.findElement( By.tagName("td"))!= null;
		Assert.assertTrue(result);
		
		logger=extent.createTest("payment gateway");
		logger.log(Status.INFO,"payment gateway is successful");
	}
	
	
	
	
	@Test(priority = 1)
	public void testRegistration()
	{
		
		//driver.findElement(By.xpath("//*[@id='header']/div[1]/div/div/div[2]/div/ul/li[2]/a")).click();
		
		
		driver.findElement(By.partialLinkText("SignUp")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

		driver.findElement(By.id("userName")).sendKeys("aaaaa25");
		
		driver.findElement(By.id("firstName")).sendKeys("vinay");
		String avl= driver.findElement(By.id("err")).getText();
		Assert.assertEquals(avl, "Available");
		driver.findElement(By.id("lastName")).sendKeys("yadav");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("pass_confirmation")).sendKeys("password");
		driver.findElement(By.cssSelector("input[value='Male']")).click();
		driver.findElement(By.id("emailAddress")).sendKeys("abc@gmail.com");
		driver.findElement(By.id("mobileNumber")).sendKeys("7411899651");
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
		String text1=driver.findElement(By.xpath("//form[@action=\'loginhere.htm\']/fieldset/div[8]")).getText();
		Assert.assertEquals(text1,"User Registered Succesfully!!! Please login");
		
		logger=extent.createTest("Registration");
		logger.log(Status.INFO,"Registration is successful");


	}
	
	
	
	
	 

}
