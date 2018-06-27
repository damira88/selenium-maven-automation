package webElements;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
}
