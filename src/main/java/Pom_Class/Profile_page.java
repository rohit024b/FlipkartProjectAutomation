package Pom_Class;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Util_Class.utilclass1;

public class Profile_page extends utilclass1 {
WebDriver driver;
	
	@FindBy(xpath="//span[text()='Personal Information']")
	private WebElement pitext;
	
	@FindBy(xpath="//div[text()='Manage Addresses']")
	private WebElement manageadd;
	
	
	@FindBy(xpath="//div[text()='ADD A NEW ADDRESS']")
	private WebElement addnewaddress;
	
	@FindBy(xpath="//textarea[@name='addressLine1']")
	private WebElement fullAddress;
	
	@FindBy(xpath="//button[text()='Save']")
	private WebElement saveButton;
	
	
	
	
	public Profile_page(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
	public boolean checkuseronpage() 
	{
		try {
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Wait.until(ExpectedConditions.visibilityOf(pitext));
		}catch(Exception e) 
		{
			return false;
		}
		return true;
	}
	
	public void clickonmanageadd() throws InterruptedException 
	{
		Thread.sleep(5000);
		manageadd.click();
	}
	
	public void addnewaddress(List<String>Addressdetail) 
	{
		WebDriverWait Wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Wait.until(ExpectedConditions.visibilityOf(addnewaddress));
		addnewaddress.click();
		
		for(int i=1; i<=4; i++) 
		{
			
			driver.findElement(By.xpath("//input[@tabindex='"+i+"']")).sendKeys(Addressdetail.get(i-1));
		}
		fullAddress.sendKeys(Addressdetail.get(4));
		saveButton.click();
}
}
