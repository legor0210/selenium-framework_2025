package org.example.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;
import org.example.base.BaseTest;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        // You can initialize reports here if needed
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTestManager.getTest().fail(result.getThrowable());

        // Optionally capture a screenshot on failure
        Object testClass = result.getInstance();
        WebDriver driver = ((BaseTest) testClass).getDriver();
        ExtentTestManager.addScreenshot(driver, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getExtentReports().flush(); // Write everything to report
    }
}