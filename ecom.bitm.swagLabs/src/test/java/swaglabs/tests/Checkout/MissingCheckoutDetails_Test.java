
package swaglabs.tests.Checkout;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.CartPage;
import swaglabs.objrepo.CheckoutPage1;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;
import swaglabs.utility.TestDataUtility;

public class MissingCheckoutDetails_Test extends BaseClass {

    @Test(priority = 2, groups = {"NT"})
    public void verifyMissingCheckoutDetails()
            throws InterruptedException, IOException {

        // Read test data from Excel
        String username = TestDataUtility.getData("TC005", 1);
        String password = TestDataUtility.getData("TC005", 2);
        String productName = TestDataUtility.getData("TC005", 3);
        String firstName = TestDataUtility.getData("TC005", 4);
        String lastName = TestDataUtility.getData("TC005", 5);
        String postalCode = TestDataUtility.getData("TC005", 6);

        // Log in to SauceDemo
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        waitForSeconds(2);

        // Add a product to the cart
        ProductPage productPage = new ProductPage(getDriver());
        productPage.addProductToCart(productName);

        waitForSeconds(2);
        productPage.openCart();

        waitForSeconds(2);

        // Open the checkout page
        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickCheckout();

        waitForSeconds(2);

        // Enter checkout details
        CheckoutPage1 checkoutPage1 = new CheckoutPage1(getDriver());
        checkoutPage1.enterCheckoutDetails(
                firstName, lastName, postalCode);

        waitForSeconds(1);

        // Submit the form
        getDriver().findElement(By.id("continue")).click();

        waitForSeconds(2);

        // Read the validation message
        String errorMessage = getDriver().findElement(By.cssSelector("h3[data-test='error']")).getText();

        Reporter.log("Error Message: " + errorMessage, true);

        // Verify the validation message
        Assert.assertTrue(
                errorMessage.contains("First Name is required"),
                "Checkout validation error was not displayed");

        // Capture a screenshot
        File screenshot = ((TakesScreenshot) getDriver())
                .getScreenshotAs(OutputType.FILE);

        File destination = new File("screenshots/TC-N-002_MissingCheckoutDetails.png");
                

        destination.getParentFile().mkdirs();
        FileHandler.copy(screenshot, destination);

        Reporter.log("Screenshot saved: " + destination.getAbsolutePath(), true);
                
        Reporter.log("Missing Checkout Details Negative Test Passed",true);
    }
               
}
