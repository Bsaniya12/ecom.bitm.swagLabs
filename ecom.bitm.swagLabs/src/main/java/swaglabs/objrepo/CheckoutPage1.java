
package swaglabs.objrepo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage1 {

    WebDriver driver;

    // Locators using @FindBy
    @FindBy(id = "first-name")
    WebElement firstName;

    @FindBy(id = "last-name")
    WebElement lastName;

    @FindBy(id = "postal-code")
    WebElement postalCode;

    @FindBy(id = "continue")
    WebElement continueButton;

    // Constructor
    public CheckoutPage1(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Enter First Name
    public void enterFirstName(String first) {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.visibilityOf(firstName)
        ).sendKeys(first);
    }

    // Enter Last Name
    public void enterLastName(String last) {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.visibilityOf(lastName)
        ).sendKeys(last);
    }

    // Enter Postal Code
    public void enterPostalCode(String postal) {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.visibilityOf(postalCode)
        ).sendKeys(postal);
    }

    // Click Continue
    public void clickContinue() {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }

    // Enter complete checkout details
    public void enterCheckoutDetails(
            String first,
            String last,
            String postal) {

        enterFirstName(first);
        enterLastName(last);
        enterPostalCode(postal);
    }
}
