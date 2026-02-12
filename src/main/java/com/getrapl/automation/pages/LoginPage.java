package com.getrapl.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // ===== LOCATORS =====
    private By emailField = By.id("loginID");
    private By passwordField = By.id("password");
    private By nextButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//*[contains(text(),'Invalid')]");
    private By dashboardElement = By.xpath("//*[contains(text(),'Dashboard')]");

    // ===== ACTIONS =====

    public void enterEmail(String email) {
        var element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        element.clear();
        element.sendKeys(email);
    }

    

    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void login(String email, String password) {

        // Step 1: Enter Email
        enterEmail(email);

        // Step 2: Click Next (to go to password screen)
        clickNext();

        // Step 3: Wait for password field
        enterPassword(password);

        // Step 4: Click Login
        clickNext();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public boolean isDashboardDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardElement)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickNextWithoutEmail() {
        clickNext();
    }
}
