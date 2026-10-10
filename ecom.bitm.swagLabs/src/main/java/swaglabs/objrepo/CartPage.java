
package swaglabs.objrepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    WebDriver driver;

    // Locators using @FindBy
    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement removeBackpack;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Remove Backpack from cart
    public void removeBackpack() {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(removeBackpack)
        ).click();
    }

    // Click Checkout
    public void clickCheckout() {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        ).click();
    }
    

    // Click Continue Shopping
    public void clickContinueShopping() {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("continue-shopping")
                )
        ).click();
    }

}
