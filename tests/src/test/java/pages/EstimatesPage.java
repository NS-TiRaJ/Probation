package pages;

import helpers.PageObjectUtils;
import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Estimates list page object.
 */
public class EstimatesPage {

    /**
     * Browser driver.
     */
    private WebDriver driver;

    /**
     * Page Url.
     */
    static final String PAGE_URL = "/estimates";

    /**
     * Method that get estimates page url.
     *
     * @return PAGE_URL return estimates page url
     */
    public static String getUrl() {
        return PAGE_URL;
    }

    /**
     * Create new client button.
     */
    @FindBy(css = "button[ng-click='vm.openFab($event)']")
    private WebElement addNewClientButton;

    /**
     * Window with client language choose.
     */
    @FindBy(css = "div[ng-if='vm.isOpen']")
    private WebElement clientLanguageWindow;

    /**
     * Choose russian client element.
     */
    @FindBy(css = "div[ng-click=\"vm.newEstimate($event, 'ru')\"] ")
    private WebElement chooseRu;

    /**
     * Ok button in delete window.
     */
    @FindBy(xpath = "//span[contains(text(), 'ОК')]/parent::*")
    private WebElement cunfirmDeleteButton;

    /**
     * Page object constructor. Checks that page is open when created.
     *
     * @param webDriver browser driver
     * @throws IllegalStateException if page is not open now
     */
    public EstimatesPage(final WebDriver webDriver) throws
            IllegalStateException {
        this.driver = webDriver;
        PageObjectUtils.waitPageLoad(driver, PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    /**
     * Open create new client page.
     *
     * @return this
     */
    public final EstimatesPage createClient() {
        createNewClient();
        chooseRuClient();
        return this;
    }

    /**
     * Open project by client name.
     *
     * @param clientName client name
     * @return this
     */
    public final EstimatesPage openClientProject(final String clientName) {
        WebElement clientProject = driver.findElement(By.xpath(
                "//a[.//div//strong[contains(text(),'" + clientName + "')]]"));
        Waiters.waitUntilElementIsVisible(driver, clientProject);
        Waiters.waitUntilElementIsClickable(driver, clientProject);
        clientProject.click();
        return this;
    }

    /**
     * Delete client from client's table by his name.
     *
     * @param clientName Client name
     * @return this
     */
    public final EstimatesPage clientDelete(final String clientName) {
        deleteClient(clientName);
        confirmDelete();
        return this;
    }

    /**
     * Click on create new client button.
     *
     * @return this
     */
    @Step("Нажатие на кнопку +")
    public final EstimatesPage createNewClient() {
        addNewClientButton.click();
        Waiters.waitUntilElementIsVisible(driver, clientLanguageWindow);
        return this;
    }

    /**
     * Click on RU client.
     *
     * @return this
     */
    @Step("Выбор русскоязычного клиента")
    public final EstimatesPage chooseRuClient() {
        chooseRu.click();
        return this;
    }

    /**
     * Delete client by his name.
     *
     * @param clientName client name
     * @return this
     */
    @Step
    public final EstimatesPage deleteClient(final String clientName) {
        WebElement deleteButton = driver.findElement(By.xpath(
                "//a[.//div//strong[contains(text(),'"
                        + clientName + "')]]/descendant::"
                        + "button[@aria-label='Удалить оценку']"));
        deleteButton.click();
            Waiters.waitUntilElementIsVisible(driver, deleteButton);
            Waiters.waitUntilElementIsClickable(driver, cunfirmDeleteButton);
        return this;
    }

    /**
     * Press OK button in confirm delete window and wait while it closes.
     * @return this
     */
    @Step
    public final EstimatesPage confirmDelete() {
        Waiters.waitUntilElementIsClickable(driver, cunfirmDeleteButton);
        cunfirmDeleteButton.click();
        return this;
    }
}

