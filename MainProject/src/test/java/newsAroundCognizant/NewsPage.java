package newsAroundCognizant;

import java.io.File;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class NewsPage {
	WebDriver driver;
	JavascriptExecutor js;
	WebDriverWait wait;
	TakesScreenshot tk;

		
		public NewsPage(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver,this);
		}
		
		
		 @FindBy(xpath="//*[contains(@class,'cv_g_91bed31b')]/div/div/div/div[2]/div/div/a") List<WebElement> newsheader;
		    
		 @FindBy(id="title_text") WebElement innerNewsheader;
		
		
		
		public void workOnNewsHeader() throws InterruptedException
		{
			for(int i=1;i<6;i++)
			{
			newsheader.get(i).click();
			
			Thread.sleep(3000);
			
			Assert.assertEquals(innerNewsheader.getText(), innerNewsheader.getAttribute("title"));
			System.out.println(" Inner Header and inner header tooltip validated");
			
			JavascriptExecutor js=(JavascriptExecutor)driver;
			
			WebElement hv=driver.findElement(By.xpath("//*[@id=\"fa45f946-463e-428f-a84b-0bbbde09c3ba\"]/div/div/div/p"));
			Thread.sleep(2000);
			js.executeScript("arguments[0].scrollIntoView();",hv);
			Thread.sleep(2000);
			
			System.out.println("News "+(i));
			System.out.println(hv.getText());
			
			Thread.sleep(2000);
			
			
			driver.navigate().back();
		 }
				
	 }
		
		public void takeScreenshot(String name) throws IOException {
			 
			  tk=(TakesScreenshot) driver;
			  File source= tk.getScreenshotAs(OutputType.FILE);
			  File target = new File("C:\\Users\\2317405\\eclipse-workspace\\CASMainProject\\MainProject\\Screenshots\\"+name+".png");
			  FileHandler.copy(source, target);
		}

}
