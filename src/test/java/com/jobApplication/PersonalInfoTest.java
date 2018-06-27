package com.jobApplication;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.AnnotationUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import static org.testng.Assert.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PersonalInfoTest {
	
		WebDriver driver;
		String firstName;
		String lastName;
		int gender;
		String dateOfBirth;
		String email;
		String phoneNumber;
		String city;
		String state;
		String country;
		int annualSalary;
		List<String> technologies;
		int yearsOfExperience;
		String education;
		String gitHub;
		List<String> certifications;
		String additionalSkills;
		Faker data = new Faker();
		String tempDateOfBirth;
		String tempTechnologies = new String();
	
	@BeforeClass
	public void setUp() {	
		System.out.println("Setting Up WebDriver in BeforeClass...");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	
	@BeforeMethod	//runs once for the whole tests
	public void navigateToHomePage() {
		System.out.println("Navigating to homepage in @BeforeMethod....");
		driver.get("https://forms.zohopublic.com/murodil/form/JobApplicationForm/formperma/kOqgtfkv1dMJ4Df6k4_mekBNfNLIconAHvfdIk3CJSQ");	
		firstName = data.name().firstName();
		lastName = data.name().lastName();
		gender = data.number().numberBetween(1,3);
		dateOfBirth = data.date().birthday().toString();
		email = "damkavalet@mail.ru";
		phoneNumber = data.phoneNumber().cellPhone().replace(".", "-");
		city=data.address().cityName();
		state=data.address().stateAbbr();
		country=data.address().country();
		annualSalary=data.number().numberBetween(60000, 150000);
		technologies = new ArrayList<>();
		technologies.add("Java-" + data.number().numberBetween(1,4));
		technologies.add("HTML-" + data.number().numberBetween(1,4));
		technologies.add("Selenium WebDriver-" + data.number().numberBetween(1,4));
		technologies.add("Maven-"+ data.number().numberBetween(1,4));
		technologies.add("Git-"+ data.number().numberBetween(1,4));
		technologies.add("TestNG-"+ data.number().numberBetween(1,4));
		technologies.add("JUnit-"+ data.number().numberBetween(1,4));
		technologies.add("Cucumber-"+ data.number().numberBetween(1,4));
		technologies.add("API Automation-"+ data.number().numberBetween(1,4));
		technologies.add("JDBC-"+ data.number().numberBetween(1,4));
		technologies.add("SQL-"+ data.number().numberBetween(1,4));
		
		yearsOfExperience = data.number().numberBetween(1, 11);
		education = data.number().numberBetween(1, 4)+"";
		gitHub = "https://github.com/CybertekSchool/selenium-maven-automation.git";
		certifications = new ArrayList<>();
		certifications.add("Java OCA");
		certifications.add("AWS");
		additionalSkills = data.job().keySkills();
	}
	
	@Test
	public void submitFullApplication() {
		driver.findElement(By.xpath("//input[@name='Name_First']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='Name_Last']")).sendKeys(lastName);
		setGender(gender);
		tempDateOfBirth = setDateOfBirth(dateOfBirth);
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='countrycode']")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@name='Address_City']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='Address_Region']")).sendKeys(state);
		Select countryElem = new Select(driver.findElement(By.xpath("//select[@id='Address_Country']")));
		countryElem.selectByIndex(data.number().numberBetween(1, countryElem.getOptions().size()));
		country = countryElem.getFirstSelectedOption().getText();
		driver.findElement(By.xpath("//input[@name='Number']")).sendKeys(String.valueOf(annualSalary)+Keys.TAB);
		verifySalaryCalculations(annualSalary);
		driver.findElement(By.xpath("//em[.=' Next ']")).click();
		
		// SECOND PAGE ACTIONS
		setSkillSet(technologies);
		if(yearsOfExperience > 0) {
			driver.findElement(By.xpath("//a[@rating_value='"+ yearsOfExperience +"']")).click();
		}
		Select educationList = new Select(driver.findElement(By.xpath("//select[@name='Dropdown']")));
		educationList.selectByIndex(data.number().numberBetween(1, educationList.getOptions().size()));
		
		driver.findElement(By.xpath("//input[@name='Website']")).sendKeys("https://github.com/cihanaygunes/selenium-maven-automation.git");
		driver.findElement(By.xpath("//input[@value='Java OCA']")).click();
		driver.findElement(By.xpath("//input[@value='AWS']")).click();
		driver.findElement(By.xpath("//textArea[@name='MultiLine']")).clear();
		driver.findElement(By.xpath("//textArea[@name='MultiLine']")).sendKeys("Communication skills");
		driver.findElement(By.xpath("//button[@elname='submit']")).click();
		
		//Name
		String actual = driver.findElement(By.xpath("//label[@class='descFld']/div[1]")).getText().replace("Dear ", "").replace(",", "");
		String expected = firstName + " " + lastName;
		assertEquals(actual, expected);
		
		ipAddress();
		
		applicationID();
	
		//Gender
		expected = driver.findElement(By.xpath("//label[@class='descFld']/div[9]")).getText().substring(7);
		actual = gender == 1 ? "Male" : "Female";
		assertEquals(actual, expected);
		
		//BirthDay
		expected = driver.findElement(By.xpath("//label[@class='descFld']/div[10]")).getText().substring(15);
		actual = tempDateOfBirth;
		assertEquals(actual, expected);
		
		//Email
		expected = driver.findElement(By.xpath("//label[@class='descFld']/div[11]")).getText().substring(7);
		actual = email;
		assertEquals(actual, expected);
		
		//Phone
		expected = driver.findElement(By.xpath("//label[@class='descFld']/div[12]")).getText().substring(7);
		actual = phoneNumber;
		assertEquals(actual, expected);
		
		//Address
		expected = driver.findElement(By.xpath("//label[@class='descFld']/div[13]")).getText().substring(9);
		actual = city + ", " + state + ", " + country;
		assertEquals(actual, expected);
		
		//Salary
		expected = driver.findElement(By.xpath("//label[@class='descFld']/div[14]")).getText().substring(15);
		actual = "" + annualSalary;
		assertEquals(actual, expected);
		
		//Technologies
		expected = driver.findElement(By.xpath("//label[@class='descFld']/div[15]")).getText().substring(14);
		actual = tempTechnologies.substring(2);
		assertEquals(actual, expected);
		
		//Years of Experience
		expected = driver.findElement(By.xpath("//label[@class='descFld']/div[16]")).getText().substring(21);
		actual = "" + yearsOfExperience;
		assertEquals(actual, expected);
		
	}
	
	public void applicationID(){
		String expected = driver.findElement(By.xpath("//label[@class='descFld']/div[8]")).getText().substring(16);
		WebDriver driver2 = new ChromeDriver();
		driver2.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver2.manage().window().fullscreen();
		driver2.get("https://www.google.com/gmail/about/");
		driver2.findElement(By.xpath("//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")).click();
		driver2.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("damira.abdraimova8@gmail.com" + Keys.ENTER);
//		driver2.findElement(By.xpath("//input[@type='password']")).sendKeys("Kgkg78de"+Keys.ENTER);
		driver.findElement(By.cssSelector("#yDmH0d")).sendKeys("Kgkg78de"+Keys.ENTER);
		String actual = driver2.findElement(By.xpath("//span[@id=':3e']")).getText().substring(22);
		assertEquals(actual, expected);
		driver2.close();
	}
	
	public void ipAddress() {
		String expected = driver.findElement(By.xpath("//label[@class='descFld']/div[6]")).getText().substring(12);
		WebDriver driver2 = new ChromeDriver();
		driver2.manage().window().fullscreen();
		driver2.get("https://www.google.com/");
		driver2.findElement(By.id("lst-ib")).sendKeys("What is my ip address" + Keys.ENTER);
		String actual = driver2.findElement(By.xpath("//div[@class='pIpgAc xyYs1c XO51F xsLG9d']")).getText();
		assertEquals(actual, expected);
		driver2.close();
	}
	
	
	public void setSkillSet(List<String> tech) {
		
		for (String skill : tech) {
			String technology = skill.substring(0, skill.length()-2);
			int rate = Integer.parseInt(skill.substring(skill.length()-1));
		
		String level = "";
		
		switch(rate) {
		case 1:
			level = "Expert";
			break;
		case 2:
			level = "Proficient";
			break;
		case 3:
			level = "Beginner";
			break;
		default:
			fail(rate + " is not a valid level");
		}
		
		String xpath = "//input[@columnvalue='" + level + "'][@rowvalue='" + technology + "']";
        driver.findElement(By.xpath(xpath)).click();
        tempTechnologies += "  " + technology +  " : " + level;
	}
		
		
	
	}
	
	public void verifySalaryCalculations(int annual) {
		
		String monthly = driver.findElement(By.xpath("//input[@name='Formula']")).getAttribute("value");
		String weekly = driver.findElement(By.xpath("//input[@name='Formula1']")).getAttribute("value");
		String hourly = driver.findElement(By.xpath("//input[@name='Formula2']")).getAttribute("value");
		
		DecimalFormat formatter = new DecimalFormat("#.##");
		
		assertEquals(monthly,formatter.format(annual / 12.0));
		assertEquals(weekly,formatter.format(annual / 52.0));
		assertEquals(hourly,formatter.format(annual / 52.0/40.0));
		
	}
	
	//Sun Nov 27 04:04:22 EST 1977
	public String setDateOfBirth(String bday) {
		String [] pieces = bday.split(" ");
		String birthDay = pieces[2] + "-" + pieces[1] + "-" + pieces[5];
		driver.findElement(By.xpath("//input[@id='Date-date']")).sendKeys(birthDay);
		return birthDay;
	}
	
	public void setGender(int n) {
		if(n==1) {
			driver.findElement(By.xpath("//input[@value='Male']")).click();
		}else {
			driver.findElement(By.xpath("//input[@value='Female']")).click();
		}
	}
	
	@Test
	public void fullNameEmptyTest() {
		//firstly assert that you are on the correct page
		assertEquals(driver.getTitle(), "SDET Job Application");
		
		
		
		driver.findElement(By.xpath("//input[@elname='first']")).clear();
		driver.findElement(By.xpath("//input[@elname='last']")).clear();
		
		driver.findElement(By.xpath("//em[.=' Next ']")).click();
	
		//write xpath with tagname + id 
		//get the text and assert test equals to "Enter a value for this field."
		
		String expected = "Enter a value for this field.";
		String actual = driver.findElement(By.xpath("//p[@id='error-Name']")).getText();
		assertEquals(actual, expected);
	}
	
}
