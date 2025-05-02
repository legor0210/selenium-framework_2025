package org.example.pages;

import org.example.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    private static final String BASE_URL = "https://www.facebook.com/";
    By username = By.id("email");
    By password = By.id("pass");
    By loginBtn = By.name("login");

    By dashboardHeader = By.id("dashboardHeader"); // assumed ID of successful login element


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToSite() {
        driver.get(BASE_URL);
        Log.info("Navigated to login page: " + BASE_URL);
    }
    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }

    public boolean isLoginSuccessful() {
        try {
            return driver.findElement(dashboardHeader).isDisplayed();
        } catch (Exception e) {
            return false; // Element not found or not visible
        }
    }
}