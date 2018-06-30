package WebTables;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class readWebTables {
String url="file:///Users/damira88/eclipse-workspace/selenium-maven-automation/src/test/java/WebTables/webtable.html";
WebDriver driver;
@BeforeClass
public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().fullscreen();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
}
@Test// read webtable data and print out
public void readScores() {
	driver.get(url);
	 WebElement table=driver.findElement(By.tagName("table"));
	 System.out.println(table.getText());
	 //find out how many rows in a table
	List<WebElement> rows= driver.findElements(By.xpath("//table[@id='worldcup']/tbody/tr") );
	System.out.println("Number of rows :"+rows.size());
	//print all table headers one by one
	List<WebElement> headers= driver.findElements(By.xpath("//table[@id='worldcup']/thead/tr/th") );
	for (WebElement eachHeader : headers) {
		System.out.println("\t"+eachHeader.getText());
	}
}
}
