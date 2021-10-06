package com.agnos.reusablefunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class reusableMethods 
{
	public static WebDriver selectOptions(WebDriver driver, WebElement sortElement,String value)
	{
		Select sortOption=new Select(sortElement);
		sortOption.selectByValue(value);
		return driver;
	}
	
}
