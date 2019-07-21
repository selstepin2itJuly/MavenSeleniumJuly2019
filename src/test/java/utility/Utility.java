package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import config.TestBase;

public class Utility {
	
	
	public static String getDateStamp() 
	{
		Date date=new Date();
		System.out.println(date);
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_HH_mm_ss");
		String str = sdf.format(date);
		return str;
	}
	
    public static void capturScreen(WebDriver driver) throws IOException
    {
    	File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    	FileHandler.copy(f, new File(TestBase.SCREENSHOTLOCATION+"/"+getDateStamp()+"_image"+".jpg"));
    }
    
    public static void waitForElement(WebElement ele, WebDriver driver) {
    	
    	WebDriverWait wait= new WebDriverWait(driver, 15);
    	wait.until(ExpectedConditions.visibilityOf(ele));
    }
    
    public static void scrollToElement(WebElement ele, WebDriver driver) {
    	
    	JavascriptExecutor je=(JavascriptExecutor) driver;
    	je.executeScript("arguments[0].scrollIntoView(false);", ele);
    	je.executeScript("window.scrollBy(0,200);", ele);
    	
    }

}
