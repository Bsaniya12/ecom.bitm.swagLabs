
package swaglabs.tests.Login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.LoginPage;

public class BlankUsername_Test extends BaseClass {

    @Test(groups = {"NT"})
    public void verifyBlankUsername() {

        LoginPage loginPage = new LoginPage(getDriver());

        // Enter a password without a username
        loginPage.login("", "secret_sauce");

        WebDriverWait wait = new WebDriverWait(
                getDriver(),
                Duration.ofSeconds(10)
        );

        wait.until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector("h3[data-test='error']")));
        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue( errorMessage.contains("Username is required"),
        		"Expected blank-username error was not displayed");
          
        Reporter.log("Blank username test passed.", true);
    }
}
