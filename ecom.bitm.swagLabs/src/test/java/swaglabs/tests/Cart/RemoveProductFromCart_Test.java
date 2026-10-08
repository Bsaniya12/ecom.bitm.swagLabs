package swaglabs.tests.Cart;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.CartPage;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;
import swaglabs.utility.TestDataUtility;

public class RemoveProductFromCart_Test extends BaseClass {

    @Test(groups = { "FT" })
    public void removeProductFromCartTest() throws InterruptedException {

        // Read test data from Excel
        String username =
                TestDataUtility.getData("TC002", 1);

        String password =
                TestDataUtility.getData("TC002", 2);

        String productName =
                TestDataUtility.getData("TC002", 3);

        // Login
        LoginPage loginPage =
                new LoginPage(getDriver());

        loginPage.login(username, password);

        waitForSeconds(2);

        // Add product to cart
        ProductPage productPage =
                new ProductPage(getDriver());

        productPage.addProductToCart(productName);

        waitForSeconds(2);

        // Open cart
        productPage.openCart();

        waitForSeconds(2);

        // Remove product
        CartPage cartPage =
                new CartPage(getDriver());

        cartPage.removeBackpack();

        waitForSeconds(2);

        // Verify product is removed
        boolean productPresent =
                !getDriver()
                        .findElements(
                                By.className("inventory_item_name")
                        )
                        .isEmpty();

        Assert.assertFalse(
                productPresent,
                "Product was not removed from cart"
        );

        System.out.println(
                "Product successfully removed from cart: "
                        + productName
        );
    }
}