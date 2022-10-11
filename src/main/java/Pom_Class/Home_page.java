package Pom_Class;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Util_Class.utilclass1;

public class Home_page extends utilclass1{
WebDriver driver;
	
	@FindBy(xpath="//div[text()='Akash']")
	private WebElement profilename;
	
	@FindBy(xpath="	//div[text()='My Profile']")
	private WebElement myprofiletext;
	
	
	@FindBy(xpath="//input[@type='text']")
	private WebElement Searchfield;
	
	@FindBy(xpath="//div[@class='_1AtVbE']")
	private WebElement textOnProductList;
	
	@FindBy(xpath="//div[@class='_2kHMtA']")
	private WebElement productList;
	
	@FindBy(xpath="//div[@class='_30jeq3 _1_WHN1']")
	private List<WebElement> lowestpricelist;
	
	
	public Home_page(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		this.driver = driver;
	}
	
	public String profilename() 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement newText = wait.until(ExpectedConditions.visibilityOf(profilename));
		String pname = newText.getText();
		return pname;
	}
	
	public void Seachfield() throws InterruptedException 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement newElement = wait.until(ExpectedConditions.visibilityOf(Searchfield));
		newElement.sendKeys("realme");
		newElement.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
	}
	
	public String getvalidText() throws InterruptedException 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(productList));
		String actualText = textOnProductList.getText();
		Thread.sleep(3000);
		return actualText;
		
	}
	
	public String getlowestpricelist() 
	{
		List<Integer>pricelist=new ArrayList<>();
		for(WebElement priceElement: lowestpricelist) 
		{
			pricelist.add(Integer.parseInt(priceElement.getText().replace("₹","").replace(",","")));
		}
		System.out.println(pricelist);
		Collections.sort(pricelist);
		return pricelist.get(0).toString();
		
	}
	
	public void switchTopage(int a) 
	{
		driver.findElement(By.xpath("//a[@class='ge-49M' and text()='"+a+"']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[@class='ge-49M' and text()='"+a+"']")));
	}
	
	public void hoveronprofile() 
	{
		Actions act = new Actions(driver);
		act.moveToElement(profilename).perform();
		
	}
	
	public void clickonprofile() 
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;		
		js.executeScript("arguments[0].click();", myprofiletext);
		//myprofiletext.click();
	}
}
