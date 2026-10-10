
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

public class BlankPassword_Test extends BaseClass {

    @Test(groups = {"NT"})
    public void verifyBlankPassword() {

        LoginPage loginPage = new LoginPage(getDriver());

        // Enter username without a password
        loginPage.login("standard_user", "");

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                                          
        wait.until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector("h3[data-test='error']")));
           
        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue(errorMessage.contains("Password is required"),
        		"Expected blank-password error was not displayed");
 
        Reporter.log("Blank password test passed.", true);
    }
}
