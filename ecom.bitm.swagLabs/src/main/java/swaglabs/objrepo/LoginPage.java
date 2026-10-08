package swaglabs.objrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    // Locators
    By username = By.id("user-name");
    By password = By.id("password");
    By loginButton = By.id("login-button");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Enter username
    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    // Enter password
    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    // Click login
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    // Complete login
    public void login(String user, String pass) {
        enterUsername(user);
        enterPassword(pass);
        clickLogin();
    }
}