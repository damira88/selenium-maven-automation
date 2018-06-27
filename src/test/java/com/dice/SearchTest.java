package com.dice;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchTest {
	/*
	 * 1.Go to amazon.com
	 * 2.Enter searchtermSelenium book
	 * 3.Verify that a resultwith “Selenium Testing Tools Cookbook”
	 * is displayed anywhere in the results
	 * 4.Enter search term Java OCA book
	 * 5.Verify that a resultwith “Selenium Testing Tools Cookbook”
	 * is not displayed anywhere in the results
	 */
@Test
public void amazonSearchOne() throws InterruptedException {
	WebDriverManager.chromedriver().setup();
	WebDriver driver =new ChromeDriver();
	driver.get("https://amazon.com");
	driver.manage().window().fullscreen();
	String str="Selenium testing tool cookBook";
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys(str+Keys.ENTER);
	String xpath="//h2[@class='a-size-medium s-inline  s-access-title  a-text-normal'][.='Selenium Testing Tools Cookbook']";
	//isDisplayed() ---> returns true if the element is displayed on the page
	Assert.assertTrue(driver.findElement(By.xpath(xpath)).isDisplayed());
	driver.findElement(By.id("twotabsearchtextbox")).clear();
//	  4.Enter search term Java OCA book
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Java OCA Book"+Keys.ENTER);
//	 * 5.Verify that a resultwith “Selenium Testing Tools Cookbook”
//	System.out.println(driver.findElement(By.xpath(xpath)).isDisplayed());
	Thread.sleep(2000);
//	Assert.assertTrue(false);
//	try {
		//if the element is in the html , this line will handle
		Assert.assertFalse(driver.findElement(By.xpath(xpath)).isDisplayed());
//	} catch (NoSuchElementException e) {
		// if the element is not in the html at all, exception will be thrown
		//since we verify smt that doesnt exist NoSuchElementException
//		means test should pass
//		e.printStackTrace();
//	}
	
	
	
	//driver.close();
}
}
