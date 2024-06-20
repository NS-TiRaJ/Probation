package helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;

public class TestListener implements ITestListener {

    /**
     * Listener for failed with timeout test's.
     * @param result test result
     */
    @Override
    public void onTestFailedWithTimeout(final ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        saveAllureScreenshot(driver);
    }

    /**
     * Listener for Success test's.
     * @param result test result
     */
    @Override
    public void onTestSuccess(final ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        saveAllureScreenshot(driver);
    }

    /**
     * Listener for fail tests.
     * @param result test result
     */
    @Override
    public void onTestFailure(final ITestResult result) {
        ITestListener.super.onTestFailure(result);
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        saveAllureScreenshot(driver);
    }

    /**
     * Listener for skipped tests.
     * @param result test result
     */
    @Override
    public void onTestSkipped(final ITestResult result) {
        ITestListener.super.onTestSkipped(result);
        WebDriver driver = ((BaseTest) result.getInstance()).getDriver();
        saveAllureScreenshot(driver);
    }

    /**
     * Save screenshot in allure-report.
     * @return screenshot
     * @param webDriver
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveAllureScreenshot(final WebDriver webDriver) {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }
}
