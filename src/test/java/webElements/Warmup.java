package webElements;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Warmup{
	public static void main(String[] args) {
			
	WebDriverManager.chromedriver().setup();
	WebDriver driver = new ChromeDriver();
	driver.manage().window().fullscreen();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("https://www.amazon.com/s/ref=nb_sb_noss_1?url=search-alias%3Daps&field-keywords=wooden+spoon");
	List <WebElement>list = driver.findElements(By.xpath("//div[@id='atfResults']//ul[@id='s-results-list-atf']"));
//	for (WebElement webElement : list) {
//		System.out.println(webElement.getText());
//		System.out.println("=====================");
//	}
	List <WebElement>list2 =driver.findElements(By.xpath("//div[@class='s-item-container']"));
	
	for (WebElement webElement : list2) {
		System.out.println(webElement.getText()+"list2");
		
	}
	}
}
//	List <WebElement>list3 =driver.findElements(By.xpath("//h2"));
//	for (WebElement webElement : list3) {
//		System.out.println(webElement.getText()+"LIST3");
//		System.out.println("=====================");
//	}
