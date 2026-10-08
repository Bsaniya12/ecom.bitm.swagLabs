package swaglabs.objrepo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {

    WebDriver driver;

    By cartButton = By.className("shopping_cart_link");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Add product to cart using product name from test data
    public void addProductToCart(String productName) {

        String productId = productName
                .toLowerCase()
                .replace("sauce labs ", "")
                .replace(" ", "-");

        String addToCartId =
                "add-to-cart-sauce-labs-" + productId;

        driver.findElement(By.id(addToCartId)).click();
    }

    // Open shopping cart
    public void openCart() {
        driver.findElement(cartButton).click();
    }

    // Logout from the application
    public void logout() {

        driver.findElement(
                By.id("react-burger-menu-btn")
        ).click();

        driver.findElement(
                By.id("logout_sidebar_link")
        ).click();
    }
}