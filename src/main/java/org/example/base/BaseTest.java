package org.example.base;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.example.utils.ExtentManager;
import org.example.utils.ExtentTestManager;
import org.example.utils.Log;

import java.lang.reflect.Method;

public class BaseTest {
    protected WebDriver driver;
    private static final String BASE_URL = "https://www.facebook.com/";

    @BeforeSuite
    public void beforeSuite() {
        ExtentManager.initReports();
    }

    @BeforeMethod
    public void setup(Method method) {
        driver = new ChromeDriver();
        ExtentTestManager.startTest(method.getName());
        Log.info("Starting test: " + method.getName());

        driver.get(BASE_URL);
        Log.info("Navigated to login page: " + BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
//            driver.quit();
            Log.info("Driver quit.");
        }
        ExtentManager.flushReports();
    }
}