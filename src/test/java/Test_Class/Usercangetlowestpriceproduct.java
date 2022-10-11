package Test_Class;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Base_Class.Baseclass1;
import Pom_Class.Home_page;
import Pom_Class.Login_page;
import Util_Class.utilclass1;

public class Usercangetlowestpriceproduct {
	static WebDriver driver;
	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest extentTest;
	Login_page lp;
	Home_page hp;
	
	
	@BeforeClass
	@Parameters("browser")
	public void beforeclass(String browser) 
	{
		htmlReporter = Baseclass1.gethtmlreporter();
		reports = Baseclass1.gethtmlreports();
		extentTest = Baseclass1.getTests("Usercangetlowestpriceproduct");
		driver = Baseclass1.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() 
	{
		lp = new Login_page(driver);
		hp = new Home_page(driver);
	}
	
	@Test(priority =1)
	public void VerifyUserCanSearchProduct() throws InterruptedException 
	{
		
		hp.Seachfield();
		String actualtext = hp.getvalidText();
		Assert.assertTrue(actualtext.contains("Showing 1 –"));
	}
	@Test(priority =2)
	public void verifythatusercangetlowestprice()
	{
		List<String>lowestprice = new ArrayList<>();
		for(int i=1; i<5; i++) 
		{
			if(i!=1) 
			{
				hp.switchTopage(i);
			}
			hp = new Home_page(driver);
			lowestprice.add(hp.getlowestpricelist());
		}
		System.out.println(lowestprice);
	}
	
		
	@AfterMethod
	public void aftermethod(ITestResult result) throws IOException 
	{
		if(result.getStatus() == ITestResult.SUCCESS) 
		{
			extentTest.log(Status.PASS, "Test : "+result.getName());
		}
		else if(result.getStatus()== ITestResult.FAILURE) 
		{
			String path = utilclass1.getScreenshot(driver, result.getName());
			extentTest.log(Status.FAIL, "Test: "+result.getName(), MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
		else if(result.getStatus()== ITestResult.SKIP) 
		{
			extentTest.log(Status.SKIP, "Test : "+result.getName());
		}
	}
	
	@AfterClass
	public void afterclass()
	{
		reports.flush();
	}
}
