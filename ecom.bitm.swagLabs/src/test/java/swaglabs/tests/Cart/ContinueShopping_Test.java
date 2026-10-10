
package swaglabs.tests.Cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.CartPage;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;

public class ContinueShopping_Test extends BaseClass {

    @Test(groups = {"FT"})
    public void verifyContinueShopping() {

        // Log in to SauceDemo
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");

        // Add a product and open the cart
        ProductPage productPage = new ProductPage(getDriver());
        productPage.addProductToCart("Sauce Labs Backpack");
        productPage.openCart();

        // Click Continue Shopping
        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickContinueShopping();

        // Verify the product listing page
        WebDriverWait wait = new WebDriverWait( getDriver(), Duration.ofSeconds(10));
               
        boolean productListDisplayed = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list") )).isDisplayed();
                                    
        Assert.assertTrue( productListDisplayed, "Product listing page was not displayed" );
       
        Reporter.log("Continue Shopping navigation verified.", true);
    }
}
