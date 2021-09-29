package project;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.Status;

public class TestClass extends BaseMethods {

	ExtentReports report = ExtendReportManager.getReportInstance();
	WebDriver driver;

	@Test
	public void test() throws Exception {
		ExtentTest logger = report.createTest("Test");

		// ******Function to open the browser*****//
		logger.log(Status.INFO, "Initiating the browser");
		setupDriver();

		// ******Function to check home page window*****//
		logger.log(Status.INFO, "Checking the homepage");
		checkHomepage();

		// ******Function to select specification of car*****//
		logger.log(Status.INFO, "Building the car configuration");
		buildCar();
		captureScreenshot();

		// ******Function to click on various buttons*****//
		logger.log(Status.INFO, "Opening different browser windows");
		clickButton();

		// ******Function to calculate number of browser windows opened*****//
		logger.log(Status.INFO, "Checking number of windows opened");
		noOfBrowserWindows();

		// ******Function to find the browser named "Visit Us" *******//
		logger.log(Status.INFO, "Checking Visit Us tab");
		checkVisitUsTab();

		// ******Function to close the browser windows*****//
		tearDown();
		logger.log(Status.INFO, "Closing the browser");
		quitBrowser();
		logger.log(Status.PASS, "Test executed successfully");
	}

	@AfterClass
	public void endReport() {

		report.flush();
	}

}
