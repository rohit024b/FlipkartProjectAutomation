package Test_Class;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
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
import Pom_Class.Profile_page;
import Util_Class.utilclass1;

public class Verfifyusercanaddnewadd {
	static WebDriver driver;
	ExtentHtmlReporter htmlreporter;
	ExtentReports reports;
	ExtentTest extentTest;
	Login_page lp;
	Home_page hp;
	Profile_page pp;
	
	
	@BeforeClass
	@Parameters("browser")
	public void beforeclass(String browser) 
	{
		htmlreporter = Baseclass1.gethtmlreporter();
		reports = Baseclass1.gethtmlreports();
		extentTest = Baseclass1.getTests("Verfifyusercanaddnewadd");
		driver = Baseclass1.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() 
	{
		lp = new Login_page(driver);
		hp = new Home_page(driver);
		pp = new Profile_page(driver);
	}
	
	@Test(priority =1)
	public void userlandonprofile()
	{
		hp.hoveronprofile();
		
		hp.clickonprofile();
		
		boolean onpage= pp.checkuseronpage();
		
		if(onpage) {
			System.out.println("Test case pass");
		}else {
			System.out.println("Test case faileddd");
		}
	}
	
	@DataProvider(name="Adressdata")
	public Object[][] getdata()
	{
		Object[][]k= {{"Akki","9825632156","411044","sus","A-14,narmada"},{"Akki1","9822056402","411044","pashan","A-25,Kaveri"}};
		return k;
	}
	
	@Test(priority = 2,dataProvider="Adressdata")
	public void AddnewAddress(String name,String mobileno, String pincode,String locality,String fulladd) throws InterruptedException 
	{
	pp.clickonmanageadd();
	List<String>Addressdetail=  Arrays.asList(name,mobileno,pincode,locality,fulladd);
	
	pp.addnewaddress(Addressdetail);
	
	}
	
	@AfterMethod()
	public void aftermetyhod(ITestResult result) throws IOException
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
	
	@AfterClass()
	public void afterclass() 
	{
		reports.flush();
		Baseclass1.unloaddriver();
		
	}
}
