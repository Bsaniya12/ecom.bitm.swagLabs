package swaglabs.objrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage3 {

    WebDriver driver;

    // Locator
    By orderConfirmation = By.className("complete-header");

    // Constructor
    public CheckoutPage3(WebDriver driver) {
        this.driver = driver;
    }

    // Get order confirmation message
    public String getOrderConfirmation() {

        return driver.findElement(orderConfirmation).getText();
    }
}