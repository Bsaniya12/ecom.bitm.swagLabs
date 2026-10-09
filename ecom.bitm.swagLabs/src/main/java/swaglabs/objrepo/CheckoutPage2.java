package swaglabs.objrepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage2 {

    WebDriver driver;

    // Locator
    By finishButton = By.id("finish");

    // Constructor
    public CheckoutPage2(WebDriver driver) {
        this.driver = driver;
    }

    // Click Finish
    public void clickFinish() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)).click();
       
    }
}