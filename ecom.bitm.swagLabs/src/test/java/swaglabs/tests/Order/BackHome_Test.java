package swaglabs.tests.Order;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.CartPage;
import swaglabs.objrepo.CheckoutPage1;
import swaglabs.objrepo.CheckoutPage2;
import swaglabs.objrepo.CheckoutPage3;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;
import swaglabs.utility.TestDataUtility;

public class BackHome_Test extends BaseClass {

    @Test(groups = {"FT"})
    public void verifyBackHome() {

        String username = TestDataUtility.getData("TC003", 1);
        String password = TestDataUtility.getData("TC003", 2);
        String productName = TestDataUtility.getData("TC003", 3);
        String firstName = TestDataUtility.getData("TC003", 4);
        String lastName = TestDataUtility.getData("TC003", 5);
        String postalCode = TestDataUtility.getData("TC003", 6);

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(username, password);

        ProductPage productPage = new ProductPage(getDriver());
        productPage.addProductToCart(productName);
        productPage.openCart();

        CartPage cartPage = new CartPage(getDriver());
        cartPage.clickCheckout();

        CheckoutPage1 checkoutPage1 = new CheckoutPage1(getDriver());
        checkoutPage1.enterCheckoutDetails(firstName, lastName, postalCode);
        
        checkoutPage1.clickContinue();

        CheckoutPage2 checkoutPage2 = new CheckoutPage2(getDriver());
        checkoutPage2.clickFinish();

        CheckoutPage3 checkoutPage3 = new CheckoutPage3(getDriver());

        Assert.assertTrue(
                checkoutPage3.getOrderConfirmation().contains("Thank you"),
                "Order confirmation message was not displayed"
        );

        checkoutPage3.clickBackHome();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            
        wait.until(
                ExpectedConditions.visibilityOfElementLocated( By.className("inventory_list")));
       

        Assert.assertTrue( getDriver().getCurrentUrl().contains("inventory.html"),
                "Back Home did not return to the products page");
        
        Reporter.log("Back Home test passed.", true);
    }
}
