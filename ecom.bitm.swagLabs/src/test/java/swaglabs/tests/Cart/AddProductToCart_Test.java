package swaglabs.tests.Cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;
import swaglabs.utility.TestDataUtility;

public class AddProductToCart_Test extends BaseClass {

    @Test(groups = { "FT", "SM" })
    public void addProductToCartTest() throws InterruptedException {

        // Read test data from Excel
        String username =
                TestDataUtility.getData("TC001", 1);

        String password =
                TestDataUtility.getData("TC001", 2);

        String productNameFromExcel =
                TestDataUtility.getData("TC001", 3);

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

        productPage.addProductToCart(
                productNameFromExcel
        );

        waitForSeconds(2);

        // Open cart
        productPage.openCart();

        waitForSeconds(2);

        // Verify product
        WebDriverWait wait =
                new WebDriverWait(
                        getDriver(),
                        Duration.ofSeconds(10)
                );

        String actualProductName =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        By.className(
                                                "inventory_item_name"
                                        )
                                )
                ).getText();

        Assert.assertEquals(
                actualProductName,
                productNameFromExcel,
                "Product was not added to cart"
        );

        System.out.println(
                "Product successfully added to cart: "
                        + productNameFromExcel
        );
    }
}