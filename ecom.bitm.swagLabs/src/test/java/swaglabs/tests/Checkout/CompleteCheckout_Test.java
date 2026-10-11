
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
import swaglabs.objrepo.CheckoutPage2;
import swaglabs.objrepo.CheckoutPage3;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;
import swaglabs.utility.TestDataUtility;

public class CompleteCheckout_Test extends BaseClass {

    @Test(priority = 2, groups = {"IT"})
    public void completeCheckoutTest() throws InterruptedException {

        // Read test data from Excel
        String username = TestDataUtility.getData("TC003", 1);
        String password = TestDataUtility.getData("TC003", 2);
        String productName = TestDataUtility.getData("TC003", 3);
        String firstName = TestDataUtility.getData("TC003", 4);
        String lastName = TestDataUtility.getData("TC003", 5);
        String postalCode = TestDataUtility.getData("TC003", 6);

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

        // Proceed to checkout
        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickCheckout();

        waitForSeconds(2);

        // Enter customer details
        CheckoutPage1 checkoutPage1 = new CheckoutPage1(getDriver());
        checkoutPage1.enterCheckoutDetails(
                firstName, lastName, postalCode);

        waitForSeconds(1);
        checkoutPage1.clickContinue();

        waitForSeconds(2);

        // Verify the Checkout Overview page
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
                

        String title = wait.until( ExpectedConditions.visibilityOfElementLocated(By.className("title"))).getText();
   

        Assert.assertEquals(
                title,
                "Checkout: Overview",
                "Checkout Overview page was not displayed");

        // Finish the purchase
        CheckoutPage2 checkoutPage2 = new CheckoutPage2(getDriver());
        checkoutPage2.clickFinish();

        waitForSeconds(2);

        // Verify the order confirmation message
        CheckoutPage3 checkoutPage3 = new CheckoutPage3(getDriver());
        String confirmation = checkoutPage3.getOrderConfirmation();

        Assert.assertEquals( confirmation, "Thank you for your order!",
        		"Order confirmation was not displayed");
                                             
        Reporter.log("Checkout completed successfully.", true);
    }
}
