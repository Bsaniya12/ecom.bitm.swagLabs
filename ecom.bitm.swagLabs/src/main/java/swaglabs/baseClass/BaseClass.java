package swaglabs.baseClass;

import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static Properties properties;

    public WebDriver getDriver() {
        return driver.get();
    }

    public void loadProperties() {
        try {
            properties = new Properties();

            InputStream inputStream = getClass()
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (inputStream == null) {
                throw new RuntimeException(
                        "config.properties not found in src/main/resources");
            }

            properties.load(inputStream);
            inputStream.close();

        } catch (Exception e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        loadProperties();

        String browser = properties.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--disable-features=PasswordLeakDetection");

            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.default_content_setting_values.notifications", 2);

            options.setExperimentalOption("prefs", prefs);

            driver.set(new ChromeDriver(options));

        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        getDriver().manage().window().maximize();
        String implicitWait = properties.getProperty("implicitWait");

        getDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Long.parseLong(implicitWait)));

        getDriver().get(properties.getProperty("url"));
    }
    public void waitForSeconds(int seconds) throws InterruptedException {
        Thread.sleep(1000);
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }
}
