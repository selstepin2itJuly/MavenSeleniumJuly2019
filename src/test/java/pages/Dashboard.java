package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Utility;

public class Dashboard {

	WebDriver dr;
	
	//constructor
	public Dashboard(WebDriver driver) {
		this.dr=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Logout")
	WebElement logout;
	
	@FindBy(css="[class='sidebar-menu tree'] > li >a > span")
	List<WebElement> menus;
	
	@FindBy(css="[class='sidebar-menu tree'] > li >a > span[1]")
	WebElement menusElement;
	
	public boolean verifyLogoutButton() 
	{
	   boolean b=false;
	   try {
		   Utility.waitForElement(logout, dr);
		   b=logout.isDisplayed();
	   }catch(Exception e) {}
	   
	   return b;
	}
	
	public int getMenuItemCount() 
	{
		return menus.size();
	}
	
	public List<String> getMenuItemList() 
	{
		List<String> menu = new ArrayList<String>();
		for(WebElement e:menus) {
			menu.add(e.getText());
		}
		return menu;
	}
}
