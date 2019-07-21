package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.Utility;

public class HomePage {
	
	WebDriver dr;
	
	//constructor
	public HomePage(WebDriver driver) {
		this.dr=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="Email")
	WebElement username;
	
	@FindBy(id="Password")
	WebElement password;
	
	@FindBy(css="[value='Log in']")
	WebElement loginButton;
	
	@FindBy(id="RememberMe")
	WebElement rememberBox;
	
	@FindBy(id="Email-error")
	WebElement emailError;
	
	@FindBy(css="[novalidate='novalidate'] > div > ul >li")
	WebElement wrongPass;
	
	@FindBy(xpath="//div[@class='title']/strong")
	WebElement welcomeMsg;
	
	private void selectRememberCheckbox() {
		
		boolean b = rememberBox.isSelected();
		if(b==false) {
			rememberBox.click();
		}
		
	}
	
	private void unselectRememberCheckbox() {
		
		boolean b = rememberBox.isSelected();
		if(b==true) {
			rememberBox.click();
		}
		
	}
	
	public void login(String user, String pass){
		username.clear();;
		password.clear();;
		username.sendKeys(user);
		password.sendKeys(pass);
		loginButton.click();
	}
	
	public void loginWithRememberSelected(String user, String pass){
		username.sendKeys(user);
		password.sendKeys(pass);
		selectRememberCheckbox();
		loginButton.click();
	}
	
	public void loginWithRememberUnselected(String user, String pass){
		username.sendKeys(user);
		password.sendKeys(pass);
		unselectRememberCheckbox();
		loginButton.click();
	}
	
	public boolean verifyWelcomeMsg(String str) 
	{
		boolean b=false;
		Utility.waitForElement(welcomeMsg, dr);
		String s=welcomeMsg.getText();
		if(s.contains(str)) {
			b=true;
		}
		return b;
	}
	public boolean verifyEmailErrorMsg() 
	{
		boolean b=false;
		
		try {
			b=emailError.isDisplayed();
		}
		catch(Exception e) {}	
		return b;
	}	
	
	
	public boolean verifyIncorrectCredentialErrorMsg() 
	{
		boolean b=false;
		
		try {
			b=wrongPass.isDisplayed();
		}
		catch(Exception e) {}	
		return b;
	}
}

