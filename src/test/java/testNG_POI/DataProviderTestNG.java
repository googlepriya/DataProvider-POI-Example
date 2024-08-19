package testNG_POI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataProviderTestNG {
	public void readExcel() throws IOException, BiffException {
		FileInputStream excel = new FileInputStream("C:\\Users\\gokulapriya\\google\\selenium-cucumber-workspace\\DataProvider-POI-Example\\login_data.xlsx");
		Workbook workbook =  Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(0);
		
		int rowCount = sheet.getRows();
		int columnCount = sheet.getColumns();
		
		String testData[][] = new String[rowCount-1][columnCount];	
	}
	
	@DataProvider(name="login data")
	public void excuteTest() {
		for(int i=0; i<usernameList.size();i++ ) {
			login(usernameList.get(i),pwdList.get(i));
		}
	}
	
	@Test(dataProvider = "login data")
	public void login(String uName, String uPwd) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(uPwd);
		driver.findElement(By.id("login-button")).click();		
	}

}
