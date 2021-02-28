package com.imp;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class Driver extends ExcelReader
	{
		public static WebDriver driver;
		public static Xml_File_Reader ses;
		public static ExtentReports report;
		public static ExtentTest test;
		 public static Logger log;

		public static void main(String[] args) throws Exception 
		{
			//ExtentListener.testSetUp();
			decideSheetName();
		}

		public static void initializeDriver() throws Exception
		{
			try
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"/Project/Resources/exe/chromedriver.exe");
				driver = new ChromeDriver();
				ses=new Xml_File_Reader();	
				log=Logger.getLogger(ClassName);
				log.info("Browser Launched");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	}


