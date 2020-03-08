package com.gmailPages.examples;

import org.openqa.selenium.By;

import com.gmailPack.examples.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class GmailLogin extends TestBase {

	public static void main(String[] args) {
		try{
			GmailLogin gm= new GmailLogin();
			gm.LoadConfig();
			gm.OpenChrome();
			gm.ReadExcel();
			gm.driver.findElement(By.xpath("//*[@id='identifierId']")).sendKeys(gm.un);	
			gm.driver.findElement(By.xpath("//*[@id='identifierNext']/span/span")).click();
			Thread.sleep(5000);
			gm.driver.findElement(By.xpath("//*[@id='password']/div[1]/div/div[1]/input")).sendKeys(gm.pw);
			gm.driver.findElement(By.xpath("//*[@id='passwordNext']/span/span")).click();
			Thread.sleep(5000);
			gm.extentReport();
			logger.info("what is the title -->"+gm.driver.getTitle());
			if(gm.driver.getTitle().contains("Gmail"))
			{
				gm.test.log(LogStatus.PASS, "pass");
			}
			else
			{
				gm.test.log(LogStatus.FAIL,"Fail");
				
			}
			gm.report.endTest(gm.test);
			gm.report.flush();
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	
	}
}

