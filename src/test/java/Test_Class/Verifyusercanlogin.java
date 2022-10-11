package Test_Class;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
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


public class Verifyusercanlogin {
	static WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports reports;
	ExtentTest extentTest;

	Login_page lp;
	Home_page hp;
	
	@BeforeClass
	@Parameters("browser")
	public void beforeclass(String browser)
	{
		htmlreporter = Baseclass1.gethtmlreporter();
		reports = Baseclass1.gethtmlreports();
		extentTest = Baseclass1.getTests("VerifyUserCanLogin");
		driver=Baseclass1.getDriver(browser);	
	}
	
	@BeforeMethod
	public void beforemethod() 
	{
		lp = new Login_page(driver);
		hp = new Home_page(driver);
		
	}

	@Test(priority =1)
	public void test1() throws IOException 
	{
		
		lp.emailID();
		extentTest.log(Status.INFO, "Entered Emil ID");
		Reporter.log("Enterd Emil ID");
		lp.password();
		extentTest.log(Status.INFO, "Entered Pass");
		Reporter.log("Enterd Pass");
		lp.button();
		extentTest.log(Status.INFO, "Click on submitbutton");
		
		String profilename = hp.profilename();
		
		Assert.assertEquals(profilename, "Akash","Profile name is not matching");
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
