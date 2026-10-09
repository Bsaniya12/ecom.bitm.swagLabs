package swaglabs.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import swaglabs.baseClass.BaseClass;
import swaglabs.objrepo.LoginPage;
import swaglabs.objrepo.ProductPage;
import swaglabs.utility.TestDataUtility;

public class Logout_Test extends BaseClass {

	@Test(priority = 1, groups = {"FT", "SM"})
    public void logoutTest() throws InterruptedException {

        String username = TestDataUtility.getData("TC001", 1);      
        String password = TestDataUtility.getData("TC001", 2);

        LoginPage loginPage =new LoginPage(getDriver());
        loginPage.login(username, password);

        waitForSeconds(2);

        ProductPage productPage =new ProductPage(getDriver());       
        productPage.logout();

        waitForSeconds(2);

        String actualUrl =getDriver().getCurrentUrl();
                
        Assert.assertTrue( actualUrl.contains("saucedemo.com"), "Logout was not successful");
               
     Reporter.log( "Logout Test Passed Successfully",true );
    }
}