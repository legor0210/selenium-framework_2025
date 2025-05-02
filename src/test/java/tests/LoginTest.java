package tests;

import org.example.base.BaseTest;
import org.example.data.loginData;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.example.pages.LoginPage;
import org.example.utils.Log;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setUpPage() {
        loginPage = new LoginPage(driver);
        Log.info("LoginPage object initialized.");
        loginPage.goToSite();
    }

    @Test
    public void testLogin() {
        loginPage.login(loginData.USER_INVALID, loginData.PASSWORD_INVALID);
        Log.info("Login attempted.");
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed: Dashboard not visible");
        Log.info("Login successful: Dashboard is visible.");
    }
}