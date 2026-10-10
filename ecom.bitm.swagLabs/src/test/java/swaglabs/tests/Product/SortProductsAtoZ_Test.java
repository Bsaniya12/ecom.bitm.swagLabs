
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

public class SortProductsAtoZ_Test extends BaseClass {

    @Test(groups = {"FT"})
    public void verifyProductsSortedAtoZ() {

        // Log in to SauceDemo
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("standard_user", "secret_sauce");

        // Sort products from A to Z
        ProductPage productPage = new ProductPage(getDriver());
        productPage.sortProductsByNameAtoZ();

        // Get displayed product names
        List<WebElement> productElements = getDriver().findElements(
                By.className("inventory_item_name")
        );

        List<String> actualNames = new ArrayList<>();

        for (WebElement product : productElements) {
            actualNames.add(product.getText());
        }

        // Create an alphabetical copy
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        // Verify the order
        Assert.assertEquals( actualNames,expectedNames, "Products are not sorted alphabetically");

        Reporter.log("Products sorted A to Z successfully.", true);
    }
}
