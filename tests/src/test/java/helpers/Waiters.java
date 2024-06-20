package helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Helper class for custom explicit waits.
 */
public final class Waiters {
    /**
     * Not called.
     */
    private Waiters() {
    }

    /**
     * Wait for Angular pending requests to finish.
     *
     * @param webDriver browser driver
     * @param timeout   wait threshold
     */
    public static void waitUntilAngularReady(final WebDriver webDriver,
                                             final int timeout) {
        final String angularReady =
                "return angular.element(document).injector()"
                        + ".get('$http').pendingRequests.length === 0";
        ExpectedCondition<Boolean> angularLoad = driver ->
                Boolean.valueOf(((JavascriptExecutor) driver)
                        .executeScript(angularReady).toString());
        new WebDriverWait(webDriver, timeout).until(angularLoad);
    }

    /**
     * Wait for Angular pending requests to finish.
     *
     * @param webDriver browser driver
     */
    public static void waitUntilAngularReady(final WebDriver webDriver) {
        int explicitTimeout = Integer.parseInt(ParametersProvider
                .getProperty("explicitTimeout"));
        waitUntilAngularReady(webDriver, explicitTimeout);
    }

    /**
     * Wait until WebElement is visible.
     *
     * @param webDriver WebDriver
     * @param element the loading WebElement we are waiting for
     * @throws NoSuchElementException when dont find element on page
     */
    public static void waitUntilElementIsVisible(final WebDriver webDriver,
                                                 final WebElement element) {
        try {
            new WebDriverWait(webDriver, Long.parseLong(ParametersProvider
                    .getProperty("explicitTimeout")))
                    .until(ExpectedConditions.visibilityOf(element))
                    .isEnabled();
        } catch (NoSuchElementException e) {
            System.out.println("Элемент" + element + "не найден");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Wait until WebElement is clickable.
     *
     * @param webDriver WebDriver
     * @param element the WebElement we are waiting to be clickable
     * @throws NoSuchElementException when don't find element on page
     */
    public static void waitUntilElementIsClickable(final WebDriver webDriver,
                                                   final  WebElement element) {
        try {
            new WebDriverWait(webDriver, Long.parseLong(ParametersProvider
                    .getProperty("explicitTimeout")))
                    .until(ExpectedConditions
                            .elementToBeClickable(element));
        } catch (NoSuchElementException e) {
            System.out.println("Элемент" + element + "не найден");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
