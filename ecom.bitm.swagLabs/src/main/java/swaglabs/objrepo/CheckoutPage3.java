package swaglabs.objrepo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage3 {

    WebDriver driver;

    @FindBy(className = "complete-header")
    WebElement orderConfirmation;

    @FindBy(id = "back-to-products")
    WebElement backHomeButton;

    public CheckoutPage3(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getOrderConfirmation() {
        return orderConfirmation.getText();
    }

    public void clickBackHome() {
        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(backHomeButton)
        ).click();
    }
}
