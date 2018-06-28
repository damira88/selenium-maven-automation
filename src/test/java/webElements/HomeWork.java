package webElements;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HomeWork {
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
	public void seleniumWebElementForm() throws InterruptedException {
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
		 Faker f=new Faker();
		 for (WebElement lists : list) {
			 lists.sendKeys(f.name().firstName());
			
		}
	
		 for (int i = 0; i < DropDowns.size(); i++) {
			 
			Select s=new Select(DropDowns.get(i));
			s.selectByIndex(f.number().numberBetween(1, 4));
			
		}
//		 for (WebElement eachCheckBox : checkBoxes) {
//			 eachCheckBox.click();
//		}
		 for (int i = 0; i < checkBoxes.size(); i+=3) {
			
			 checkBoxes.get(f.number().numberBetween(0, 3)+i).click();
		}
		 for (int i = 0; i < radioButtons.size(); i++) {
			 radioButtons.get(i).click();
			 Thread.sleep(1000);
			
		}
		 for (WebElement eachButton : buttons) {
			eachButton.click();
		}
		 driver.close();
		 
	}
	
	/**
	 * * Homework:
         *     Loop through each inputbox and enter random names√
         *  Loop through each dropDown and randomly select by index√
         *  Loop through each checkBoxes and select each one√
         *  Loop through each radioButton and click one by one by waiting one second intervals
         *  click all buttons
         */
	
}
