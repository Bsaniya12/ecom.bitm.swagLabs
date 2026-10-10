
package swaglabs.tests.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;

public class SortProductsPrice_Test extends BaseClass {

    @Test(groups = {"FT"})
    public void verifyProductsSortedByPriceLowToHigh() {

        // Log in to SauceDemo
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");

        // Sort products by price
        ProductPage productPage = new ProductPage(getDriver());
        productPage.sortProductsByPriceLowToHigh();

        // Read displayed product prices
        List<WebElement> priceElements = getDriver().findElements(
                By.className("inventory_item_price")
        );

        List<Double> actualPrices = new ArrayList<>();

        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace("$", "");
            actualPrices.add(Double.parseDouble(priceText));
        }

        // Create a sorted copy of the prices
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        // Verify the price order
        Assert.assertEquals( actualPrices, expectedPrices, 
        		"Products are not sorted from low to high price");
               
        Reporter.log("Products sorted by price successfully.", true);
    }
}
