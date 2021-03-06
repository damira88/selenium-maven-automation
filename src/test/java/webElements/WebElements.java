package webElements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {

	WebDriver driver;

	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	@Test
	public void myLinks() {
		driver.get("https://github.com");
		List<WebElement> links=driver.findElements(By.xpath("//a"));
		int numberOfTheLinks=links.size();
		List<String> list=new ArrayList<>();
		System.out.println("numberOfTheLinks : "+numberOfTheLinks);
		for (WebElement link : links) {
			if(!link.getText().isEmpty())
			System.out.println(link.getText());
			list.add(link.getText());
		}
			System.out.println(list);
			
	}
	@Test
	public void seleniumWebElementForm() {
		driver.get("https://forms.zohopublic.com/murodil/form/"
				+ "SeleniumWebElements/formperma/"
				+ "eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		 List <WebElement> list=driver.findElements(By.xpath("//input[@type='text']"));
		 System.out.println(list.size());
		 List <WebElement> DropDowns=driver.findElements(By.tagName("select"));
		 System.out.println(DropDowns.size());
		 List <WebElement> checkBoxes=driver.findElements(By.xpath("//input[@type='checkbox']"));
		 List <WebElement> radioButtons=driver.findElements(By.xpath("//input[@type='radio']"));
		 List <WebElement> buttons=driver.findElements(By.tagName("button"));
		 Assert.assertEquals(list.size(), 2);
		 Assert.assertEquals(DropDowns.size(), 3);
		 Assert.assertEquals(checkBoxes.size(), 9);
		 Assert.assertEquals(radioButtons.size(), 9, "Message will show if it fails");
		 Assert.assertEquals(buttons.size(), 1 , "Message will show if it fails");
		 
		 
		
	}
	@Test
	public void slideShow() throws InterruptedException {
		driver.get("https://www.hbloom.com/Gifts/birthday-flowers");
		List<WebElement> images = driver.findElements(By.tagName("img"));
		List<String> srcs = new ArrayList<>();
		
		for(WebElement flower: images) {
			srcs.add(flower.getAttribute("src"));
		}
	
		for (String link : srcs) {
			driver.get(link);
			Thread.sleep(1234);
		}
	
	}
	/**
	 * * Homework:
         *     Loop through each inputbox and enter random names
         *  Loop through each dropDown and randomly select by index
         *  Loop through each checkBoxes and select each one
         *  Loop through each radioButton and click one by one by waiting one second intervals
         *  click all buttons
         */
	
	
	 
}
