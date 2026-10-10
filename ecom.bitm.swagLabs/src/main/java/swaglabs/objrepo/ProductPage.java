
package swaglabs.objrepo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ProductPage {

    WebDriver driver;

    // Locators using @FindBy
    @FindBy(className = "shopping_cart_link")
    WebElement cartButton;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutButton;

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Add product to cart using product name from test data
    public void addProductToCart(String productName) {

        String productId = productName.toLowerCase()
                .replace("sauce labs ", "")
                .replace(" ", "-");

        String addToCartId = "add-to-cart-sauce-labs-" + productId;

        driver.findElement(By.id(addToCartId)).click();
    }

    // Open shopping cart
    public void openCart() {
        cartButton.click();
    }

    // Logout from the application
    public void logout() {
        menuButton.click();
        logoutButton.click();
    }

    // Sort products by name from A to Z
    public void sortProductsByNameAtoZ() {
        Select sortDropdown = new Select(
                driver.findElement(
                        By.className("product_sort_container")
                )
        );

        sortDropdown.selectByValue("az");
    }
        

        // Sort products by price from low to high
        public void sortProductsByPriceLowToHigh() {
            Select sortDropdown = new Select(
                    driver.findElement(
                            By.className("product_sort_container")
                    )
            );

            sortDropdown.selectByValue("lohi");
        }
            




        // Open product details using the product name
        public void openProductDetails(String productName) {

            By productLocator = By.xpath(
                    "//div[contains(@class,'inventory_item_name')"
                            + " and normalize-space()='" + productName + "']"
            );

            WebDriverWait wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(10)
            );

            WebElement product = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(productLocator)
            );

            product.click();
        }

        // Get cart badge count
        public String getCartBadgeCount() {
            WebDriverWait wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(10)
            );

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("shopping_cart_badge")
                    )
            ).getText();
        }


        }

        


