package tests;

import helpers.DriverFactory;
import helpers.ParametersProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import java.io.IOException;


public class BaseTest {

    /**
     * WebDriver.
     */
    private WebDriver driver;

    /**
     * SetUp WebDriver.
     * Change .getProperty("webUrl")
     * on.getProperty("remoteWebUrl") when using selenoid
     * @throws IOException when config file not available
     */
    @BeforeClass
    public final void setEnvironment() throws IOException {
        this.driver = DriverFactory.createDriver();
        String webUrl = ParametersProvider.getProperty("webUrl");
        driver.get(webUrl);
    }

    /**
     * WebDriver getter.
     * @return driver
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Suite tear down.
     */
    @AfterClass
    public final void tearDown() {
        driver.quit();
    }
}
