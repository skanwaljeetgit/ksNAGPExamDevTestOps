package com.estore.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.estore.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author kanwaljeetsingh BaseClass contains methods used for various
 *         operations required to initialize test execution
 *
 */
public class BaseClass {

	public static Properties prop;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return driver.get();
	}

	public void openApplication(String browserName) throws IOException, InterruptedException {
		// select browser for execution based on parameters
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
			break;
		case "internetexplorer":
			WebDriverManager.iedriver().setup();
			driver.set(new InternetExplorerDriver());
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
			break;
		}

		// Maximize the screen
		getDriver().manage().window().maximize();
		// Delete all the cookies
		getDriver().manage().deleteAllCookies();
		// Implicit TimeOuts
		getDriver().manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitWait")),
				TimeUnit.SECONDS);
		// PageLoad TimeOuts
		getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageLoadTimeOut")),
				TimeUnit.SECONDS);
		// Launching the URL
		getDriver().get(prop.getProperty("url"));
	}

	// startReport method of ExtentManager is called
	// config.properties file is loaded
	@BeforeSuite(groups = { "Smoke", "Sanity", "Regression" })
	public void beforeSuite() throws IOException {
		ExtentManager.startReport();
		prop = new Properties();
		FileInputStream fileInput = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\configuration\\config.properties");
		System.out.println(System.getProperty("user.dir"));
		prop.load(fileInput);
	}

	// browserName parameter passed to openApplication method above to select
	// browser for execution
	@Parameters("browserName")
	@BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void setup(String browserName) throws IOException, InterruptedException {
		openApplication(browserName);
	}

	// quit all browser sessions
	@AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
	public void tearDown() {
		getDriver().quit();
	}

	// endReport method of ExtentManager is called
	@AfterSuite(groups = { "Smoke", "Sanity", "Regression" })
	public void afterSuite() {
		ExtentManager.endReport();
	}
}
