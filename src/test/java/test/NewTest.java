package test;

import java.lang.reflect.Method;


import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.imp.Test_Initializer;
import com.relevantcodes.extentreports.LogStatus;

import testutility.ExtentReporting;

public class NewTest {
	
	
	@BeforeClass
	public void setUp() throws Exception
	{
		ExtentReporting.setUpReport();
		ExtentReporting.atStart("NewTest");
	}
	
	@BeforeMethod(alwaysRun = true )
	public void getTestName(Method method) throws Exception
	{
		ExtentReporting.atGetTestName(method);
	}
	
	@AfterMethod
	public void collectResult(ITestResult result)
	{
		ExtentReporting.writeResult(result);
	}
	
	@AfterClass(alwaysRun = true )
	public void flushReport()
	{
		ExtentReporting.report.endTest(ExtentReporting.test);
		ExtentReporting.report.flush();
	}
	
	
	@AfterTest(alwaysRun = true )
	public void endSetUp()
	{
		ExtentReporting.tearDown();
	}
	
	
	
	
	
	@Test
	public void firstTest()
	{
		String title="sachin";
		
		Assert.assertEquals(title, "sac1hin");
		
	}
	
	@Test
	public void secondTest()
	{
		String title="sachin";
		
		Assert.assertEquals(title, "sac1hin");
		
	}
	
	
}
