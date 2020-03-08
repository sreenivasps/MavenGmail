package com.gmailPack.examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import jxl.Sheet;
import jxl.Workbook;

public class TestBase {
	public static Properties CONFIG=null;
	public WebDriver driver;
	public String un;
	public String pw;
	public ExtentReports report;
	public ExtentTest test;
	
	public static Logger logger = Logger.getLogger(TestBase.class);
	public void LoadConfig() throws FileNotFoundException{
		try{
			System.out.println("where is the path of config file:"+System.getProperty("user.dir"));
		logger.info("Path is"+System.getProperty(("user.dir")+"\\src\\main\\resources\\config.properties"));
		CONFIG = new Properties();
		
		FileInputStream fp= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
		CONFIG.load(fp);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void OpenChrome(){
		logger.info("opening Chrome browser");
		System.setProperty("webdriver.chrome.driver",CONFIG.getProperty("ChromeDriverPath"));
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(CONFIG.getProperty("Gmail"));
		
	}
	
	public void ReadExcel(){
		try{
			
		
		FileInputStream Ex= new FileInputStream(CONFIG.getProperty("ExcelFile"));
		Workbook wb=Workbook.getWorkbook(Ex);
		Sheet s= wb.getSheet("Sheet1");
		
		 un=s.getCell(0,0).getContents(); //row no, Col no
		logger.info(un);
		 pw=s.getCell(1,0).getContents();
		logger.info(pw);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void extentReport()
	{
		report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
		test= report.startTest("Extent Demo");
	}
	
}
