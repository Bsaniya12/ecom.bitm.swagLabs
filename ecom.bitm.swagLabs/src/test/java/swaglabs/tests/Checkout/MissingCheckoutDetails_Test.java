package swaglabs.tests.Checkout;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.CartPage;
import swaglabs.objrepo.CheckoutPage1;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;
import swaglabs.utility.TestDataUtility;

public class MissingCheckoutDetails_Test extends BaseClass {

    @Test(groups = { "NT" })
    public void verifyMissingCheckoutDetails()
            throws InterruptedException, IOException {

        String username =
                TestDataUtility.getData("TC005", 1);

        String password =
                TestDataUtility.getData("TC005", 2);

        String productName =
                TestDataUtility.getData("TC005", 3);

        String firstName =
                TestDataUtility.getData("TC005", 4);

        String lastName =
                TestDataUtility.getData("TC005", 5);

        String postalCode =
                TestDataUtility.getData("TC005", 6);

        LoginPage loginPage =
                new LoginPage(getDriver());

        loginPage.login(username, password);

        waitForSeconds(2);

        ProductPage productPage =
                new ProductPage(getDriver());

        productPage.addProductToCart(productName);

        waitForSeconds(2);

        productPage.openCart();

        waitForSeconds(2);

        CartPage cartPage =
                new CartPage(getDriver());

        cartPage.clickCheckout();

        waitForSeconds(2);

        CheckoutPage1 checkoutPage1 =
                new CheckoutPage1(getDriver());

        checkoutPage1.enterCheckoutDetails(
                firstName,
                lastName,
                postalCode
        );

        waitForSeconds(1);

        getDriver().findElement(
                By.id("continue")
        ).click();

        waitForSeconds(2);

        String errorMessage =
                getDriver()
                        .findElement(
                                By.cssSelector(
                                        "h3[data-test='error']"
                                )
                        )
                        .getText();

        System.out.println(
                "Error Message: " + errorMessage
        );

        Assert.assertTrue(
                errorMessage.contains(
                        "First Name is required"
                ),
                "Checkout validation error was not displayed"
        );

        File screenshot =
                ((TakesScreenshot) getDriver())
                        .getScreenshotAs(
                                OutputType.FILE
                        );

        File destination =
                new File(
                        "screenshots/TC-N-002_MissingCheckoutDetails.png"
                );

        destination.getParentFile().mkdirs();

        FileHandler.copy(
                screenshot,
                destination
        );

        System.out.println(
                "Screenshot saved: "
                + destination.getAbsolutePath()
        );

        System.out.println(
                "Missing Checkout Details Negative Test Passed"
        );
    }
}