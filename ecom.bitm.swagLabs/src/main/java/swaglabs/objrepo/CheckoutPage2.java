
package swaglabs.objrepo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage2 {

    WebDriver driver;

    // Locator using @FindBy
    @FindBy(id = "finish")
    WebElement finishButton;

    // Constructor
    public CheckoutPage2(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Click Finish
    public void clickFinish() {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        ).click();
    }
}
