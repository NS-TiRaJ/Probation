package pages;

import helpers.PageObjectUtils;
import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Authorization page object.
 */
public class LoginPage {

    /**
     * Browser driver.
     */
    private WebDriver driver;

    /**
     * Page url.
     */
    private static final String PAGE_URL = "/login";

    /**
     * Method that get login page url.
     *
     * @return PAGE_URL return login page url
     */
    public static String getUrl() {
        return PAGE_URL;
    }

    /**
     * Login input element.
     */
    @FindBy(css = "input[ng-model='vm.login']")
    private WebElement loginInput;

    /**
     * Password input element.
     */
    @FindBy(css = "input[ng-model='vm.password']")
    private WebElement passwordInput;

    /**
     * Login submit button.
     */
    @FindBy(css = "button[ng-click='vm.log()']")
    private WebElement loginButton;

    /**
     * Locator for pressed login button.
     */
    @FindBy(css = "div[class='md-ripple-container")
    private static WebElement loginButtonIsPressed;

    /**
     * Locator for not empty login button.
     */
    @FindBy(css = "md-input-container[class='md-input-has-value']")
    private static WebElement loginInputContainsValue;

    /**
     * Locator for Error message.
     */
    @FindBy(css = "div[class='error-msg ng-binding']")
    private static WebElement errorMsg;

    /**
     * Getter for Web element loginButtonIsPressed.
     *
     * @return loginButtonIsPressed
     */
    public static WebElement getLoginButtonIsPressed() {
        return loginButtonIsPressed;
    }

    /**
     * Getter for Web element loginInputContainsValue.
     *
     * @return loginInputContainsValue
     */
    public static WebElement getLoginInputContainsValue() {
        return loginInputContainsValue;
    }

    /**
     * Getter for Web element errorMsg.
     *
     * @return errorMsg
     */
    public static WebElement getErrorMsg() {
        return errorMsg;
    }

    /**
     * Page object constructor. Checks that page is open when created.
     *
     * @param webDriver browser driver
     * @throws IllegalStateException if page is not open now
     */
    public LoginPage(final WebDriver webDriver) throws IllegalStateException {
        this.driver = webDriver;
        PageObjectUtils.waitPageLoad(driver, PAGE_URL);
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Error msg element.
     *
     * @return this
     */
    public LoginPage waitErrorMsg() {
        Waiters.waitUntilElementIsVisible(driver, errorMsg);
        return this;
    }

    /**
     * Authorization method.
     *
     * @param login    login to input
     * @param password password to input
     * @return page object with estimates list
     */
    @Step("Авторизация на сайте")
    public final EstimatesPage login(final String login,
                                     final String password) {
        Waiters.waitUntilAngularReady(driver);
        sendLogin(login);
        sendPassword(password);
        loginButtonClick();
        return new EstimatesPage(driver);
    }

    /**
     * Send login method.
     *
     * @param login login to input
     * @return this
     */
    @Step("Ввод логина")
    public final LoginPage sendLogin(final String login) {
        Waiters.waitUntilElementIsVisible(driver, loginInput);
        loginInput.sendKeys(login);
        return this;
    }

    /**
     * Clear login field.
     *
     * @return this
     */
    @Step("Отчистка поле логина")
    public final LoginPage clearLoginInput() {
        loginInput.clear();
        return this;
    }

    /**
     * Send password method.
     *
     * @param password password to input
     * @return this
     */
    @Step("Ввод пароля")
    public final LoginPage sendPassword(final String password) {
        Waiters.waitUntilElementIsVisible(driver, passwordInput);
        passwordInput.sendKeys(password);
        return this;
    }

    /**
     * Clear password field.
     *
     * @return this
     */
    @Step("Отчистка поле логина")
    public final LoginPage clearPasswordInput() {
        passwordInput.clear();
        return this;
    }

    /**
     * Login button click.
     *
     * @return this
     */
    @Step("Нажатие на кнопку входа")
    public final LoginPage loginButtonClick() {
        Waiters.waitUntilElementIsVisible(driver, loginButton);
        Waiters.waitUntilElementIsClickable(driver, loginButton);
        loginButton.click();
        return this;
    }
}
