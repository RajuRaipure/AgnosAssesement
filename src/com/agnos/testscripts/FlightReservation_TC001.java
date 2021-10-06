package com.agnos.testscripts;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.agnos.reusablefunctions.reusableMethods;
import com.agnos.webelements.FlightReservationPageWebElements;

public class FlightReservation_TC001 extends reusableMethods implements FlightReservationPageWebElements
{
	public static String path=System.getProperty("user.dir");
	public static WebDriver driver=null;
	public FlightReservation_TC001()
	{
		System.setProperty("webdriver.chrome.driver",path+"\\drivers\\chromedriver.exe"); 
		ChromeOptions options = new ChromeOptions(); 
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
		driver=new ChromeDriver(options);
		
	}

	public static void main(String[] args) throws InterruptedException 
	{
		FlightReservation_TC001 fr=new FlightReservation_TC001();
		reusableMethods reuse=new reusableMethods();
		driver.navigate().to("https://www.expedia.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//a/span[contains(text(),'Flights')]")).click();
		driver.findElement(ONEWAYTRIP).click();
		driver.findElement(FROM).click();
		driver.findElement(By.id("location-field-leg1-origin")).sendKeys("MIAMI");
		driver.findElement(SELECTORIGIN).click();
		driver.findElement(TO).click();
		driver.findElement(By.id("location-field-leg1-destination")).sendKeys("CALIFORNIA");
		driver.findElement(SELECTDESTINATION).click();
		driver.findElement(SEARCH_BUTTON).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);				
		String titleOfPage=driver.getTitle();
		if (titleOfPage.contains("flights"))
		{
			System.out.println("Test Pass. Search results are displayed.");
			WebElement sortElement=driver.findElement(By.id("id='listings-sort'"));
			reuse.selectOptions(driver,sortElement,"DURATION_INCREASING");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div[3]/div[1]/section/main/ul/li[1]")).click();
			driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div[2]/div[3]/div[1]/section/main/div[6]/section/div[2]/div/div[2]/div[2]/div/div/ul/li[1]/div/button")).click();
			driver.findElement(By.xpath("//a[contains(text(),'No thanks')]")).click();
			String mainWindow=driver.getWindowHandle();
			Set<String>handles=driver.getWindowHandles();
			Thread.sleep(3000);
			for(String handle:handles)
			{
				if(!handle.equals(mainWindow))
				{
					driver.switchTo().window(handle);
					driver.findElement(CHECKOUT).click();
					if(driver.getTitle().equalsIgnoreCase("Expedia: Payment"))
					{
						System.out.println("Test Pass. User is navigated to payments page");
						driver.findElement(By.id("firstname[0]")).sendKeys("FirstName");
						driver.findElement(By.id("lastname[0]")).sendKeys("LastName");
						driver.findElement(By.id("phone-number[0]")).sendKeys("9876543210");
						driver.findElement(By.id("gender_male[0]")).click();
						reuse.selectOptions(driver, driver.findElement(By.id("date_of_birth_month0")),"02");
						reuse.selectOptions(driver, driver.findElement(By.id("date_of_birth_day[0]")),"01");
						reuse.selectOptions(driver, driver.findElement(By.id("date_of_birth_year[0]")),"1992");
						driver.findElement(By.id("optional-prefs-toggle-link-0")).click();
						reuse.selectOptions(driver, driver.findElement(By.id("frequent_flyer_plan_id_[0]_[0]")), "AA-ADV");
						driver.findElement(By.id("frequent_flyer_membership_number_[0]_[0]")).sendKeys("P9890");
						driver.findElement(By.xpath("//span[contains(text(),'TSA PreCheck')]/following-sibling::input")).sendKeys("T9890");
						driver.findElement(By.xpath("//span[contains(text(),'Redress number')]/following-sibling::input")).sendKeys("RN9890");
						driver.findElement(By.xpath("//a[contains(text(),'Click-to-Pay')]")).click();
						driver.findElement(By.name("email")).sendKeys("test@test.com");
						driver.findElement(By.name("password")).sendKeys("demo");
						driver.findElement(By.name("repeat_password")).sendKeys("demo");
						driver.findElement(By.id("complete-booking")).click();
						
						String mainChildWindow=driver.getWindowHandle();
						Set<String>Childhandles=driver.getWindowHandles();
						Thread.sleep(3000);
						for(String handleChild:Childhandles)
						{
							if(!handle.equals(mainChildWindow))
							{
								driver.switchTo().window(handleChild);								
								if(driver.getTitle().equalsIgnoreCase("SRC Checkout"))
								{
									System.out.println("Test Pass. User is navigated to payments page");
									driver.quit();
									
								}
								else
								{
									System.out.println("Test Fail. User is not navigated to payments page");
									driver.quit();
								}
								
							}
						}
						
						
					}
					else
					{
						System.out.println("Test fail. User is not navigated to payments page");
						driver.quit();
					}
					
				}
				
			}
			
		}
		else
		{
			System.out.println("Test Fail. Not able to search flight");
			driver.quit();
		}
		
		
		
	}

}
