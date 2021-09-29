package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import com.driversetup.DriverSetup;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class BaseMethods {

	public ExtentReports report = ExtendReportManager.getReportInstance();
	public ExtentTest logger;

	static WebDriver driver;
	static String baseURL = "http://cookbook.seleniumacademy.com/Config.html";

	// Method to setup driver
	public WebDriver setupDriver() {
		// get the driver, launch the wab application using baseURL
		driver = DriverSetup.getWebDriver();
		driver.get(baseURL);
		String windowHandle = driver.getWindowHandle();

		return driver;
	}

	/*********** Method to check home page title *******/
	/*****
	 * @throws IOException
	 **********/
	public void checkHomepage() throws IOException {

		String expectedTitle = "Build my Car - Configuration";
		String title = driver.getTitle();

		Assert.assertEquals(title, expectedTitle);
		System.out.println("Home page title is :" + title + "\n");

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("./Screenshots/beforeSelection.png"));

	}

	/**********
	 * Method to select car specification
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 *******/

	public WebDriver buildCar() throws NullPointerException, InterruptedException, IOException {
		try {

			Select dropdown = new Select(driver.findElement(By.name("make")));
			dropdown.selectByValue("audi");
			Thread.sleep(1000);

			FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "/excel/input.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			XSSFCell cell1 = sheet.getRow(0).getCell(0);
			String cellValue1 = cell1.getStringCellValue();
			System.out.println(cellValue1);

			if (cellValue1.toString().contentEquals("Diesel")) {
				WebElement fuelType = driver.findElement(By.xpath("//input[@value='Diesel']"));
				fuelType.click();

			} else {
				WebElement fuelType = driver.findElement(By.xpath("//input[@value='Petrol']"));
				fuelType.click();
			}
			Thread.sleep(1000);

			XSSFCell cell2 = sheet.getRow(0).getCell(1);
			String cellValue2 = cell2.getStringCellValue();
			System.out.println(cellValue2);

			if (cell2.toString().contentEquals("Parking Sensor")) {
				WebElement checkbox = driver.findElement(By.xpath("//input[@value='ParkingSensor']"));
				checkbox.click();
			} else {
				WebElement checkbox = driver.findElement(By.xpath("//input[@value='ABS']"));
				checkbox.click();
			}
			Thread.sleep(1000);

			Select color = new Select(driver.findElement(By.name("color")));
			color.selectByValue("wt");
			System.out.println("Required car model, feature and color selected" + "\n");
			return driver;
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return driver;
	}

	/**********
	 * Method to open multiple windows
	 * 
	 * @throws InterruptedException
	 *******/
	public WebDriver clickButton() throws NullPointerException, InterruptedException {

		WebElement next = driver.findElement(By.name("Conf_Next"));
		next.click();
		Thread.sleep(1000);

		WebElement visitbutton = driver.findElement(By.id("visitbutton"));
		visitbutton.click();
		Thread.sleep(1000);

		WebElement chatbutton = driver.findElement(By.id("chatbutton"));
		chatbutton.click();
		Thread.sleep(1000);

		WebElement help = driver.findElement(By.id("helpbutton"));
		help.click();
		Thread.sleep(1000);

		System.out.println("Multiple windows are opened" + "\n");

		return driver;

	}

	/********** Method to count number of windows opened *******/
	public WebDriver noOfBrowserWindows() {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println("No. of windows opened: " + tabs.size() + "\n");

		return driver;

	}

	/********** Method to find "Visit Us" browser window *******/
	public void checkVisitUsTab() throws InterruptedException {

		// String windowHandle = driver.getWindowHandle();
		Set<String> handler = driver.getWindowHandles();
		Iterator<String> iterator = handler.iterator();

		String homepageID = iterator.next();
		System.out.println("Homepage ID is : " + homepageID + "\n");

		String helpwindowID = iterator.next();
		System.out.println("help window ID is: " + helpwindowID + "\n");

		driver.switchTo().window(helpwindowID);
		Thread.sleep(2000);
		// driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		String chatwindowID = iterator.next();
		System.out.println("chat window ID is: " + chatwindowID + "\n");

		driver.switchTo().window(chatwindowID);
		Thread.sleep(2000);
		// driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		String visitwindowID = iterator.next();
		System.out.println("Visit window ID is: " + visitwindowID + "\n");
		driver.switchTo().window(visitwindowID);
		Thread.sleep(2000);
		// driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

		String title = driver.getTitle();
		Assert.assertEquals(title, "Visit Us");
		System.out.println("Visit Us browser window found" + "\n");
		driver.close();

		driver.switchTo().window(homepageID);
		Thread.sleep(2000);
		// driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	}

	/********** Method to take screenshot *******/
	public void captureScreenshot() throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("./Screenshots/afterSelection.png"));
	}

	/********** Method to close browser window *******/
	public void tearDown() {

		driver.close();
	}

	/********** Method to close browser *******/
	public void quitBrowser() {

		driver.quit();
		System.out.println("Browser is successfully closed");
	}

}
