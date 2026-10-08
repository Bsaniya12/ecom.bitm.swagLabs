package swaglabs.tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.CartPage;
import swaglabs.objrepo.CheckoutPage1;
import swaglabs.objrepo.CheckoutPage2;
import swaglabs.objrepo.CheckoutPage3;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;
import swaglabs.utility.ExcelUtility;
import swaglabs.utility.TestDataUtility;

public class ExcelDrivenTest extends BaseClass {

    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage1 checkoutPage1;
    CheckoutPage2 checkoutPage2;
    CheckoutPage3 checkoutPage3;

    // =========================================================
    // EXCEL DATA PROVIDER
    // =========================================================

    @DataProvider(name = "testCaseData")
    public Object[][] getTestCaseData(ITestContext context) {

        String testType =
                context.getCurrentXmlTest()
                        .getParameter("testType");

        if (testType == null ||
                testType.trim().isEmpty()) {

            testType = "ALL";
        }

        ExcelUtility excel =
                new ExcelUtility();

        excel.openExcel(
                "SauceDemo_Simple_Test_Cases.xlsx",
                "Test Cases"
        );

        int rows =
                excel.getRowCount();

        int columns =
                excel.getColumnCount(7);

        int startRow = 8;

        List<Object[]> filteredData =
                new ArrayList<>();

        for (int i = startRow; i <= rows; i++) {

            String excelTestType =
                    excel.getCellData(i, 2);

            if (testType.equalsIgnoreCase("ALL") ||
                    excelTestType.equalsIgnoreCase(testType)) {

                Object[] rowData =
                        new Object[columns];

                for (int j = 0; j < columns; j++) {

                    rowData[j] =
                            excel.getCellData(i, j);
                }

                filteredData.add(rowData);
            }
        }

        excel.closeExcel();

        Object[][] data =
                new Object[filteredData.size()][];

        for (int i = 0;
                i < filteredData.size();
                i++) {

            data[i] =
                    filteredData.get(i);
        }

        System.out.println();
        System.out.println(
                "=========================================="
        );
        System.out.println(
                "XML TEST TYPE: " + testType
        );
        System.out.println(
                "Excel rows selected: " + data.length
        );
        System.out.println(
                "=========================================="
        );

        return data;
    }

    // =========================================================
    // MAIN EXCEL DRIVEN TEST
    // =========================================================

    @Test(dataProvider = "testCaseData")
    public void executeTestCase(
            String tcId,
            String module,
            String testType,
            String testCaseName,
            String preCondition,
            String steps,
            String testData,
            String expectedResult,
            String actualResult,
            String status,
            String comments)
            throws InterruptedException {

        // Initialize Page Objects
        loginPage =
                new LoginPage(getDriver());

        productPage =
                new ProductPage(getDriver());

        cartPage =
                new CartPage(getDriver());

        checkoutPage1 =
                new CheckoutPage1(getDriver());

        checkoutPage2 =
                new CheckoutPage2(getDriver());

        checkoutPage3 =
                new CheckoutPage3(getDriver());

        // Read actual test data from SauceDemo_TestData.xlsx
        String username =
                TestDataUtility.getData(
                        getTestDataId(tcId),
                        1
                );

        String password =
                TestDataUtility.getData(
                        getTestDataId(tcId),
                        2
                );

        // Login
        loginPage.login(
                username,
                password
        );

        waitForSeconds(2);

        System.out.println();
        System.out.println(
                "=========================================="
        );
        System.out.println(
                "TEST CASE: " + tcId
        );
        System.out.println(
                "MODULE: " + module
        );
        System.out.println(
                "TYPE: " + testType
        );
        System.out.println(
                "NAME: " + testCaseName
        );
        System.out.println(
                "=========================================="
        );

        // Execute corresponding test case
        switch (tcId) {

        case "TC-F-001":
            testAddProduct(tcId);
            break;

        case "TC-F-002":
            testRemoveProduct(tcId);
            break;

        case "TC-I-001":
            testCartOpensCheckout(tcId);
            break;

        case "TC-I-002":
            testCheckoutCompletesOrder(tcId);
            break;

        case "TC-S-001":
            testCartToCheckoutFlow(tcId);
            break;

        case "TC-S-002":
            testCompletePurchaseFlow(tcId);
            break;

        case "TC-SM-001":
            testAddToCartSmoke(tcId);
            break;

        case "TC-SM-002":
            testCheckoutButtonSmoke(tcId);
            break;

        case "TC-N-001":
            testBlankFirstName(tcId);
            break;

        case "TC-N-002":
            testMissingCheckoutDetails(tcId);
            break;

        default:
            Assert.fail(
                    "Unknown Test Case ID: " + tcId
            );
        }

        System.out.println(
                "------------------------------------------"
        );

        System.out.println(
                "Expected Result: " + expectedResult
        );

        System.out.println(
                "Status: PASS"
        );

        System.out.println(
                "------------------------------------------"
        );
    }

