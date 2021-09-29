package project;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;

public class ExtendReportManager {
	// For designing report in HTML.

	public static ExtentReports report;

	public static ExtentReports getReportInstance() {

		if (report == null) {
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
					System.getProperty("user.dir") + "//test-output//ExtentReport.html");
			report = new ExtentReports();
			report.attachReporter(htmlReporter);

			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("IDE", "Eclipse");
			report.setSystemInfo("Browser", "Chrome");

			htmlReporter.config().setDocumentTitle("Automation Results");
			htmlReporter.config().setReportName("Build configuration Results");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);

		}
		return report;
	}
}
