package com.imp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader 
{
	public static String ClassName=""; 	
	static String sheetName="";
	static Config_Reader configreader=new Config_Reader();
	public static HashMap<Integer, String> headers = new HashMap<Integer, String>();
	public static HashMap<Integer, String> sheets = new HashMap<Integer, String>();
	static int counter=1;
	public static HashMap<Integer, String> myMap = new HashMap<Integer, String>();
	
	
	//public static final String currentDir = System.getProperty("user.dir");	 
 
    //Excel WorkBook
    private static XSSFWorkbook excelWBook;
 
    //Excel Sheet
    private static XSSFSheet excelWSheet;
 
    //Excel cell
    private static XSSFCell cell;
 
    //Excel row
    private static XSSFRow row;
 
    //Row Number
    public static int rowNumber;
 
    //Column Number
    public static int columnNumber;
 
    //Setter and Getters of row and columns
    public static void setRowNumber(int pRowNumber) {
        rowNumber = pRowNumber;
    }
 
    public static int getRowNumber() {
        return rowNumber;
    }
 
    public static void setColumnNumber(int pColumnNumber) {
        columnNumber = pColumnNumber;
    }
 
    public static int getColumnNumber() {
        return columnNumber;
    }
 
    // This method has two parameters: "Test data excel file name" and "Excel sheet name"
    // It creates FileInputStream and set excel file and excel sheet to excelWBook and excelWSheet variables.
    public static void setExcelFileSheet(String sheetName) throws Exception 
    {
        try 
        {
          
            FileInputStream ExcelFile = new FileInputStream(configreader.getExcelFilePath());
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } 
        catch (Exception e) 
        {
        	  e.printStackTrace();
        	
            try
            {
                throw (e);
            }
            catch (IOException e1) 
            {
               e1.printStackTrace();
            }
        }
    }
 
    //This method reads the test data from the Excel cell.
    //We are passing row number and column number as parameters.
    public static String getCellData(int RowNum, int ColNum) throws Exception
    {
        try 
        {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
        } 
        catch (Exception e)
        {
            throw (e);
        }
    }
 
    //This method takes row number as a parameter and returns the data of given row number.
    public static XSSFRow getRowData(int RowNum) throws Exception
    {
        try
        {
            row = excelWSheet.getRow(RowNum);
            return row;
        } 
        catch (Exception e)
        {
            throw (e);
        }
    }
 
    
    //This method gets excel file, row and column number and set a value to the that cell.
    public static void setCellData(String value, int RowNum, int ColNum) throws Exception 
    {
        try 
        {
            row = excelWSheet.getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null)
            {
                cell = row.createCell(ColNum);
                cell.setCellValue(value);
            }
            else
            {
                cell.setCellValue(value);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream("");
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } 
        catch (Exception e) 
        {
            try 
            {
                throw (e);
            } 
            catch (IOException e1) 
            {
                e1.printStackTrace();
            }
        }
    }
   
    
    public static void getHeaders()throws Exception
    { 	
    	String headerName;
    	try
    	{ 
    		XSSFRow row = excelWSheet.getRow(0);
    		int column =row.getLastCellNum();
    		for(int j=0;j<column;j++)
    		{
    			cell = excelWSheet.getRow(0).getCell(j);
                DataFormatter formatter = new DataFormatter();
                headerName = formatter.formatCellValue(cell);
             
                
                if(!headerName.equals(null) && !headerName.isEmpty())
                {
                	 headers.put(j,headerName);
                }                
    		}
	 		
    	}
    	catch(Exception e)
    	{
    		e.getLocalizedMessage();
    	}
    }
    
    public static void getData()throws Exception
    { 	
    	String className=null;
    	String status=null;
    	try
    	{   		 
    		int rowCount = excelWSheet.getLastRowNum()+1;
    		for(int j=1;j<rowCount;j++)
    		{
    			cell = excelWSheet.getRow(j).getCell(0);
                DataFormatter formatter = new DataFormatter();
                className = formatter.formatCellValue(cell);
                
                cell = excelWSheet.getRow(j).getCell(1);
                status = formatter.formatCellValue(cell);
                
                if(status.equalsIgnoreCase("Yes"))
                {
                	 myMap.put(j,className);
                }                
    		}
  
    	}
    	catch(Exception e)
    	{
    		e.getLocalizedMessage();
    	}
    }
    
 
    
    
    public static String getVariable(String key)throws Exception
    { 	
    	
    	try
    	{   		 
    		for (Entry<Integer, String> entry : headers.entrySet()) 
    		{
    			if(entry.getValue().equalsIgnoreCase(key))
    			{
    				columnNumber=entry.getKey();
    			}
    	    }
	 		
    	}
    	catch(Exception e)
    	{
    		e.getLocalizedMessage();
    	}
    	return getCellData(rowNumber, columnNumber);
    }
    
    
    public static void decideSheetName()throws Exception
    { 	
    
    	setExcelFileSheet(configreader.getSheetName());
    	String Sheet=null;
    	String status=null;
    	try
    	{   		 
    		int rowCount = excelWSheet.getLastRowNum()+1;    		
    		for(int j=1;j<rowCount;j++)
    		{
    			setExcelFileSheet(configreader.getSheetName());
    			cell = excelWSheet.getRow(j).getCell(0);
                DataFormatter formatter = new DataFormatter();
                Sheet = formatter.formatCellValue(cell);
                
                cell = excelWSheet.getRow(j).getCell(1);
                status = formatter.formatCellValue(cell);        
                if(status.equalsIgnoreCase("Yes"))
                {
                	 sheetName=Sheet;
                	 ExtentListener.testSetUp();
                	 invoke();
                }              
    		}	
    		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		e.getLocalizedMessage();
    	}
    }
    
    
    public static void invoke() throws Exception 
    {
    	try
    	{
    		setExcelFileSheet(sheetName);
    		getData();
    		getHeaders();
    		DynamicTestNG d1=new DynamicTestNG();
    		for (Entry<Integer, String> entry : myMap.entrySet()) 
    		{
    			rowNumber=entry.getKey();
    			ClassName=entry.getValue();
    			System.out.println("Executing "+ClassName+" of "+sheetName+" sheet.....");
    			d1.runTestNGTest(entry.getValue());			
    	    }
    		 myMap.clear();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
	}
    
    
    
    

}

