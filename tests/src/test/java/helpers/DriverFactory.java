package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Browser driver factory.
 */
public final class DriverFactory {
    /**
     * Not called.
     */
    private DriverFactory() {
    }

    /**
     * Sets driver path if it possible.
     * @param browserName browser name
     */
    private static void trySetDriverPath(final String browserName) {
        String driverPath = ParametersProvider.getProperty("driverPath");
        if (!driverPath.isEmpty()) {
            System.setProperty("webdriver." + browserName
                            + ".driver", driverPath);
        }
    }

    /**
     *  Get browser name.
     * @param browserName
     * @return browser
     */
    private static WebDriver getDriver(final String browserName) {
        switch (browserName) {
            case "chrome": case "opera":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            case "edge":
                return new EdgeDriver();
            default:
                throw new IllegalStateException("Chosen browser not supported");
        }
    }

    /**
     * Creates a browser driver using configuration.
     * @return browser driver
     * @throws IOException when config file is not available
     * @throws IllegalStateException when unsupported browser chosen
     */
    public static WebDriver createDriver() throws IOException,
            IllegalStateException {
        WebDriver driver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String browserName = ParametersProvider.getProperty("browserName");
        String browserVersion = ParametersProvider
                .getProperty("browserVersion");
        boolean remote = Boolean.parseBoolean(ParametersProvider
                .getProperty("remote"));
        int pageLoadTimeout = Integer.parseInt(ParametersProvider
                .getProperty("pageLoadTimeout"));
        if (remote) {
            capabilities.setBrowserName(browserName);
            capabilities.setVersion(browserVersion);
            capabilities.setCapability("enableVNC", true);
            driver = new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"), capabilities);
        } else {
            trySetDriverPath(browserName);
            driver = getDriver(browserName);
        }
        driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout,
                TimeUnit.SECONDS);
        return driver;
    }
}
