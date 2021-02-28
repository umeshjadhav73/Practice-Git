package testutility;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;

import com.imp.Driver;
import com.imp.ExcelReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporting {

	public static ExtentReports report;
	public static ExtentTest test;
	static String testMethodName;
	public static void setUpReport()
	{
		try
		{	
			try
			{
				
				long millis  = System.currentTimeMillis();
				String timeStamp = new SimpleDateFormat("dd-MM-YYYY  HH.mm.ss").format(new java.util.Date(millis));
				File source = new File(System.getProperty("user.dir")+"\\Reports\\ExtentReport.html");
				System.out.println(source);
				File destination = new File(System.getProperty("user.dir")+"\\Reports\\Old Reports\\ExtentReport"+timeStamp+".html");
				System.out.println(destination);
				Files.copy(source.toPath(), destination.toPath());
				Files.delete(source.toPath());
			}
			catch(Exception e)
			{
				System.out.println("Preparing new report....");
			}
			
			report = new ExtentReports (System.getProperty("user.dir")+"\\Reports\\ExtentReport.html", false);
			report.addSystemInfo("Host Name", "My Report")
			.addSystemInfo("Environment", "Testing");			
			report.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		}
		catch(Exception e)
		{
			System.out.println("Exception");
			e.printStackTrace();
		}
		
	}
	
	public static void tearDown()
	{
		report.endTest(test);
	}

	public static void writeResult(ITestResult result)
	{
	    try
	    {
	        if(result.getStatus() == ITestResult.SUCCESS)
	        {

	        	test.log(LogStatus.PASS, testMethodName+" method is passed");
	        }
	        else if(result.getStatus() == ITestResult.FAILURE)
	        {
	        	test.log(LogStatus.FAIL, testMethodName+" method is Failed");

	        }
	        else if(result.getStatus() == ITestResult.SKIP)
	        {
	        	test.log(LogStatus.SKIP,testMethodName+" method is Skiped");

	        }
	        report.endTest(test);
	    }
	    catch(Exception e)
	    {
	        System.out.println("\nLog Message::@AfterMethod: Exception caught");
	        e.printStackTrace();
	    }

	}
	
	public static void atStart(String className) throws Exception
	{
		
		test=report.startTest(className);	
	
	}
	
	public static void atGetTestName(Method method) throws Exception
	{
		testMethodName = method.getName();
	}

	


}
