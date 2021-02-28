package com.imp;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;



public class Config_Reader 
{

	private static Properties properties;
	private final String propertyFilePath= System.getProperty("user.dir") +"/Project/Resources/Configuration/Configuration.properties";


	public  Config_Reader()
	{
		BufferedReader reader;
		try 
		{
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try 
			{
				properties.load(reader);
				reader.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}

	public static String getDriverPath()
	{
		String driverPath = properties.getProperty("driverPath");
		System.out.println(driverPath);
		if(driverPath!= null) 
			return driverPath;
		else 
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");		
	}

	public static  long getImplicitlyWait()
	{		
		String implicitlyWait = properties.getProperty("implicitlyWait");
		if(implicitlyWait != null) 
			return Long.parseLong(implicitlyWait);
		else 
			throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");		
	}

	public static String getApplicationUrl()
	{
		String url;
		if(properties.getProperty("EnvironmentLevel").equals("L3"))
		{
			url = properties.getProperty("L3_ECD_url");
			return url;
		}
		else if(properties.getProperty("EnvironmentLevel").equals("L2"))
		{
			url = properties.getProperty("L2_ECD_url");
			return url;
		}
	
		else 
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}

	public static String getECRDApplicationUrl()
	{
		String url;
		if(properties.getProperty("EnvironmentLevel").equals("L3"))
		{
			url = properties.getProperty("L3_ECRD_url");
			return url;
		}
		else if(properties.getProperty("EnvironmentLevel").equals("L2"))
		{
			url = properties.getProperty("L2_ECRD_url");
			return url;
		}
	
		else 
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	public static String getReportConfigPath()
	{
		String reportConfigPath = properties.getProperty("reportConfigPath");
		if(reportConfigPath!= null) 
			return reportConfigPath;
		else 
			throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
	
	public static String getExcelFilePath()
	{
		String reportConfigPath = properties.getProperty("excelFilePath");
		if(reportConfigPath!= null) 
			return reportConfigPath;
		else 
			throw new RuntimeException("Excel Path not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
	public static String getSheetName()
	{
		String reportConfigPath = properties.getProperty("sheetName");
		if(reportConfigPath!= null) 
			return reportConfigPath;
		else 
			throw new RuntimeException("Sheet name is not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
	
	public static String getJsonUrl()
	{
		String url;
		if(properties.getProperty("EnvironmentLevel").equals("L3"))
		{
			url = properties.getProperty("L3_Json_url");
			return url;
		}
		else if(properties.getProperty("EnvironmentLevel").equals("L2"))
		{
			url = properties.getProperty("L2_Json_url");
			return url;
		}
	
		else 
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	
	
	
	
	public static String getOpCo()
	{
		String reportConfigPath = properties.getProperty("OpCo");
		if(reportConfigPath!= null) 
			return reportConfigPath;
		else 
			throw new RuntimeException("OpCo is not specified in the Configuration.properties file for the Key:reportConfigPath");		
	}
}



