package com.imp;

import java.lang.reflect.Method;


import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

public class US_test extends Driver
{
	static String Homewindow;
	static String ClaimProcessScreen;
	
	@BeforeClass
	public void start() throws Exception
	{
		Test_Initializer.atStart();
	}

	@BeforeMethod(alwaysRun = true )
	public void getTestName(Method method) throws Exception
	{
		Test_Initializer.atGetTestName(method);
	}
	
	@AfterTest(alwaysRun = true )
	public void endtest() throws Exception
	{
		Test_Initializer.atEndTest();
	}

	@AfterMethod(alwaysRun = true )
	public static void result(ITestResult result)
	{
		Test_Initializer.atResult(result);
	}

	@AfterClass(alwaysRun = true )
	public void end()
	{
		Test_Initializer.atEnd();
	}

	@Test
	public void TC_174() throws Exception
	{
		try
		{		
			
			Libraries.waitForElementNotPresent("//*[@id='page_loading_inner']");
			Libraries.waitFor("//*[@id='data-table-agent']/tbody/tr[1]/td[3]");
			Libraries.waitForElementToBeClickable("//*[@id='data-table-agent']/tbody/tr[1]/td[3]");
			Libraries.waitForElementNotPresent("//*[@id='all_employees_loading']");
			Thread.sleep(3000);
			Homewindow=driver.getWindowHandle();
	
			
			//$("#data-table-agent").closest('tbody').children('tr:first') 
			//from 45591
			//CaseID=ses.getElement("agent_first_claim").getText();
			ses.getElement("agent_first_claim").click();
			Libraries.mouseRightClick(ses.getElement("agent_first_claim"));
			ses.getElement("pend_option").click();
			Thread.sleep(3000);
			
//			driver.findElement(By.xpath("//*[@id='pend_dialog_pending_code']/option[2]")).click();
//			ses.getElement("pend_dialog_notes").sendKeys("notes writing");
//			ses.getElement("pend_dialog_done").click();
			
//			ses.getElement("GlobalSearch1").click();
//			ses.getElement("GlobalSearch2").click();
//			ses.getElement("searchTextBox").sendKeys(getVariable("US_112253_reopened_Case_number"));
//			ses.getElement("searchTextBox").sendKeys(Keys.ENTER);	
//			Libraries.waitFor("//*[@id='table-search-res-claim']/tbody/tr/td[2]");
//			Libraries.mouseDoubleClick(ses.getElement("table-search-res-claim"));
//			Thread.sleep(1000);
//		    for(String Screen_2 : driver.getWindowHandles())
//			{
//				if((!Screen_2.equals(Homewindow)))
//				{       
//					driver.switchTo().window(Screen_2);
//					driver.manage().window().maximize();
//				}
//			}	
//		    Libraries.waitForElementNotPresent("//*[@id='page_loading_inner']");
//		    Libraries.waitForElementNotPresent("//*[@id='claim_detail_loading']");
//		    Libraries.waitFor("//*[@id='claim_summary_scrn']");
//		    Libraries.waitForElementToBeClickable("//*[@id='claim_summary_scrn']");
//		    ClaimProcessScreen=driver.getWindowHandle();
//		    ses.getElement("claim_summary_scrn").click();
//		    for(String Screen_3 : driver.getWindowHandles())
//			{
//				if((!Screen_3.equals(Homewindow)) && (!Screen_3.equals(ClaimProcessScreen)))
//				{       
//					driver.switchTo().window(Screen_3);
//					driver.manage().window().maximize();					
//				}
//			}
//		    Libraries.waitForElementNotPresent("//*[@id='page_loading_inner']");
//		    Libraries.waitFor("//*[@id='open_reopen_days']");
//		    Thread.sleep(2000);
//			String date_verify=ses.getElement("open_reopen_days").getText();
//			System.out.println(date_verify);
//			if(!date_verify.equals(null))
//			{
//				ses.passMessage("TC_174 PASSED, Open/Reopen date is displayed");				
//			}
//			else
//			{
//				ses.failMessage("TC_174 Failed, Open/Reopen date is not displayed");				
//			}	
		}		
		catch(Exception e)
		{
			e.printStackTrace();
			ses.failMessage("TC_174 failed");
		}		
	}

//	@Test
//	public void TC_175() throws Exception
//	{
//		try
//		{	
//			String date_verify=ses.getElement("open_reopen_days").getText();
//			if(!date_verify.isEmpty())
//			{
//				ses.passMessage("TC_175 PASSED, Reopened date is displayed correctly");				
//			}
//			else
//			{
//				ses.failMessage("TC_175 FAILED, Reopened date is not displayed correctly");				
//			}						
//		}		
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			ses.failMessage("TC_175 failed");
//		}		
//	}
//	@Test
//	public void TC_176() throws Exception
//	{
//		try
//		{	
//			Libraries.selectByIndex(ses.getElement("open_reopen_days"), 2);
//			String selected_date=ses.getElement("open_reopen_days").getText();
//			selected_date=selected_date.substring(1, 10);
//			if(selected_date.charAt(0)=='0')
//			{
//				selected_date=selected_date.replaceFirst("0", "");
//			}
//			if(selected_date.charAt(2)=='0')
//			{
//				selected_date=selected_date.replaceFirst("0", "");
//			}
//			System.out.println(selected_date);
//			String date_val=ses.getElement("summaryCaseNotesText").getText();
//			System.out.println(date_val);
//			if(date_val.contains(selected_date))
//			{
//				ses.passMessage("TC_176 PASSED, Summary Case Notes contains the reopen date");				
//			}
//			else
//			{
//				ses.failMessage("TC_176 FAILED, Summary Case Notes does not contain the reopen date");				
//			}			
//		}		
//		catch(Exception e)
//		{	
//			e.printStackTrace();
//			ses.failMessage("TC_176 failed");
//		}		
//	}
}


