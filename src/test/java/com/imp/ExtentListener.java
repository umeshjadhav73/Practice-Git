package com.imp;
import java.io.File;

import java.nio.file.Files;
import java.text.SimpleDateFormat;


import com.relevantcodes.extentreports.ExtentReports;


public class ExtentListener extends Driver
{

	public static void testSetUp()throws Exception
	{
		try
		{	
			try
			{
				
				long millis  = System.currentTimeMillis();
				String timeStamp = new SimpleDateFormat("dd-MM-YYYY  HH.mm.ss").format(new java.util.Date(millis));
				File pathtofile = new File(System.getProperty("user.dir") +"/Project/Reports/New Report/ECDReport"+ExcelReader.sheetName+".html");
				System.out.println(pathtofile);
				File pathtofile2 = new File(System.getProperty("user.dir") +"/Project/Reports/Old Report/ECDReport_"+ExcelReader.sheetName+"_"+timeStamp+".html");
				System.out.println(pathtofile2);
				Files.copy(pathtofile.toPath(), pathtofile2.toPath());
				Files.delete(pathtofile.toPath());
			}
			catch(Exception e)
			{
				System.out.println("Preparing new report....");
			}
			
			report = new ExtentReports (System.getProperty("user.dir") +"/Project/Reports/New Report/ECDReport"+ExcelReader.sheetName+".html", false);
			report.addSystemInfo("Host Name", "ECD Automation")
			.addSystemInfo("Environment", "Testing");
			
			report.loadConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		}
		catch(Exception e)
		{
			System.out.println("Exception");
			e.printStackTrace();
		}
	}

}


