package com.dice;

import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
		// set up chrome driver path
	WebDriverManager.chromedriver().setup();
	//invoke selenium driver 
	WebDriver driver=new ChromeDriver();
	/**
	 * test case :
Title:dice job search
launch browser and navigate to https://dice.com
full screen,

Expected : dice home page 
Step 2:
Insert search keyword and location then click on find tech jobs
Expected : search result page should be loaded
 Expected : page title should contain count of results along with search keyword
Count of results should be displayed on the page 
	 */
	
	// full screen
	driver.manage().window().fullscreen();
	// sets the universal wait time in case web page is slow
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	String url="https://dice.com";
	driver.get(url);
	String actual=driver.getTitle();
	String expected="Job Search for Technology Professionals | Dice.com";
	
	if(actual.equals(expected)) {
		System.out.println(" Step PASS: Dice HomePage is successfully loaded ");
	}else {
		System.out.println(" Step FAIL: Dice HomePage is NOT successfully loaded ");
		throw new RuntimeException("Step FAIL: Dice HomePage is NOT successfully loaded ");
	}
	
	String keyword="sdet";
	// clears the input box if something is written there
	driver.findElement(By.id("search-field-keyword")).clear();
	driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
	String location ="10005";
	driver.findElement(By.id("search-field-location")).clear();
	driver.findElement(By.id("search-field-location")).sendKeys(location);
	driver.findElement(By.id("findTechJobs")).click();
	String count =driver.findElement(By.id("posiCountId")).getText();
	System.out.println(count);
	int  countResult=Integer.parseInt(count.replace(",", ""));
	if(countResult>0) {
		System.out.println("  Step PASS: Keyword :"+keyword+" search returned "
	+ countResult +" results in "+location  );
	}else {
		System.out.println("Step FAIL: Keyword :"+keyword+" search returned "
				+ countResult +" results in "+location);
	}
	driver.close();
	}

}
