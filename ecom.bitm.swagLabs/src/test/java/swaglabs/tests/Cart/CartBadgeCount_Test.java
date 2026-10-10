
package swaglabs.tests.Cart;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;

public class CartBadgeCount_Test extends BaseClass {

    @Test(groups = {"FT"})
    public void verifyCartBadgeCount() {

        // Log in to SauceDemo
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");

        // Add a product to the cart
        ProductPage productPage = new ProductPage(getDriver());
        productPage.addProductToCart("Sauce Labs Backpack");

        // Verify cart badge count
        String badgeCount = productPage.getCartBadgeCount();

        Assert.assertEquals(badgeCount,"1","Cart badge count was incorrect");

        Reporter.log("Cart badge count verified successfully.", true);
    }
}
