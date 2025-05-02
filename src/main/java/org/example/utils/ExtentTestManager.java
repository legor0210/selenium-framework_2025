package org.example.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void startTest(String testName) {
        ExtentTest extentTest = ExtentManager.getExtentReports().createTest(testName);
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void addScreenshot(WebDriver driver, String methodName) {
        try {
            // Capture as BASE64
            String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

            // Attach to report using base64
            getTest().fail("Screenshot:",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } catch (Exception e) {
            getTest().warning("Could not attach screenshot: " + e.getMessage());
        }
    }

}