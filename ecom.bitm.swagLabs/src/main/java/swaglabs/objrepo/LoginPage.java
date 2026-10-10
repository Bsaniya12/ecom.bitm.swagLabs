
package swaglabs.objrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    // Locators using @FindBy
    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement loginButton;
    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Click login
    public void clickLogin() {
        loginButton.click();
    }

    // Complete login
    public void login(String user, String pass) {
        username.sendKeys(user);
        password.sendKeys(pass);
        clickLogin();
    }
        

        // Get login error message
        public String getErrorMessage() {
            return errorMessage.getText();
        }
}