package swaglabs.tests.Checkout;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.CartPage;
import swaglabs.objrepo.CheckoutPage1;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;
import swaglabs.utility.TestDataUtility;

public class BlankPostalCode_Test extends BaseClass {

    @Test(groups = {"NT"})
    public void verifyBlankPostalCode() {

        // Read test data from Excel
        String username = TestDataUtility.getData("TC005", 1);
        String password = TestDataUtility.getData("TC005", 2);
        String productName = TestDataUtility.getData("TC005", 3);
        String firstName = TestDataUtility.getData("TC005", 4);
        String lastName = TestDataUtility.getData("TC005", 5);

        // Log in
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        // Add product and open cart
        ProductPage productPage = new ProductPage(getDriver());
        productPage.addProductToCart(productName);
        productPage.openCart();

        // Open checkout
        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickCheckout();

        // Leave postal code blank
        CheckoutPage1 checkoutPage = new CheckoutPage1(getDriver());

        checkoutPage.enterCheckoutDetails(
                firstName, lastName, ""
        );

        checkoutPage.clickContinue();

        // Verify validation message
        WebDriverWait wait = new WebDriverWait(
                getDriver(),
                Duration.ofSeconds(10)
        );

        String errorMessage = wait.until( ExpectedConditions.visibilityOfElementLocated( By.cssSelector("h3[data-test='error']") )
                ).getText();

        Assert.assertTrue(errorMessage.contains("Postal Code is required"),
        		"Unexpected validation message: " + errorMessage);                          
                
        Reporter.log(  "Blank postal code validation test passed.",true);
                 
    }
}
