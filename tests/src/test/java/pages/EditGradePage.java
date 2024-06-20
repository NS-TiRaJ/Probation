package pages;

import helpers.PageObjectUtils;
import helpers.Waiters;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditGradePage {

    /**
     * Browser driver.
     */
    private WebDriver driver;

    /**
     * Edit page url.
     */
    private static final String PAGE_URL = "/estimates";

    /**
     * Getter for page url.
     *
     * @return page url.
     */
    public static String getUrl() {
        return PAGE_URL;
    }

    /**
     * Phase button element.
     */
    @FindBy(css = "button[id='add-phase-button']")
    private WebElement addPhaseButton;

    /**
     * Client info table element.
     */
    @FindBy(css = "md-content[ng-show='!vm.preloader']")
    private WebElement clientInfoTable;

    /**
     * Phase window element.
     */
    @FindBy(css = "md-dialog[class='phaseModal md-transition-in']")
    private WebElement addPhaseWindow;

    /**
     * Mobile phase element.
     */
    @FindBy(css = "div[class='md-container md-ink-ripple']")
    private WebElement mobilePhaseFromDirectory;

    /**
     * Custom phase input element.
     */
    @FindBy(css = "input[id='save-adding-phases-button']")
    private WebElement customPhaseInput;

    /**
     * Save phase button element.
     */
    @FindBy(css = "button[class='md-primary md-button md-ink-ripple']")
    private WebElement savePhaseButton;

    /**
     * New task or feature element.
     */
    @FindBy(css = "button[ng-click=\"vm.openFab($event)\"]")
    private WebElement addNewTaskOrFeatureButton;

    /**
     * New task element.
     */
    @FindBy(css = "div[ng-click='vm.addNewTask($event)']")
    private WebElement addNewTaskButton;

    /**
     * Phase window element.
     */
    @FindBy(css = "md-dialog[class='phaseModal md-transition-in']")
    private WebElement phaseWindow;

    /**
     * First task or feature element.
     */
    @FindBy(css = "textarea[item-name='vm.item.name']")
    private WebElement firstTaskOrFeatureInColum;

    /**
     * Task window element.
     */
    @FindBy(css = "md-dialog[role=\"dialog\"]")
    private WebElement taskWindow;
    /**
     * Custom task input element.
     */
    @FindBy(css = "textarea[id=\"task-name-textarea\"]")
    private WebElement customTaskInput;

    /**
     * First task window element.
     */
    @FindBy(css = "div[class=\"md-container md-ink-ripple\"]")
    private WebElement firstTaskInWindow;

    /**
     * Save task button element.
     */
    @FindBy(css = "button[id='save-adding-tasks-button']")
    private WebElement saveTaskButton;

    /**
     * Task in page header element.
     */
    @FindBy(css = "md-tab-item[md-tabs-template=\"::tab.label\"] "
            + "label[contenteditable=\"false\"]")
    private WebElement phaseInHeader;

    /**
     * Commentary button element.
     */
    @FindBy(css = "md-icon[ng-click='vm.toggleDescription()']")
    private WebElement commentaryButton;

    /**
     * Commentary input field element.
     */
    @FindBy(css = "textarea[id='description-textarea']")
    private WebElement commentaryInput;

    /**
     * Filed from element.
     */
    @FindBy(css = "input[wh-value='vm.item.minHours']")
    private WebElement fieldFromInput;

    /**
     * Field to element.
     */
    @FindBy(css = "input[wh-value='vm.item.maxHours']")
    private WebElement fieldToInput;

    /**
     * Edit phase button element.
     */
    @FindBy(css = "md-icon[aria-label='Edit Phase']")
    private WebElement editPhaseButton;

    /**
     * delete phase button element.
     */
    @FindBy(css = "button[ng-click='vm.deletePhase($event, phase)']")
    private WebElement deletePhaseButton;

    /**
     * Confirm Delete button element in delete window.
     */
    @FindBy(xpath = "//span[contains(text(), 'ОК')]/parent::*")
    private WebElement confirmDeletePhaseButton;

    /**
     * Page constructor.
     *
     * @param webDriver browser driver.
     * @throws IllegalStateException if page is not open now
     */
    public EditGradePage(final WebDriver webDriver) throws
            IllegalStateException {
        this.driver = webDriver;
        PageObjectUtils.waitPageLoad(driver, PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    /**
     * Create new task.
     *
     * @return this
     */
    public EditGradePage createNewTask() {
        addNewTaskOrFeature();
        addNewTask();
        addTask();
        saveTask();
        return this;
    }

    /**
     * Open add phase window.
     *
     * @return this
     */
    @Step
    public EditGradePage openAddPhaseWindow() {
        addPhaseButton.click();
        return this;
    }

    /**
     * Click on mobile phase.
     *
     * @return this
     */
    @Step
    public EditGradePage clickMobilePhase() {
        Waiters.waitUntilElementIsVisible(driver, mobilePhaseFromDirectory);
        Waiters.waitUntilElementIsClickable(driver, mobilePhaseFromDirectory);
        mobilePhaseFromDirectory.click();
        return this;
    }

    /**
     * Input phase name in custom phase field.
     *
     * @param phaseName Phase name to input
     * @return this
     */
    @Step
    public EditGradePage inputCustomPhase(final String phaseName) {
        Waiters.waitUntilElementIsVisible(driver, customPhaseInput);
        customPhaseInput.sendKeys(phaseName);
        return this;
    }

    /**
     * Click on save phase button.
     *
     * @return this
     */
    @Step
    public EditGradePage savePhase() {
        savePhaseButton.click();
        Waiters.waitUntilElementIsVisible(driver, phaseInHeader);
        return this;
    }

    /**
     * Click on + icon.
     *
     * @return this
     */
    @Step
    public EditGradePage addNewTaskOrFeature() {
        Waiters.waitUntilElementIsVisible(driver, addNewTaskOrFeatureButton);
        Waiters.waitUntilElementIsClickable(driver, addNewTaskOrFeatureButton);
        addNewTaskOrFeatureButton.click();
        return this;
    }

    /**
     * Click on add new tusk button.
     *
     * @return this
     */
    @Step
    public EditGradePage addNewTask() {
        Waiters.waitUntilElementIsVisible(driver, addNewTaskButton);
        Waiters.waitUntilElementIsClickable(driver, addNewTaskButton);
        addNewTaskButton.click();
        return this;
    }

    /**
     * Click on first task in task window.
     *
     * @return this
     */
    @Step
    public EditGradePage addTask() {
        Waiters.waitUntilElementIsVisible(driver, firstTaskInWindow);
        Waiters.waitUntilElementIsClickable(driver, firstTaskInWindow);
        firstTaskInWindow.click();
        return this;
    }

    /**
     * Input custom task filed.
     *
     * @param customTask name of custom task
     * @return this
     */
    @Step
    public EditGradePage inputCustomTask(final String customTask) {
        Waiters.waitUntilElementIsVisible(driver, customTaskInput);
        Waiters.waitUntilElementIsClickable(driver, customTaskInput);
        customTaskInput.sendKeys(customTask);
        return this;
    }

    /**
     * Click save task button.
     *
     * @return this
     */
    @Step
    public EditGradePage saveTask() {
        Waiters.waitUntilElementIsVisible(driver, saveTaskButton);
        Waiters.waitUntilElementIsClickable(driver, saveTaskButton);
        saveTaskButton.click();
        return this;
    }

    /**
     * Click on first commentary button.
     *
     * @return this
     */
    @Step
    public EditGradePage clickCommentaryButton() {
        Waiters.waitUntilElementIsVisible(driver, commentaryButton);
        Waiters.waitUntilElementIsClickable(driver, commentaryButton);
        commentaryButton.click();
        return this;
    }

    /**
     * Input commentary in commentary field.
     *
     * @param commentary commentary value
     * @return this
     */
    @Step
    public EditGradePage inputCommentary(final String commentary) {
        Waiters.waitUntilElementIsVisible(driver, commentaryInput);
        Waiters.waitUntilElementIsClickable(driver, commentaryInput);
        commentaryInput.click();
        commentaryInput.sendKeys(commentary);
        return this;
    }

    /**
     * Input hours in field From.
     *
     * @param hours hours to input
     * @return this
     */
    @Step
    public EditGradePage inputFieldFrom(final String hours) {
        Waiters.waitUntilElementIsVisible(driver, fieldFromInput);
        Waiters.waitUntilElementIsClickable(driver, fieldFromInput);
        fieldFromInput.clear();
        fieldFromInput.sendKeys(hours);
        return this;
    }

    /**
     * Input hours in field To.
     *
     * @param hours hours to input
     * @return this
     */
    @Step
    public EditGradePage inputFieldTo(final String hours) {
        Waiters.waitUntilElementIsVisible(driver, fieldToInput);
        Waiters.waitUntilElementIsClickable(driver, fieldToInput);
        fieldToInput.clear();
        fieldToInput.sendKeys(hours);
        return this;
    }

    /**
     * Click on edit phase button.
     *
     * @return this
     */
    @Step
    public EditGradePage editPhase() {
        Waiters.waitUntilElementIsVisible(driver, editPhaseButton);
        Waiters.waitUntilElementIsClickable(driver, editPhaseButton);
        editPhaseButton.click();
        return this;
    }

    /**
     * Click on delete phase button.
     *
     * @return this
     */
    @Step
    public EditGradePage deletePhase() {
        Waiters.waitUntilElementIsVisible(driver, deletePhaseButton);
        Waiters.waitUntilElementIsClickable(driver, deletePhaseButton);
        deletePhaseButton.click();
        return this;
    }

    /**
     * Click on OK button in confirm delete window.
     *
     * @return this
     */
    @Step
    public EditGradePage confirmDelete() {
        Waiters.waitUntilElementIsVisible(driver, confirmDeletePhaseButton);
        Waiters.waitUntilElementIsClickable(driver, confirmDeletePhaseButton);
        confirmDeletePhaseButton.click();
        return this;
    }
}
