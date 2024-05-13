package newsAroundCognizant;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;


public class BeCognizantPOM {

WebDriver driver;
JavascriptExecutor js;
WebDriverWait wait;
TakesScreenshot tk;

	
	public BeCognizantPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//WebElements locators
	@FindBy(id="O365_MainLink_MePhoto") WebElement userinfo;
	
	@FindBy(id="mectrl_headerPicture") WebElement userclick;
    
	@FindBy(id="mectrl_currentAccount_primary") WebElement txt_username;
    
    @FindBy(id="mectrl_currentAccount_secondary") WebElement txt_email;
    
    @FindBy(xpath="//div[@id='6a300658-3c93-45bc-8746-5964a4379bbf']") WebElement news;
    
    @FindBy(xpath="//*[contains(@class,\"bl_h_91bed31b\")]/div/div[2]/div/div[2]/a")List<WebElement> header;
    
    @FindBy(linkText="See all") WebElement seeall;
    
    @FindBy(xpath="//span[text()=\"News\"]") WebElement seeAllValidation;
    
   
    

	//Action Methods 
	public void userInformation() throws InterruptedException 
	{
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("O365_MainLink_Help_container")));
		Thread.sleep(3000);
		userinfo.click();
		
		
	}
	
	public void userDetails() throws InterruptedException
	{
		System.out.println("Printing the User Information");
		System.out.println(txt_username.getText());
		System.out.println(txt_email.getText());
		Thread.sleep(3000);
		userclick.click();
		
		
		
	}
	
	
	public void newsFetch()
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement ele=driver.findElement(By.xpath("//*[@id='5d7d4eec-cbe0-4c55-ae2e-f38d926d82a0']/div/div/div"));
		js.executeScript("arguments[0].scrollIntoView();",ele);
		
		Assert.assertEquals(news.isDisplayed(),true);
		
		System.out.println("Validated correctly");	
	}
	
	public void workOnHeader()
	{
		System.out.println("All headers are displayed:");
		System.out.println("-------------------------------------------");
		for(WebElement ele: header) {
			
			System.out.println(ele.getText());
			String tooltip=ele.getAttribute("title");
			
			Assert.assertEquals(ele.getText(),tooltip );
			//System.out.println("News header and Tool Tips of News are same in News section");	
			
		}	
		
	}
	
	public void workOnseeAll() throws InterruptedException
	{
		seeall.click();
		
		System.out.println("--------------------------------------------------");
		System.out.println(seeAllValidation.isDisplayed());
		Assert.assertTrue(seeAllValidation.isDisplayed());
		
		//Thread.sleep(3000);	
		
	}
	
	public void takeScreenshot(String name) throws IOException {
		 
		  tk=(TakesScreenshot) driver;
		  File source= tk.getScreenshotAs(OutputType.FILE);
		  File target = new File("C:\\Users\\2317405\\eclipse-workspace\\CASMainProject\\MainProject\\"+name+".png");
		  FileHandler.copy(source, target);
	}

	
	
}
