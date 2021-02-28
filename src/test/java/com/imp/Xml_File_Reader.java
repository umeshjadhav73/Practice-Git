package com.imp;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


import com.relevantcodes.extentreports.LogStatus;

import net.bytebuddy.implementation.bytecode.Throw;

public class Xml_File_Reader extends Driver
{

	String value;
	public static String message;

	ITestResult result;
	public  String locateElement(String keyval)
	{
		value =  "";
		try
		{

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (new File(System.getProperty("user.dir")+"/Repository/"+ExcelReader.ClassName+".xml"));

			// normalize text representation
			doc.getDocumentElement ().normalize ();
			//	System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());


			NodeList listOfObject = doc.getElementsByTagName("Object");
			int totalObject = listOfObject.getLength();
			//	System.out.println("Total no of Object : " + totalObject);

			for(int s=0; s<listOfObject.getLength() ; s++)
			{


				Node firstObjectNode = listOfObject.item(s);
				if(firstObjectNode.getNodeType() == Node.ELEMENT_NODE)
				{


					Element firstObjectElement = (Element)firstObjectNode; 

					//-------
					NodeList firstKeyList = firstObjectElement.getElementsByTagName("Key");
					Element firstKeyElement = (Element)firstKeyList.item(0);

					NodeList textFNList = firstKeyElement.getChildNodes();
					//		System.out.println("Key : " + ((Node)textFNList.item(0)).getNodeValue().trim());
					String key = ((Node)textFNList.item(0)).getNodeValue().trim();

					if(keyval.matches(key))
					{
						//----
						NodeList valueList = firstObjectElement.getElementsByTagName("Value");
						Element ageElement = (Element)valueList.item(0);

						NodeList textValueList = ageElement.getChildNodes();
						//			System.out.println("Value : " + ((Node)textValueList.item(0)).getNodeValue().trim());
						value =  ((Node)textValueList.item(0)).getNodeValue().trim();

					}	
					else
					{
						continue;
					}
				}
			}
		}
		catch (SAXParseException err)
		{
			System.out.println ("** Parsing error" + ", line " + err.getLineNumber () + ", uri " + err.getSystemId ());
			System.out.println(" " + err.getMessage ());

		}
		catch (SAXException e)
		{
			Exception x = e.getException ();
			((x == null) ? e : x).printStackTrace ();

		}
		catch (Throwable t) 
		{
			t.printStackTrace ();
		}
		//System.exit (0);
		return value;
	}

	public  WebElement getElement(String Key)
	{
		log.info("Getting "+Key+" element");
		WebElement Wb = null;
		String Value;
		try
		{
			Value=locateElement(Key);
			if(Value.isEmpty())
			{
				ses.failMessage("Key " + Key + " is not found with Object Repository");
			}
			else
			{
				Wb=driver.findElement(By.xpath(Value));
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Wb;
	}

	public  List<WebElement> getElements(String Key)
	{
		log.info("Getting "+Key+" element");
		List<WebElement> Wb = null;
		try
		{
			String Value=locateElement(Key);
			if(Value.isEmpty())
			{
				ses.failMessage("Key " + Key + " is not found with Object Repository");
			}
			else
			{
				Wb=driver.findElements(By.xpath(Value));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Wb;
	}

	public void passMessage(String msg)
	{
		try
		{
			test.log(LogStatus.PASS, msg);
			log.info(msg);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	public void failMessage(String msg) throws IOException
	{

		try
		{
			test.log(LogStatus.FAIL, msg);
			log.error(msg);
			throw new CustomException(msg);
			
		}				
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		finally
		{
			Assert.fail();	
		}
	}
	
	public static String addscreenshot(String msg) throws IOException
	{
	
		long millis  = System.currentTimeMillis();
		String timeStamp = new SimpleDateFormat("dd-MM-YYYY  HH.mm.ss").format(new java.util.Date(millis));
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		File src=screenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") +"\\Project\\Reports\\Failed Screenshots\\"+ClassName+"-"+msg+"-"+timeStamp+".png";
		FileUtils.copyFile(src,new File(destination));
		System.out.println("Successfully captured a screenshot");
		return destination;
	}


}




