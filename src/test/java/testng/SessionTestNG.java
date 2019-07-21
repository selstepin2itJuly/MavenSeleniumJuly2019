package testng;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class SessionTestNG {
  @Test
  public void f1() {
	  System.out.println("F1");
  }
  
  @Test
  public void f2() {
	  System.out.println("F2");
  }
  
  @Test
  public void f3() {
	  System.out.println("F3");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("BMethod");
  }
  

  @AfterMethod
  public void afterMethod() {
	  System.out.println("AMethod");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("BClass");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("AClass");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("BTest");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("ATest");
  }

  @BeforeSuite
  public void beforeSuite() {
  
	  System.out.println("BSuite");}

  @AfterSuite
  public void afterSuite() {
	  System.out.println("ASuite");
  }

}
