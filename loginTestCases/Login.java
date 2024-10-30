package loginTestCases;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Login {
	
	String [][] data=null;

	@DataProvider(name="loginData")
	public String[][] loginDataProvider() throws BiffException, IOException {
		data = getExcelData();
		return data;
	}
	
	
	public String[][] getExcelData() throws BiffException, IOException {
		FileInputStream excel = new FileInputStream("C:\\Users\\Dark World\\Downloads\\TestData.xls");//for reading file
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(0);//select which sheet is needed from excel
		int rowCount = sheet.getRows();
		int columnCount = sheet.getColumns();
		String[][] testdata = new String[rowCount-1][columnCount];//create for final output
		for(int i=1;i<rowCount;i++) {
			for(int j=0;j<columnCount;j++) {
				testdata[i-1][j] = sheet.getCell(j,i).getContents();
			}
		}
		return testdata;
	}
	
	@Test(dataProvider = "loginData")//connecting with data provider
	public void LoginWithBothCorrect(String user, String pass) {
		System.setProperty("webdriver.chrome.driver", "E:\\Selenium Projects\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://practicetestautomation.com/practice-test-login/");
		
		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys(user);
		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys(pass);
		WebElement loginbutton = driver.findElement(By.id("submit"));
		loginbutton.click();
		driver.quit();
	}
}
