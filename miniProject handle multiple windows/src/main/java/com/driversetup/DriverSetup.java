package com.driversetup;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {
	private static WebDriver driver;
	static String baseURL = "http://cookbook.seleniumacademy.com/Config.html";

	public static WebDriver getWebDriver() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Select one browser from below :" + "\n" + "1.Firefox" + "\n" + "2.Chrome");
		int browser = sc.nextInt();
		if (browser == 1) {
			// To select Firefox browser
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/resources/drivers/geckodriver.exe");
			WebDriver driver = new FirefoxDriver();

			// To maximize window
			driver.manage().window().maximize();

			System.out.println(" Firefox selected and windows maximized" + "\n");
			driver.get(baseURL);
			return driver;

		}

		else if (browser == 2) {
			// To select Chrome browser
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/resources/drivers/chromedriver.exe");
			WebDriver driver = new ChromeDriver();

			// To maximize window
			driver.manage().window().maximize();

			System.out.println(" Chrome selected and windows maximized" + "\n");
			driver.get(baseURL);
			return driver;
		}

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		return driver;

	}

}
