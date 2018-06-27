package com.dice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class XpathCSS {

	public static void main(String[] args) {
	WebDriverManager.chromedriver().setup();
	WebDriver driver =new ChromeDriver();
	driver.get("http://orbitz.com");
	driver.manage().window().fullscreen();
	driver.findElement(By.cssSelector(".icon.icon-packages-double")).click();
	//

	}

}
