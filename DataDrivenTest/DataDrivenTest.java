package DataDrivenTest;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataDrivenTest {
	
	@Test(dataProvider="dataFromExcel")
	public void addNumbers(String val1, String val2, String val3){
		int a = Integer.parseInt(val1);
		int b = Integer.parseInt(val2);
		int c = Integer.parseInt(val3);
		int sum = a + b + c; 
		System.out.println("Sum is: "+sum);
	}
	
	@DataProvider(name="dataFromExcel")
	public Object[][] readNumbersFromExcel() throws BiffException, IOException{
		
		File file = new File("D:/Book2.xls");
		
		Workbook w = Workbook.getWorkbook(file);
		
		Sheet s = w.getSheet("Sheet1");
		
		int rows = s.getRows(); 
		int columns = s.getColumns();
		
		System.out.println(rows);
		System.out.println(columns);
		
		String input[][] = new String[rows][columns];
		
		for(int i=0; i<rows; i++){
			for(int j=0; j<columns; j++){
				Cell c = s.getCell(j, i); 
				input[i][j] = c.getContents();
				System.out.println(input[i][j]);
			}
		}
		return input;
		
	}

}
