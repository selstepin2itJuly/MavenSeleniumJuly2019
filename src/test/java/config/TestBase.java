package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {
	
	private static WebDriver driver;
	public static Properties prop;
	private static FileInputStream inStream;
	private static String browser;
	public static String SCREENSHOTLOCATION;
	private static String ENVIRONMENT;
	private static String URL;
	private DesiredCapabilities cap;
	public String USERNAME;
	public String PASSWORD;
	
	
	public WebDriver getInstance() throws IOException {
		
		prop = new Properties();
		SCREENSHOTLOCATION=prop.getProperty("screenshotLocation");
		FileInputStream inStream=new FileInputStream(new File("./src/test/java/config/config.properties"));
		
		prop.load(inStream);
		
		browser=prop.getProperty("browser");
		
		USERNAME=prop.getProperty("username");
		PASSWORD=prop.getProperty("password");
		
		if(browser.equalsIgnoreCase("chrome")) {
			cap=new DesiredCapabilities();
			
			cap.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
			ChromeOptions options=new ChromeOptions();
			options.setAcceptInsecureCerts(false);
			options.addArguments("incognito");
			options.addArguments("disable-infobars");
			options.addArguments("disable-popup-blocking");
			//cap.setHeadless(true);
			options.merge(cap);
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromeAgent"));
			driver=new ChromeDriver(options);
		}else if(browser.equalsIgnoreCase("firefox")) 
		{
			cap=new DesiredCapabilities();
			cap.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
			FirefoxOptions options=new FirefoxOptions();
			options.setAcceptInsecureCerts(true);
			options.setCapability("incognito", true);
			options.setCapability("disable-infobars", true);
			options.setCapability("disable-popup-blocking", true);
			//cap.setHeadless(true);
			options.merge(cap);
			System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxAgent"));
			
			driver=new FirefoxDriver(options);
		}else if(browser.equalsIgnoreCase("Edge")) 
		{
			//System.setProperty("webdriver.edge.driver", prop.getProperty("EdgeAgent"));
			//cap=new DesiredCapabilities();
			driver=new EdgeDriver();
		}else 
		{
			Throwable error = new Throwable();
			error.initCause(null);
		}
			//Sync //implicit
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
			driver.manage().window().maximize();
			
			ENVIRONMENT=prop.getProperty("env");
			if(ENVIRONMENT.equalsIgnoreCase("qa")) 
			{
				URL=prop.getProperty("testURL");
			}else if(ENVIRONMENT.equalsIgnoreCase("sit")) 
			{
				URL=prop.getProperty("SITurl");
			}else if(ENVIRONMENT.equalsIgnoreCase("uat")) 
			{
				URL=prop.getProperty("UATurl");
			}
						
			driver.get(URL);
		
		return driver;
		
	}
}
