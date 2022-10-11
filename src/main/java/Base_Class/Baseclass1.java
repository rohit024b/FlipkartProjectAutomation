package Base_Class;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
public class Baseclass1 {
	
static WebDriver driver;
static ExtentHtmlReporter htmlreporter;
static ExtentReports reports;
static ExtentTest extentTest;

	
	public static WebDriver getDriver(String browser)
	{
		if(driver==null) {
		if(browser.equals("chrome")) {
		//WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\browser\\chromedriver.exe");
		driver = new ChromeDriver();
		}else 
		{
			//WebDriverManager.firefoxdriver().setup();
		System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\browser\\geckodriver.exe");
			driver = new FirefoxDriver();	
				
		}
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
	}
		return driver;
	}
	
	public static void unloaddriver() 
	{
		driver=null;
	}
	
	public static ExtentHtmlReporter gethtmlreporter() 
	{
		if(htmlreporter==null) 
		{
			htmlreporter = new ExtentHtmlReporter("reports.html");
		}
		return htmlreporter;	
	}
	
	public static ExtentReports gethtmlreports() 
	{
		if(reports==null) 
		{
			reports = new ExtentReports();
			reports.attachReporter(htmlreporter);
		}
		return reports;	
	}
	
	public static ExtentTest getTests(String testName) 
	{
		extentTest = reports.createTest(testName);
		return extentTest;
	}
	
	public static ExtentTest getalreadyexiststest() 
	{
		return extentTest;
	}
	

}
