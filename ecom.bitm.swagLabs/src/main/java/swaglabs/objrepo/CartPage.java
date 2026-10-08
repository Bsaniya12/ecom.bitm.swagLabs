package swaglabs.objrepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;

    // Locators
    By checkoutButton = By.id("checkout");
    By removeBackpack = By.id("remove-sauce-labs-backpack");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Remove Backpack from cart
    public void removeBackpack() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(removeBackpack)
        ).click();
    }

    // Click Checkout
    public void clickCheckout() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        ).click();
    }
}