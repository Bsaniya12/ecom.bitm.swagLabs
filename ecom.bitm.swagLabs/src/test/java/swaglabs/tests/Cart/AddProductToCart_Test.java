
package swaglabs.tests.Cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;
import swaglabs.utility.TestDataUtility;

public class AddProductToCart_Test extends BaseClass {

    @Test(priority = 1, groups = {"FT", "SM"})
    public void addProductToCartTest() throws InterruptedException {

        // Read test data from Excel
        String username = TestDataUtility.getData("TC001", 1);
        String password = TestDataUtility.getData("TC001", 2);
        String productNameFromExcel = TestDataUtility.getData("TC001", 3);

        // Log in to SauceDemo
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        waitForSeconds(2);

        // Add the selected product to the cart
        ProductPage productPage = new ProductPage(getDriver());
        productPage.addProductToCart(productNameFromExcel);

        waitForSeconds(2);

        // Open the shopping cart
        productPage.openCart();

        // Wait for the cart product to appear
        WebDriverWait wait = new WebDriverWait( getDriver(), Duration.ofSeconds(10));
    

        WebElement cartProduct = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".cart_item .inventory_item_name")));
        
         // Verify the product name
        String actualProductName = cartProduct.getText();

        Assert.assertEquals(actualProductName, productNameFromExcel,
        		  "Product was not added to the cart");
               
        Reporter.log("Product successfully added to cart: " + productNameFromExcel, true);
       
    }
}
