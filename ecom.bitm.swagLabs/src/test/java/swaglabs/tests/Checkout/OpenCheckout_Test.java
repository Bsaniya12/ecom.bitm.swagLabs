package swaglabs.tests.Checkout;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.CartPage;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;
import swaglabs.utility.TestDataUtility;

public class OpenCheckout_Test extends BaseClass {

    @Test(groups = {"IT", "SM"})
    public void openCheckoutTest()
            throws InterruptedException {

        // Read test data from Excel
        String username =
                TestDataUtility.getData("TC003", 1);

        String password =
                TestDataUtility.getData("TC003", 2);

        String productName =
                TestDataUtility.getData("TC003", 3);

        // Login
        LoginPage loginPage =
                new LoginPage(getDriver());

        loginPage.login(
                username,
                password
        );

        waitForSeconds(2);

        // Add product to cart
        ProductPage productPage =
                new ProductPage(getDriver());

        productPage.addProductToCart(productName);

        waitForSeconds(2);

        // Open cart
        productPage.openCart();

        waitForSeconds(2);

        // Click Checkout
        CartPage cartPage =
                new CartPage(getDriver());

        cartPage.clickCheckout();

        waitForSeconds(2);

        // Verify Checkout Information page
        WebDriverWait wait =
                new WebDriverWait(
                        getDriver(),
                        Duration.ofSeconds(10)
                );

        String title =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        By.className("title")
                                )
                ).getText();

        Assert.assertEquals(
                title,
                "Checkout: Your Information",
                "Checkout Information page was not displayed"
        );

        System.out.println(
                "Checkout Information page displayed successfully."
        );
    }
}