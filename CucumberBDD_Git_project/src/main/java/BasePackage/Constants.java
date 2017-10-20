package BasePackage;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Constants {
	
	public static WebDriver driver;
	public	static Properties property;
	public static Logger mylog;
    public	static String applurl;
	public	static String username;
	public	static String password;
	
	 @Test(priority= 0)
		public static void logMethod(){
			 mylog = Logger.getLogger(Logger.class.getName());
			DOMConfigurator.configure("C:\\Aparna\\Workspace_project\\cucumberBDD_Project\\Log4j.xml");
			//mylog.info("invoke Browser")
		
		}
	@Test
	public static void loadData()throws Exception{
		
		property = new Properties();
		File file = new File("C:\\Aparna\\Workspace_project\\cucumberBDD_Project\\Configuration\\Config.properties");
		FileReader obj = new FileReader(file);
		property.load(obj);
		
		System.out.println(property.getProperty("url"));
		System.out.println(property.getProperty("username"));
		System.out.println(property.getProperty("password"));
		System.out.println(property.getProperty("browser"));
	
	}
	@Test
	public static String getObject(String Data) throws Exception{
		loadData();
		String data = property.getProperty(Data);
		System.out.println("Object ="+data);
		return data;
		
	}
    @Test(priority=1)
    public static void SetUp() throws Exception{
    	//loadData();
    	 applurl =getObject("url");
    	System.out.println("Appl Url ="+applurl);
    	System.setProperty("webdriver.chrome.driver","C:\\Aparna\\Workspace_project\\cucumberBDD_Project\\chromedriver_win32 (2)\\chromedriver.exe");
		driver=new ChromeDriver();
   
		System.out.println("==============SetUp Ready==============");
		mylog.info("Application stated");
			
	    driver.get("http://newtours.demoaut.com/");
	   		
	    Util.takeScreenshot(driver,"UrlLaunched");
	    Thread.sleep(2000);
	    System.out.println("Mercury Application launched");
	    System.out.println(driver.getTitle());
	      
     }
    @Test(priority=2)
    public static void verifyApplTitle() throws Exception{
    	
    	 Assert.assertTrue(driver.getTitle().contains("Mercury Tours"));
    	 System.out.println("Title of the Application Url =  "+driver.getTitle());
    }
    /*@Test
    public static void register() throws Exception{
    	
    	driver.findElement(By.xpath("//a[contains(.,'REGISTER')]")).click();
        System.out.println("Register page open success");
    }*/
    @Test(priority=3)
   	public static void login()throws Exception{
    	    // mylog.info("Login page");
    	
    	String uname = getObject("username");
    	String pw = property.getProperty("password");
    	     driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(uname);
			 driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pw);
			 
			 Thread.sleep(2000);
			 //Util.takeScreenshot(driver,"login");
			 
			 driver.findElement(By.xpath("//input[@name='login']")).click();
			 Thread.sleep(20000);
					
		     System.out.println("Login Success");
	}
    @Test(priority=4)
   	public static void closeBrowser()throws Exception{
    	
    	driver.quit();
    }

}