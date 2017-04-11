package tests;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataDrivenFramework {
	
	public WebDriver driver; 
	@Test(dataProvider="testData")
	public void login(String username, String password){
		System.out.println("Login: "+username+" "+password);
		System.setProperty("webdriver.gecko.driver", "D:/drivers/geckodriver.exe");		
		driver = new FirefoxDriver();
		driver.get("https://www.google.com");
	}
	
	@AfterTest
	public void shutDown(){
		driver.close();
	}
	
	@DataProvider(name="testData")
	public Object[][] readExcel() throws BiffException, IOException{
		
		// Instantiate the excel file 
		File excelFile = new File("D:/Book1.xls"); 
		
		//Workbook 
		Workbook workBook = Workbook.getWorkbook(excelFile);
		
		//Sheet 
		Sheet sheet = workBook.getSheet("Sheet1"); 
		
		int numberOfRows = sheet.getRows(); 
		
		int numberOfColumns = sheet.getColumns();
		
		System.out.println(numberOfRows);
		System.out.println(numberOfColumns);
		
		String fileData[][] = new String[numberOfRows][numberOfColumns];
		
		for(int i=0;i<numberOfRows;i++){
			for(int j=0;j<numberOfColumns;j++){
				Cell c = sheet.getCell(j, i);
				fileData[i][j] = c.getContents();
				System.out.println(fileData[i][j]);
			}
		}
		return fileData;
		
		
	}

}
