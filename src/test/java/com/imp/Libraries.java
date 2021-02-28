package com.imp;

import java.awt.AWTException;

import java.awt.Robot;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;


import com.google.common.base.Function;

public class Libraries extends Driver
{
	public static void selectOption(WebElement Element, String Value) throws Exception
	{
		try
		{
			new Select(Element).selectByVisibleText(Value);
			log.info("Selecting "+Value+" Option");
		}
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public static void selectByIndex(WebElement Element, int Index) throws Exception
	{
		try
		{
			new Select(Element).selectByIndex(Index);
			log.info("Selecting element of "+Index+" index");
		}		
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public static void selectByValue(WebElement Element, String Value) throws Exception
	{
		try
		{
			new Select(Element).selectByValue(Value);
			log.info("Selecting "+Value+" Option");
		}		
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public static void waitFor(final String XPath)
	{ 
		try
		{
			log.info("Waiting for "+XPath+" element");
			WebElement wait = new FluentWait<WebDriver>(Driver.driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS)
					.ignoring(Exception.class)
					.until(new Function<WebDriver, WebElement>()
					{
						public WebElement apply(WebDriver driver) {
							return driver.findElement(By.xpath(XPath));
						}
					});
			Thread.sleep(2000);
		}		
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public static void waitForElementNotPresent(final String XPath)
	{ 
		try
		{
			log.info("Waiting for "+XPath+" element not present");
			Boolean wait = new FluentWait<WebDriver>(Driver.driver)
					.withTimeout(150, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS)
					.ignoring(Exception.class)
					.until(ExpectedConditions.not(
							ExpectedConditions.visibilityOfElementLocated(By.xpath(XPath))));
		}
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public static void waitForElementToBeClickable(final String XPath)
	{ 
		try
		{
			log.info("Waiting for "+XPath+" element to be clickable");
			WebElement wait = new FluentWait<WebDriver>(Driver.driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(2, TimeUnit.SECONDS)
					.ignoring(Exception.class)
					.until(ExpectedConditions.elementToBeClickable(By.xpath(XPath)));
			Thread.sleep(1000);
		}		
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public static void waitForElementToBeSelected(final String XPath)
	{ 
		try
		{
			log.info("Waiting for "+XPath+" element to be selected");
			Boolean wait = new FluentWait<WebDriver>(Driver.driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS)
					.ignoring(Exception.class)
					.until(ExpectedConditions.elementToBeSelected(By.xpath(XPath)));
		}
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public static void waitForDropdownValues(final String XPath)
	{ 
		try
		{
			log.info("Waiting for drodown values to be loaded in "+XPath+" element");
			final Select droplist = new Select(Driver.driver.findElement(By.xpath(XPath)));
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(30, TimeUnit.SECONDS)
					.pollingEvery(3, TimeUnit.SECONDS)
					.ignoring(Exception.class);

			Boolean foo = wait.until(new Function<WebDriver, Boolean>() {
				public Boolean apply(WebDriver driver) {
					return (droplist.getOptions().size() > 1);
				}
			});
		}
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public static void mouseDoubleClick(WebElement WbElement)
	{
		try
		{
			new Actions(Driver.driver).doubleClick(WbElement).build().perform();
			log.info("Performing Double click");
		}		
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public static void mouseRightClick(WebElement WbElement)
	{
		try
		{
			new Actions(Driver.driver).contextClick(WbElement).build().perform();	
			log.info("Performing Right click");
		}		
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public static void mouseClick(WebElement WbElement)
	{
		try
		{
			new Actions(Driver.driver).click(WbElement).build().perform();
			log.info("Clicked on Element");
		}
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}		
	}

	public static void moveToElement(WebElement WbElement)
	{
		try
		{
			new Actions(Driver.driver).moveToElement(WbElement).build().perform();
			log.info("Moved to Element");
		}
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		}		
	}

	public static void highLightElement(WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)Driver.driver; 
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();
		} 
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element); 
	}

	public static String currentdate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String CURRENTDAY = sdf.format(date);
		return CURRENTDAY;
	}

	public static void deleteBrowserCookies() throws InterruptedException
	{
		Actions keyAction = new Actions(Driver.driver);     
		Driver.driver.get("chrome://settings/clearBrowserData");
		Thread.sleep(5000);
		Action modifierkey = keyAction.sendKeys(Keys.ENTER).build();
		modifierkey.perform();
	}

	public static void Robot_EnterKey() throws AWTException, InterruptedException
	{
		Robot r = new Robot();
		r.keyPress(java.awt.event.KeyEvent.VK_ENTER);
		r.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
	}

	public static void Robot_TabOut() throws AWTException, InterruptedException
	{
		Robot r = new Robot();
		r.keyPress(java.awt.event.KeyEvent.VK_TAB);
		r.keyRelease(java.awt.event.KeyEvent.VK_TAB);
	}

	public static void Robot_EscKey() throws AWTException, InterruptedException
	{
		Robot r = new Robot();
		r.keyPress(java.awt.event.KeyEvent.VK_ESCAPE);
		r.keyRelease(java.awt.event.KeyEvent.VK_ESCAPE);
	}

	public static void Robot_KEYDOWN() throws AWTException, InterruptedException
	{
		Robot r = new Robot();
		r.keyPress(java.awt.event.KeyEvent.VK_DOWN);
		r.keyRelease(java.awt.event.KeyEvent.VK_DOWN);
	}

	public static int Get_X(String  XPath)
	{

		WebElement ELE = driver.findElement(By.xpath(XPath));
		Point classname = ELE.getLocation();
		int xcordi = classname.getX();
		System.out.println("Element's Position from left side "+xcordi +" pixels.");
		return xcordi;
	}
	public static int Get_Y(String  XPath)
	{

		WebElement ELE = driver.findElement(By.xpath(XPath));
		Point classname = ELE.getLocation();
		int ycordi = classname.getY();
		System.out.println("Element's Position from top "+ycordi +" pixels."); 
		return ycordi;


	}

	public static void verifyDropDownElementByAttributeValue(WebElement Element, String Value) throws Exception
	{
		String selectedValue = "";
		try
		{
			selectedValue=new Select(Element).getFirstSelectedOption().getAttribute("value");
			log.info("Getting value of selected option");
			if(selectedValue.contains(Value))
			{
				ses.passMessage("Pass : Selected value of Dropdown is " + selectedValue + ", Expected value is " + Value);
			}
			else
			{
				ses.failMessage("Fail : Selected value of Dropdown is " + selectedValue + ", Expected value is " + Value);
			}			
		}		
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();		
		}	
	}

	public static void verifyDropDownElementByText(WebElement Element, String VisibleText) throws Exception
	{
		String selectedValue = "";
		try
		{
			selectedValue=new Select(Element).getFirstSelectedOption().getText();
			log.info("Getting value of selected option");
			if(selectedValue.contains(VisibleText))
			{
				ses.passMessage("Pass : Text of selected element of Dropdown is " + selectedValue + ", Expected Text is " + VisibleText);
			}
			else
			{
				ses.failMessage("Fail : Text of selected element of Dropdown is " + selectedValue + ", Expected Text is " + VisibleText);
			}	
		}		
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();		
		}	
	}

	public static void verifyAllDropdown_Elements(String Key, String Values) throws Exception
	{
		String DDvalues= Values;
		String[] actual= DDvalues.split(",");
		java.util.List<WebElement> elem = ses.getElements(Key);
		int count =0;
		for(WebElement WE:elem )
		{
			if(WE.getAttribute("value").replaceAll("\\s+","").equalsIgnoreCase(actual[count].replaceAll("\\s+","")))
			{
				ses.passMessage("PASS");
				System.out.println(WE.getAttribute("value").replaceAll("\\s+","") + " Matches with " + actual[count].replaceAll("\\s+",""));
			}
			else
			{
				ses.failMessage("Elemnts not matching");
				System.out.println(WE.getAttribute("value").replaceAll("\\s+","") + " does not match with " + actual[count].replaceAll("\\s+",""));
			}
			count++;
		}
	}	

	public static void verifyAllDropdown_Elements(List <WebElement> Elements, String Values) throws Exception
	{
		String DDvalues= Values;
		String[] actual= DDvalues.split(",");
		int count =0;
		for(WebElement WE:Elements )
		{
			if(WE.getAttribute("value").replaceAll("\\s+","").equalsIgnoreCase(actual[count].replaceAll("\\s+","")))
			{
				ses.passMessage("PASS");
				System.out.println(WE.getAttribute("value").replaceAll("\\s+","") + " Matches with " + actual[count].replaceAll("\\s+",""));
			}
			else
			{
				System.out.println(WE.getAttribute("value").replaceAll("\\s+","") + " does not match with " + actual[count].replaceAll("\\s+",""));
				ses.failMessage("Elemnts not matching");				
			}
			count++;
		}
	}

	public static void verifyAllDropdown_Elements_ByText(List <WebElement> Elements, String Values) throws Exception
	{
		String DDvalues= Values;
		String[] actual= DDvalues.split(",");
		int count =0;
		for(WebElement WE:Elements )
		{
			if(WE.getText().replaceAll("\\s+","").equalsIgnoreCase(actual[count].replaceAll("\\s+","")))
			{
				ses.passMessage("PASS");
				System.out.println(WE.getText().replaceAll("\\s+","") + " Matches with " + actual[count].replaceAll("\\s+",""));
			}
			else
			{
				System.out.println(WE.getText().replaceAll("\\s+","") + " does not match with " + actual[count].replaceAll("\\s+",""));
				ses.failMessage("Elemnts not matching");				
			}
			count++;
		}
	}

	public static void verifyElementByAttributeValue(WebElement Element, String Value) throws Exception
	{
		String selectedValue = "";
		try
		{
			selectedValue=Element.getAttribute("value");
			if(selectedValue.contains(Value))
			{
				ses.passMessage("Pass : Actual value is " + selectedValue + ", Expected value is " + Value);
			}
			else
			{
				ses.failMessage("Fail : Actual value is " + selectedValue + ", Expected value is " + Value);
			}			
		}		
		catch(Exception e)
		{
			log.info(e.getLocalizedMessage());
			e.printStackTrace();		
		}	
	}
	public static Boolean isSelected(WebElement Element, String VisibleText) throws Exception
	{
		Boolean flag=false;
		String selectedValue = "";
		try
		{
			selectedValue=new Select(Element).getFirstSelectedOption().getText();
			log.info("Getting value of selected option");
			if(selectedValue.contains(VisibleText))
			{
				flag=true;
				ses.passMessage("Pass : Text of selected element of Dropdown is " + selectedValue + ", Expected Text is " + VisibleText);
			}
			else
			{
				flag=false;
				ses.failMessage("Fail : Text of selected element of Dropdown is " + selectedValue + ", Expected Text is " + VisibleText);
			}	
		}		
		catch(Exception e)
		{
			flag=false;
			ses.failMessage("Fail : Text of selected element of Dropdown is " + selectedValue + ", Expected Text is " + VisibleText);	
		}
		return flag;	
	}
}

