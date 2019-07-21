package testng;

import org.testng.annotations.Test;

import config.TestBase;
import pages.Dashboard;
import pages.HomePage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class RegressionTest {
	 static WebDriver dr;
	 HomePage hp;
	 TestBase tb;
	 Dashboard db;
	 
  //@Parameters({"user","pass"})
  @Test(priority=1,enabled=true, description="negative credentials Login Test", groups= {"sanity"})
  public void negativeLoginTestInvalidUser() {
	  hp.login("user", "pass");
	  Assert.assertTrue(hp.verifyEmailErrorMsg());
  }
  
  @Test(priority=5,enabled=true,groups= {"sanity", "Regression"})
  public void negativeLoginTestInvalidPass() {
	  hp.login(tb.USERNAME, "pass");
	  Assert.assertTrue(hp.verifyIncorrectCredentialErrorMsg());
  }
  
  @Test(priority=3,enabled=true, description="Valid credentials Login Test", groups={"Regression"})
  public void validLoginTest() {
	  hp.login(tb.USERNAME, tb.PASSWORD);
	  //Assert.assertTrue(db.verifyLogoutButton());
	  Assert.assertFalse(db.verifyLogoutButton());
  }
  
  @Test(priority=4,enabled=false,groups={"Regression"})
  public void countMenuOptions() 
  {
	  hp.login(tb.USERNAME, tb.PASSWORD);
	  assertTrue(db.getMenuItemCount()>0);
	  assertTrue(db.getMenuItemCount()==10);
  }
  
  @Test(priority=2, enabled=false, dependsOnMethods="countMenuOptions", groups={"sanity", "Regression"})
  public void verifyMenuSequence() {
	  hp.login(tb.USERNAME, tb.PASSWORD);
	  String[] str= {"Dashboard", "Catalog", "Sales", "Customers",
			  "Promotions","Content management", "Configuration", 
			  "System", "Reports",
			  "Help" };
	  
	  List<String> actual = db.getMenuItemList();
	  int i=0;
	  for(String s:actual)
	  {
		  assertTrue(s.contentEquals(str[i]));
		  System.out.println(s+" --> "+str[i] +"--> "+s.contentEquals(str[i]));
		  i++;
	  }
	  //	  System.out.print(actual);
  }
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() throws IOException {
	  tb=new TestBase();
	  dr = tb.getInstance();
	  hp=new HomePage(dr);
	  db=new Dashboard(dr);
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod() {
	  dr.quit();
  }

}
