package testCases;


import java.io.IOException;
import java.time.Duration;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import newsAroundCognizant.BeCognizantPOM;
import newsAroundCognizant.NewsPage;


public class TestBeCognizant {
	WebDriver driver;
	BeCognizantPOM bm;
	NewsPage np;
	JavascriptExecutor js;
	WebDriverWait wait;
	
	//R1:Navigate to Be. Cognizant website and capture the user information.
	@BeforeClass
	@Parameters({"browser","url"})
	void setUp(String br,String appurl) throws InterruptedException
	{
		if(br.equals("chrome"))
		{
//		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		}
		else if(br.equals("edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		
		driver.get(appurl);
		Thread.sleep(5000);
		
	}
	
	@Test(priority=1)
	void testUser() throws InterruptedException, IOException
	{	
		bm=new BeCognizantPOM(driver);

		bm.userInformation();
		
		bm.takeScreenshot("tc1");

	}
	@Test(priority=2,dependsOnMethods={"testUser"})
	void testUserDetails() throws InterruptedException, IOException
	{
		bm=new BeCognizantPOM(driver);
		Thread.sleep(2000);
		bm.userDetails();
		
		//Thread.sleep(2000);
		bm.takeScreenshot("tc2");
		
		
	}
	//R2:Validate Around Cognizant News section is displayed in Be. Cognizant page.
	
	@Test(priority=3,dependsOnMethods={"testUserDetails"})
	void AroundCognizant() throws InterruptedException, IOException
	{
		
		bm.newsFetch();		
		
   
		//R3:Get all the News headers in Around Cognizant News Section and verify whether News header and Tool Tips of News are same.
		bm.workOnHeader();
		Thread.sleep(2000);
		
		bm.takeScreenshot("tc3");
		
	}
	
	
	//R4:Click on See All and Validate all the news are displayed in Be. Cognizant are displayed When user clicks on See All.
	@Test(priority=4)
	void seeAllNaviagtion() throws InterruptedException, IOException
	{

		
		bm.workOnseeAll();
		bm.takeScreenshot("tc4");
		
		
	}
	//R5:Click on each News header. Verify the News header and Tooltip for each News.
	
	@Test(priority=5)
	void seeAllNewsHeader() throws IOException, InterruptedException 
	{
		//R6:Print all the details of the News.
		//R7:Repeat Step 5 and Step 6 for Top 5 News.
		
		np=new NewsPage(driver);
		Thread.sleep(2000);
		np.workOnNewsHeader();
		Thread.sleep(2000);
		np.takeScreenshot("tc5");
		
	}
	
	@AfterClass
	void tearDown() {
	driver.quit();
	}
	

}
