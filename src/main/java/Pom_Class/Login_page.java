package Pom_Class;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Util_Class.utilclass1;



public class Login_page extends utilclass1 {


	@FindBy(xpath="(//input[@type='text'])[2]")
	private WebElement emailid;
	
	@FindBy(xpath="//input[@type='password']")
	private WebElement password;
	
	@FindBy(xpath="(//button[@type='submit'])[2]")
	private WebElement button;
	
	public Login_page(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void emailID() throws IOException
	{
		emailid.sendKeys(getConfigData("email"));
	}
	
	public void password() throws IOException
	{
		password.sendKeys(getConfigData("password"));
	}
	
	public void button()
	{
		button.click();
	}
}