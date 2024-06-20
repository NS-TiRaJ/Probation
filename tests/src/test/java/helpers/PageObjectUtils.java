package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Helper class for page objects.
 */
public final class PageObjectUtils {
    /**
     * Not called.
     */
    private PageObjectUtils() {
    }

    /**
     * Wait page is open by its Url.
     *
     * @param driver  browser driver
     * @param pageUrl page URL
     */
    public static void waitPageLoad(final WebDriver driver,
                                    final String pageUrl) {
        int timeout = Integer.parseInt(ParametersProvider
                .getProperty("explicitTimeout"));
        Waiters.waitUntilAngularReady(driver, timeout);
        new WebDriverWait(driver, timeout)
                .until(ExpectedConditions.urlContains(pageUrl));
    }

    /**
     * Checks that page is open by its Url.
     *
     * @param driver  browser driver
     * @param pageUrl page URL
     * @return is page open
     */
    public static boolean checkPageIsPresentByUrl(final WebDriver driver,
                                                  final String pageUrl) {
        try {
            waitPageLoad(driver, pageUrl);
        } catch (TimeoutException e) {
            System.out.println("Страница (" + pageUrl + ") не загрузилась");
            return false;
        }
        return true;
    }

    /**
     * Checks that page contain WebElement.
     *
     * @param driver  browser driver
     * @param element WebElement
     * @return is page contain element
     */
    public static boolean checkPageContainElement(final WebDriver driver,
                                                  final WebElement element) {
        try {
            element.isEnabled();
        } catch (NoSuchElementException e) {
            System.out.println("Страница не содержит элемента: " + element);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Checks that element contains text.
     *
     * @param driver WebDriver
     * @param text   The text we are looking for
     * @return is page contains text
     */
    public static boolean checkPageContainText(final WebDriver driver,
                                               final String text) {
        try {
            driver.findElement(By.xpath("//*[contains(text(), '"
                    + text + "')]"));
        } catch (NoSuchElementException e) {
            System.out.println("На странице нету текста: " + text);
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
