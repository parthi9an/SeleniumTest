package com.metron.test;

import org.testng.annotations.Test;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class GymgoLogin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://gymgo.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  // Valid login: Entered valid username and password
  @Test
  public void validLogin() throws Exception {
	driver.get(baseUrl + "/#/signin");
    assertEquals(driver.getTitle(), "GYMGO | Responsive Admin Theme");
    driver.findElement(By.linkText("Corporate Login")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("johny@gmail.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("Welcome01");
    driver.findElement(By.xpath("//body[@id='page-top']/div/div[2]/div/div/div/form/div[4]/button")).click();
    driver.findElement(By.cssSelector("div.ng_profileinfo")).click();
    driver.findElement(By.cssSelector("button.ng_btn.logout")).click();
    assertEquals(driver.getTitle(), "GYMGO | Responsive Admin Theme");
  }
  // Invalid Login: Valid user and invalid password
  @Test
  public void invalidLogin() throws Exception {
    driver.get(baseUrl + "/#/signin");
    assertEquals(driver.getTitle(), "GYMGO | Responsive Admin Theme");
    driver.findElement(By.linkText("Corporate Login")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("johny@gmail.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("hhhhh");
    driver.findElement(By.xpath("//body[@id='page-top']/div/div[2]/div/div/div/form/div[4]/button")).click();
    assertEquals(driver.findElement(By.xpath("//div[contains(@class,'formerror')]")).getText(), "No user matching this email and password");
  }
  
  // Valid User and Empty Password
  @Test
  public void emptyPassword() throws Exception {
	driver.get(baseUrl + "/#/signin");
	assertEquals(driver.getTitle(), "GYMGO | Responsive Admin Theme");
	driver.findElement(By.linkText("Corporate Login")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("johny@gmail.com");
    driver.findElement(By.xpath("//body[@id='page-top']/div/div[2]/div/div/div/form/div[4]/button")).click();
    assertEquals(driver.findElement(By.xpath("//div[contains(@class,'formerror')]")).getText(), "Password required to login");    
  }
  
  //Valid User and Empty Password
  @Test
  public void emptyUsername() throws Exception {
    driver.get(baseUrl + "/#/signin");
    assertEquals(driver.getTitle(), "GYMGO | Responsive Admin Theme");
    driver.findElement(By.linkText("Corporate Login")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("Welcome01");
    driver.findElement(By.xpath("//body[@id='page-top']/div/div[2]/div/div/div/form/div[4]/button")).click();
    assertEquals(driver.findElement(By.xpath("//div[contains(@class,'formerror')]")).getText(), "Email required to login");    
  }

  

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
