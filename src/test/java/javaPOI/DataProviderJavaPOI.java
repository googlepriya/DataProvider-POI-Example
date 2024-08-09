package javaPOI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DataProviderJavaPOI {
	static List<String> usernameList = new ArrayList<String>();
	static List<String> pwdList = new ArrayList<String>();
	
	public void readExcel() throws IOException {
		FileInputStream excel = new FileInputStream("C:\\Users\\gokulapriya\\google\\selenium-cucumber-workspace\\DataProvider-POI-Example\\login_data.xlsx");
		Workbook workbook = new XSSFWorkbook(excel);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.iterator();
		
		while(rowIterator.hasNext()) {
			Row rowValue = rowIterator.next();
			Iterator<Cell> columnIterator = rowValue.iterator();
			int i=2;
			while(columnIterator.hasNext()) {
				if(i%2 == 0) {
				  usernameList.add(columnIterator.next().getStringCellValue());
				}else {
				  pwdList.add(columnIterator.next().getStringCellValue());
				}
				i++;
			}
		}
	}
	
	public void excuteTest() {
		for(int i=0; i<usernameList.size();i++ ) {
			login(usernameList.get(i),pwdList.get(i));
		}
	}
	
	public void login(String uName, String uPwd) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(uPwd);
		driver.findElement(By.id("login-button")).click();		
	}

	public static void main(String[] args) throws IOException {
		DataProviderJavaPOI login = new DataProviderJavaPOI();
		login.readExcel();
		login.excuteTest();
		System.out.println("Username List"+ usernameList);
		System.out.println("Password List"+ pwdList);

	}
}
