package pages;

import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationBar {

    /**
     * Browser driver.
     */
    private WebDriver driver;

    /**
     * Navigation bar button.
     */
    @FindBy(css = "button[id='toggle-side-nav-button']")
    private WebElement navigationBarButton;

    /**
     * Logout button in navbar.
     */
    @FindBy(css = "a[id='logout-button'] md-icon")
    private WebElement logoutButton;

    /**
     * Estimates button in navigation bar.
     */
    @FindBy(css = "a[ui-sref='index.estimates']")
    private WebElement estimatesPageButton;

    /**
     * Navigation bar selector.
     */
    @FindBy(css = "md-sidenav[ng-init=\"vm.initSideNav('left')\"]")
    private WebElement navigationBarBody;


    /**
     * Page object constructor.
     *
     * @param webDriver browser driver
     */
    public NavigationBar(final WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Opening estimates page from navigation bar.
     *
     * @return this
     */
    public final NavigationBar openEstimatesFromNavBar() {
        openNavigationBar();
        openEstimatesPage();
        return this;
    }

    /**
     * Open navigation bar.
     * @return this
     */
    @Step("Открытие навигационной панели")
    public final NavigationBar openNavigationBar() {
        navigationBarButton.click();
        Waiters.waitUntilElementIsVisible(driver, navigationBarBody);
        return this;
    }

    /**
     * Open Estimates Page from navigation bar.
     * @return this
     */
    @Step("Переход на страницу оценок через навигационную панель")
    public final NavigationBar openEstimatesPage() {
        Waiters.waitUntilElementIsVisible(driver, estimatesPageButton);
        estimatesPageButton.click();
        return this;
    }

    /**
     * Logout from account.
     * @return this
     */
    @Step ("Выход из аккаунта")
    public final NavigationBar logout() {
        Waiters.waitUntilElementIsVisible(driver, logoutButton);
        Waiters.waitUntilElementIsClickable(driver, logoutButton);
        logoutButton.click();
        return this;
    }
}
