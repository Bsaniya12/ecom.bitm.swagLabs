package swaglabs.tests.Product;

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

public class ProductDetails_Test extends BaseClass {

    @Test(groups = {"FT"})
    public void verifyProductDetailsPage() {

        String username = "standard_user";
        String password = "secret_sauce";
        String productName = "Sauce Labs Backpack";

        // Log in to SauceDemo
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        WebDriverWait wait = new WebDriverWait(
                getDriver(),
                Duration.ofSeconds(10)
        );

        // Wait for the product page to load
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_list")
                )
        );

        // Open product details
        ProductPage productPage = new ProductPage(getDriver());
        productPage.openProductDetails(productName);

        // Verify product details
        WebElement productNameElement = wait.until(  ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_details_name")));
     

        String actualProductName = productNameElement.getText();

        Assert.assertEquals( actualProductName,productName,
        		 "Product details page was not displayed correctly");
       

        Reporter.log( "Product details page verified successfully.", true);
               
    }
}
