package ListnerClass;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base_Class.Baseclass1;

public class Listenerclass1 implements ITestListener{

		ExtentTest extentTest;
	
	public void onTestStart(ITestResult result) {
			extentTest = Baseclass1.getalreadyexiststest();
		    extentTest.log(Status.INFO, "Test : Started"+result.getName());
		    System.out.println("Test : Started"+result.getName()+"From Listener");
		    Reporter.log("Test Started");
	 }
	
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, "Test : Passed"+result.getName());
	    System.out.println("Test : Passed"+result.getName()+"From Listener");
	    Reporter.log("Test Passed");
	  }
	
	public void onTestFailure(ITestResult result) {
		extentTest.log(Status.FAIL, "Test : Failed"+result.getName());
		System.out.println("Test Failed "+ result.getName()+ " From listener");
		 Reporter.log("Test Failed");
	}
	
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.SKIP, "Test : Skipped"+result.getName());
	    System.out.println("Test : Skipped"+result.getName()+"From Listener");
	    Reporter.log("Test Skipped");
	 }

	
}
