package loginTestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataDrivenUsingPOI {

	static List<String> usernamelist = new ArrayList<String>();
	static List<String> passwordlist = new ArrayList<String>();
	
	public void readExcel() throws IOException {
		FileInputStream excel = new FileInputStream("C:\\Users\\Dark World\\Downloads\\TestData1.xlsx");
		Workbook workbook = new XSSFWorkbook(excel);
		Sheet sheet = workbook.getSheetAt(0);//apache.poi.ss. library
		Iterator<Row> rowiterator = sheet.iterator();//read all rows
		
		while(rowiterator.hasNext()) {
			Row rowvalue = rowiterator.next();//to take value
			Iterator<Cell> columnIterator = rowvalue.iterator();//cell library apache.poi
			int i=2;
			while(columnIterator.hasNext()) {
				if(i%2==0) {
					usernamelist.add(columnIterator.next().getStringCellValue());//adding values to arraylist
				}else {
					passwordlist.add(columnIterator.next().getStringCellValue());
				}
				i++;
			}	
		}
	}
	
	public void Login(String user, String pass) {
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
	
	public void excute() {
		for(int i=0;i<usernamelist.size();i++) {
			Login(usernamelist.get(i),passwordlist.get(i));
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		DataDrivenUsingPOI usingPOI = new DataDrivenUsingPOI();
		usingPOI.readExcel();
		System.out.println("usernamelist" +usernamelist);
		System.out.println("passwordlist" +passwordlist);
		usingPOI.excute();
	}

}
