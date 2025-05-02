package org.example.base;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.example.utils.ExtentManager;
import org.example.utils.ExtentTestManager;
import org.example.utils.Log;

import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;

    @BeforeSuite
    public void beforeSuite() {
        ExtentManager.initReports();
    }

    @BeforeMethod
    public void setup(Method method) {
        String browser = System.getProperty("browser", "edge").toLowerCase();

        // Initialize the test in ExtentReports before any logging
        ExtentTestManager.startTest(method.getName());

        Log.info("Starting test: " + method.getName());

        switch (browser) {
            case "firefox":
                driver = new FirefoxDriver();
                Log.info("Launching Firefox browser.");
                break;
            case "edge":
                driver = new EdgeDriver();
                Log.info("Launching Edge browser.");
                break;
            case "chrome":
            default:
                driver = new ChromeDriver();
                Log.info("Launching Chrome browser.");
                break;
        }
    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            Log.info("Driver quit.");
        }
        ExtentManager.flushReports();
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}