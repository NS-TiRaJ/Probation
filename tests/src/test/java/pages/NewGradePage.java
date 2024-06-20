package pages;

import helpers.PageObjectUtils;
import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewGradePage {

    /**
     * Browser driver.
     */
    private WebDriver driver;

    /**
     * Edit page Url.
     */
    private static final String PAGE_URL = "/edit";

    /**
     * Method that get edit page url.
     *
     * @return PAGE_URL return edit page url
     */
    public static String getUrl() {
        return PAGE_URL;
    }

    /**
     * Client name input element.
     */
    @FindBy(css = "textarea[name='customer']")
    private WebElement clientNameInput;

    /**
     * Project name input element.
     */
    @FindBy(css = "textarea[ng-model='vm.project.name']")
    private WebElement projectNameInput;

    /**
     * Experts input element.
     */
    @FindBy(xpath = "//label[contains(text(), 'Эксперты')]"
            + "/following-sibling::md-contact-chips//input")
    private WebElement expertsInput;

    /**
     * CRM input element.
     */
    @FindBy(css = "textarea[name='linkToCRM']")
    private WebElement crmLinkInput;

    /**
     * Description input element.
     */
    @FindBy(css = "textarea[name='description']")
    private WebElement descriptionInput;

    /**
     * Checkbox for QA department.
     */
    @FindBy(css = "md-checkbox[aria-label='*Направление QA']")
    private WebElement qaDepartmentCheckBox;

    /**
     * Save form button element.
     */
    @FindBy(css = "button[ng-click='vm.editAbout($event)']")
    private WebElement saveAndAddPhaseButton;

    /**
     * Phase window element.
     */
    @FindBy(css = "md-dialog[class='phaseModal md-transition-in']")
    private WebElement phaseWindow;

    /**
     * Close phase window element.
     */
    @FindBy(css = "md-icon[ng-click='vm.closeModal()']")
    private WebElement closePhaseWindowButton;


    /**
     * Page object constructor. Checks that page is open when created.
     *
     * @param webDriver browser driver
     * @throws IllegalStateException if page is not open now
     */
    public NewGradePage(final WebDriver webDriver)
            throws IllegalStateException {
        this.driver = webDriver;
        PageObjectUtils.waitPageLoad(driver, PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to create and save new client.
     *
     * @param clientName  client name
     * @param projectName project name
     * @param description project description
     * @param expertName  expert name
     * @param crmLink     link to CRM
     * @return this
     */
    public final NewGradePage createAndSaveClient(final String clientName,
                                                  final String projectName,
                                                  final String description,
                                                  final String expertName,
                                                  final String crmLink) {
        inputClientName(clientName);
        inputProjectName(projectName);
        inputCrmLink(crmLink);
        inputDescription(description);
        inputExpert(expertName);
        chooseQaDepartment();
        saveClient();
        closePhaseWindow();
        return this;
    }


    /**
     * Send client name.
     *
     * @param clientName client name to input
     * @return this
     */
    @Step("Ввод имени клиента")
    public final NewGradePage inputClientName(final String clientName) {
        Waiters.waitUntilElementIsVisible(driver, clientNameInput);
        clientNameInput.sendKeys(clientName);
        return this;
    }

    /**
     * Send project name.
     *
     * @param projectName project name to input
     * @return this
     */
    @Step("Ввод названия проекта")
    public final NewGradePage inputProjectName(final String projectName) {
        projectNameInput.sendKeys(projectName);
        return this;
    }

    /**
     * Send description.
     *
     * @param description description to input
     * @return this
     */
    @Step("Ввод описания проекта")
    public final NewGradePage inputDescription(final String description) {
        descriptionInput.sendKeys(description);
        return this;
    }

    /**
     * Send CRM link.
     *
     * @param crmLink CRM link to input
     * @return this
     */
    @Step("Ввод CRM ссылки")
    public final NewGradePage inputCrmLink(final String crmLink) {
        crmLinkInput.sendKeys(crmLink);
        return this;
    }

    /**
     * Send expert name.
     *
     * @param expertName Expert name to input
     * @return this
     */
    @Step("Ввод и выбор эксперта")
    public final NewGradePage inputExpert(final String expertName) {
        expertsInput.sendKeys(expertName + Keys.ENTER);
        return this;
    }

    /**
     * Click on QA department checkbox.
     *
     * @return this
     */
    @Step("Нажатие на чекбокс QA отдела")
    public final NewGradePage chooseQaDepartment() {
        qaDepartmentCheckBox.click();
        return this;
    }

    /**
     * Click on Save and add phase button.
     *
     * @return this
     */
    @Step("Сохранение клиента")
    public final NewGradePage saveClient() {
        Waiters.waitUntilElementIsVisible(driver, saveAndAddPhaseButton);
        Waiters.waitUntilElementIsClickable(driver, saveAndAddPhaseButton);
        saveAndAddPhaseButton.click();
        return this;
    }

    /**
     * Close phase window.
     *
     * @return this
     */
    @Step("Закрытие окна добавления фазы")
    public final NewGradePage closePhaseWindow() {
        Waiters.waitUntilElementIsVisible(driver, phaseWindow);
        Waiters.waitUntilElementIsClickable(driver, closePhaseWindowButton);
        closePhaseWindowButton.click();
        return this;
    }
}