    // =========================================================
    // TC-F-001
    // =========================================================

    private void testAddProduct(String tcId)
            throws InterruptedException {

        String productName =
                getProductName(tcId);

        System.out.println(
                "Adding " + productName + "..."
        );

        productPage.addProductToCart(
                productName
        );

        waitForSeconds(2);

        productPage.openCart();

        waitForSeconds(2);

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
                productName,
                "Product was not added to cart"
        );

        System.out.println(
                "Product successfully added to cart."
        );
    }

    // =========================================================
    // TC-F-002
    // =========================================================

    private void testRemoveProduct(String tcId)
            throws InterruptedException {

        String productName =
                getProductName(tcId);

        productPage.addProductToCart(
                productName
        );

        waitForSeconds(2);

        productPage.openCart();

        waitForSeconds(2);

        cartPage.removeBackpack();

        waitForSeconds(2);

        boolean productPresent =
                !getDriver()
                        .findElements(
                                By.className(
                                        "inventory_item_name"
                                )
                        )
                        .isEmpty();

        Assert.assertFalse(
                productPresent,
                "Product was not removed from cart"
        );

        System.out.println(
                "Product successfully removed from cart."
        );
    }

    // =========================================================
    // TC-I-001
    // =========================================================

    private void testCartOpensCheckout(
            String tcId)
            throws InterruptedException {

        addProductAndOpenCart(tcId);

        cartPage.clickCheckout();

        waitForSeconds(2);

        WebDriverWait wait =
                new WebDriverWait(
                        getDriver(),
                        Duration.ofSeconds(10)
                );

        String title =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        By.className("title")
                                )
                ).getText();

        Assert.assertEquals(
                title,
                "Checkout: Your Information",
                "Checkout Information page was not displayed"
        );

        System.out.println(
                "Checkout Information page displayed successfully."
        );
    }

    // =========================================================
    // TC-I-002
    // =========================================================

    private void testCheckoutCompletesOrder(
            String tcId)
            throws InterruptedException {

        addProductAndOpenCheckout(tcId);

        enterCustomerDetails(tcId);

        checkoutPage1.clickContinue();

        waitForSeconds(2);

        verifyCheckoutOverview();

        checkoutPage2.clickFinish();

        waitForSeconds(2);

        String confirmation =
                checkoutPage3.getOrderConfirmation();

        Assert.assertEquals(
                confirmation,
                "Thank you for your order!",
                "Order confirmation was not displayed"
        );

        System.out.println(
                "Order completed successfully."
        );
    }

    // =========================================================
    // TC-S-001
    // =========================================================

    private void testCartToCheckoutFlow(
            String tcId)
            throws InterruptedException {

        addProductAndOpenCheckout(tcId);

        enterCustomerDetails(tcId);

        checkoutPage1.clickContinue();

        waitForSeconds(2);

        verifyCheckoutOverview();

        System.out.println(
                "Cart to Checkout flow completed successfully."
        );
    }

    // =========================================================
    // TC-S-002
    // =========================================================

    private void testCompletePurchaseFlow(
            String tcId)
            throws InterruptedException {

        addProductAndOpenCheckout(tcId);

        enterCustomerDetails(tcId);

        checkoutPage1.clickContinue();

        waitForSeconds(2);

        verifyCheckoutOverview();

        checkoutPage2.clickFinish();

        waitForSeconds(2);

        String confirmation =
                checkoutPage3.getOrderConfirmation();

        Assert.assertEquals(
                confirmation,
                "Thank you for your order!",
                "Order confirmation page was not displayed"
        );

        System.out.println(
                "Complete purchase flow successful."
        );
    }

    // =========================================================
    // TC-SM-001
    // =========================================================

    private void testAddToCartSmoke(
            String tcId)
            throws InterruptedException {

        String productName =
                getProductName(tcId);

        productPage.addProductToCart(
                productName
        );

        waitForSeconds(2);

        productPage.openCart();

        waitForSeconds(2);

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
                productName,
                "Add to Cart smoke test failed"
        );

        System.out.println(
                "Add to Cart smoke test passed."
        );
    }

    // =========================================================
    // TC-SM-002
    // =========================================================

    private void testCheckoutButtonSmoke(
            String tcId)
            throws InterruptedException {

        addProductAndOpenCart(tcId);

        cartPage.clickCheckout();

        waitForSeconds(2);

        WebDriverWait wait =
                new WebDriverWait(
                        getDriver(),
                        Duration.ofSeconds(10)
                );

        String title =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        By.className("title")
                                )
                ).getText();

        Assert.assertEquals(
                title,
                "Checkout: Your Information",
                "Checkout button smoke test failed"
        );

        System.out.println(
                "Checkout button smoke test passed."
        );
    }

    // =========================================================
    // TC-N-001
    // =========================================================

    private void testBlankFirstName(
            String tcId)
            throws InterruptedException {

        addProductAndOpenCheckout(tcId);

        String lastName =
                TestDataUtility.getData(
                        getTestDataId(tcId),
                        5
                );

        String postalCode =
                TestDataUtility.getData(
                        getTestDataId(tcId),
                        6
                );

        checkoutPage1.enterCheckoutDetails(
                "",
                lastName,
                postalCode
        );

        checkoutPage1.clickContinue();

        waitForSeconds(2);

        WebDriverWait wait =
                new WebDriverWait(
                        getDriver(),
                        Duration.ofSeconds(10)
                );

        String errorMessage =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        By.cssSelector(
                                                "[data-test='error']"
                                        )
                                )
                ).getText();

        System.out.println(
                "Error message: " + errorMessage
        );

        Assert.assertTrue(
                errorMessage
                        .toLowerCase()
                        .contains("first name"),
                "First Name validation error was not displayed"
        );

        System.out.println(
                "Blank First Name validation passed."
        );
    }

    // =========================================================
    // TC-N-002
    // =========================================================

    private void testMissingCheckoutDetails(
            String tcId)
            throws InterruptedException {

        addProductAndOpenCheckout(tcId);

        checkoutPage1.enterCheckoutDetails(
                "",
                "",
                ""
        );

        checkoutPage1.clickContinue();

        waitForSeconds(2);

        WebDriverWait wait =
                new WebDriverWait(
                        getDriver(),
                        Duration.ofSeconds(10)
                );

        String errorMessage =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        By.cssSelector(
                                                "[data-test='error']"
                                        )
                                )
                ).getText();

        System.out.println(
                "Error message: " + errorMessage
        );

        Assert.assertTrue(
                errorMessage
                        .toLowerCase()
                        .contains("first name"),
                "Validation error was not displayed"
        );

        System.out.println(
                "Missing details validation passed."
        );
    }

    // =========================================================
    // ADD PRODUCT AND OPEN CART
    // =========================================================

    private void addProductAndOpenCart(
            String tcId)
            throws InterruptedException {

        String productName =
                getProductName(tcId);

        productPage.addProductToCart(
                productName
        );

        waitForSeconds(2);

        productPage.openCart();

        waitForSeconds(2);
    }

    // =========================================================
    // ADD PRODUCT AND OPEN CHECKOUT
    // =========================================================

    private void addProductAndOpenCheckout(
            String tcId)
            throws InterruptedException {

        addProductAndOpenCart(tcId);

        cartPage.clickCheckout();

        waitForSeconds(2);
    }

    // =========================================================
    // ENTER CUSTOMER DETAILS
    // =========================================================

    private void enterCustomerDetails(
            String tcId)
            throws InterruptedException {

        String firstName =
                TestDataUtility.getData(
                        getTestDataId(tcId),
                        4
                );

        String lastName =
                TestDataUtility.getData(
                        getTestDataId(tcId),
                        5
                );

        String postalCode =
                TestDataUtility.getData(
                        getTestDataId(tcId),
                        6
                );

        checkoutPage1.enterCheckoutDetails(
                firstName,
                lastName,
                postalCode
        );

        waitForSeconds(2);
    }

    // =========================================================
    // VERIFY CHECKOUT OVERVIEW
    // =========================================================

    private void verifyCheckoutOverview()
            throws InterruptedException {

        WebDriverWait wait =
                new WebDriverWait(
                        getDriver(),
                        Duration.ofSeconds(10)
                );

        String title =
                wait.until(
                        ExpectedConditions
                                .visibilityOfElementLocated(
                                        By.className("title")
                                )
                ).getText();

        Assert.assertEquals(
                title,
                "Checkout: Overview",
                "Checkout Overview page was not displayed"
        );
    }

    // =========================================================
    // GET PRODUCT NAME FROM TEST DATA
    // =========================================================

    private String getProductName(
            String tcId) {

        return TestDataUtility.getData(
                getTestDataId(tcId),
                3
        );
    }

    // =========================================================
    // MAP TEST CASE ID TO TEST DATA ID
    // =========================================================

    private String getTestDataId(
            String tcId) {

        switch (tcId) {

        case "TC-F-001":
            return "TC001";

        case "TC-F-002":
            return "TC002";

        case "TC-I-001":
            return "TC003";

        case "TC-I-002":
            return "TC003";

        case "TC-S-001":
            return "TC003";

        case "TC-S-002":
            return "TC003";

        case "TC-SM-001":
            return "TC001";

        case "TC-SM-002":
            return "TC003";

        case "TC-N-001":
            return "TC004";

        case "TC-N-002":
            return "TC005";

        default:
            throw new RuntimeException(
                    "No test data mapping found for: "
                            + tcId
            );
        }
    }
}