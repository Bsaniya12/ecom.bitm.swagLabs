package swaglabs.objrepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage1 {

    WebDriver driver;

    // Locators
    By firstName = By.id("first-name");
    By lastName = By.id("last-name");
    By postalCode = By.id("postal-code");
    By continueButton = By.id("continue");

    // Constructor
    public CheckoutPage1(WebDriver driver) {
        this.driver = driver;
    }

    // Enter First Name
    public void enterFirstName(String first) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until( ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(first);
    }

    // Enter Last Name
    public void enterLastName(String last) {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until( ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(last);
    }

    // Enter Postal Code
    public void enterPostalCode(String postal) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until( ExpectedConditions.visibilityOfElementLocated(postalCode)).sendKeys(postal);
    }

    // Click Continue
    public void clickContinue() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    // Enter complete checkout details
    public void enterCheckoutDetails( String first, String last, String postal) {

        enterFirstName(first);
        enterLastName(last);
        enterPostalCode(postal);
    }
}