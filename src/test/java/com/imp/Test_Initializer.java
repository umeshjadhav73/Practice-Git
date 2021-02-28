package com.imp;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;


import com.relevantcodes.extentreports.LogStatus;

public class Test_Initializer extends Driver
{

	static String testMethodName;

	public static void atStart() throws Exception
	{
		Driver.initializeDriver();
		test=report.startTest(ClassName);	
		PropertyConfigurator.configure("Log4j.properties");
	}

	public static void atGetTestName(Method method) throws Exception
	{
		testMethodName = method.getName();
	}

	public static void atEndTest() throws Exception
	{
		report.endTest(test);
	}

	public static void atResult(ITestResult result)
	{
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			try 
			{
				test.log(LogStatus.PASS, "Test Case "+ testMethodName + " is Passed");			
				test.log(LogStatus.INFO, test.addScreenCapture(ses.addscreenshot(result.getName())));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			try {

				//logger.log(LogStatus.FAIL, "Test Case "+ testMethodName + " is Failed And Exception is "+result.getThrowable()+logger.addScreenCapture(ses.addscreenshot(result.getName())));
				test.log(LogStatus.FAIL, "Test Case "+ testMethodName + " is Failed And Exception is "+result.getThrowable());
				test.log(LogStatus.INFO, test.addScreenCapture(ses.addscreenshot(result.getName())));
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else if(result.getStatus() == ITestResult.SKIP || result.getStatus() == ITestResult.CREATED || result.getStatus() == ITestResult.STARTED || result.getStatus() == ITestResult.SUCCESS_PERCENTAGE_FAILURE)
		{
			try 
			{
				test.log(LogStatus.SKIP, "Test Case "+ testMethodName + " is Skipped");
				test.log(LogStatus.INFO, test.addScreenCapture(ses.addscreenshot(result.getName())));
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		report.endTest(test);
	}


	/*public static void atResult(ITestResult result)
	{
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.log(LogStatus.PASS, "Test Case "+ result.getName() + " is Passed");
		}
		else if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.log(LogStatus.FAIL, "Test Case "+ result.getName()+ " is Failed And Exception is "+result.getThrowable());
		}
		else if(result.getStatus() == ITestResult.SKIP)
		{
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		report.endTest(logger);
	}*/

	public static void atEnd()
	{
		driver.quit();
		report.endTest(test);
		report.flush();
	}

}




