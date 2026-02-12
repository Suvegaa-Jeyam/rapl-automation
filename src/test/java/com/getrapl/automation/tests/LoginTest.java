package com.getrapl.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.getrapl.automation.base.BaseTest;
import com.getrapl.automation.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(description = "Verify user cannot login with invalid credentials")
    public void verifyInvalidLogin() {

        LoginPage loginPage = new LoginPage(driver);

        // Enter invalid credentials
        loginPage.login("wrong@test.com", "wrong123");

        // Capture error message
        String actualError = loginPage.getErrorMessage();

        // Validate error contains "invalid"
        Assert.assertTrue(
                actualError.toLowerCase().contains("invalid"),
                "Expected invalid login error message, but not found."
        );
    }
}
