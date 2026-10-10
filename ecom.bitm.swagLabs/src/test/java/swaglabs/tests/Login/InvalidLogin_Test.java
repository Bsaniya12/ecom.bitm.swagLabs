
package swaglabs.tests.Login;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.LoginPage;

public class InvalidLogin_Test extends BaseClass {

    @Test(priority = 1, groups = {"NT"})
    public void verifyInvalidLogin() {

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.login("invalid_user", "wrong_password");

        WebDriverWait wait = new WebDriverWait( getDriver(),Duration.ofSeconds(10));
                   
        wait.until(ExpectedConditions.visibilityOfElementLocated(
        		 org.openqa.selenium.By.cssSelector("h3[data-test='error']")));
        
               
        String errorMessage = loginPage.getErrorMessage();

        Assert.assertTrue( errorMessage.contains("Username and password do not match"),
                "Expected invalid-login error was not displayed");
        
        Reporter.log("Invalid login test passed.", true);
    }
}
