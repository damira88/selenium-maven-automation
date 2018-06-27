package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookXpath {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://facebook.com");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("damira");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("damira");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		driver.findElement(By.xpath("//body[@class='hasLeftCol _2yq"
				+ " home composerExpanded _5vb_ fbx _-kb apple _61s0"
				+ " _605a o_6ks1te4la chrome webkit mac x2 Locale_ru_RU"
				+ " cores-gte4 _19_u hasAXNavMenubar']")).click();
	
	}

}
