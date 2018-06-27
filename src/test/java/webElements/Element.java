package webElements;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Element {
	WebDriver driver;

	@BeforeClass //runs once for all tests
	public void setUp() {
		System.out.println("Setting up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	@Test
	public void WebElementExamples() {
		driver.get("https://forms.zohopublic.com/murodil/form/JobApplicationForm/"
				+ "formperma/kOqgtfkv1dMJ4Df6k4_mekBNfNLIconAHvfdIk3CJSQ");	
		
		WebElement email=driver.findElement(By.name("Email"));
		String value=email.getAttribute("value");
		String maxLength=email.getAttribute("maxlength");
		String type=email.getAttribute("type");
		String tag=email.getTagName();
		System.out.println("value: "+value+"\n"+"maxlength: "+maxLength+"\n"+
				"type:" +type+"\n"+"tagName : "+tag+"\n");
		Assert.assertEquals(value, "youremail@mail.com");
		email.clear();
		email.sendKeys("another@mail.com");
		WebElement country=driver.findElement(By.id("Address_Country"));
		Select selectCountry=new Select(country);
		String def=selectCountry.getFirstSelectedOption().getText();
		System.out.println(def);
		selectCountry.selectByIndex(127);
//		selectCountry.selectByValue("Kyrgyzstan");
		WebElement cSalary=driver.findElement(By.name("Number"));
		Assert.assertEquals(cSalary.isDisplayed(), true);
		driver.findElement(By.xpath("//em[.=' Next ']")).click();
		cSalary.sendKeys("123456");
		
	}

}
