package tests;

import helpers.PageObjectUtils;
import helpers.ParametersProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.EditGradePage;
import pages.EstimatesPage;
import pages.LoginPage;
import pages.NavigationBar;
import pages.NewGradePage;

public class PhaseTaskManagementTests extends BaseTest {

    /**
     * Login in system as Administrator, create new client and create new phase.
     */
    @BeforeMethod
    public final void loginAndCreateClient() {
        String adminLogin = ParametersProvider.getProperty("adminLogin");
        String adminPassword = ParametersProvider.getProperty("adminPassword");
        String clientName = ParametersProvider.getProperty("clientName");
        String projectName = ParametersProvider.getProperty("projectName");
        String description = ParametersProvider.getProperty("description");
        String expert = ParametersProvider.getProperty("expert");
        String crmLink = ParametersProvider.getProperty("crmLink");
        new LoginPage(getDriver())
                .login(adminLogin, adminPassword);
        new EstimatesPage(getDriver())
                .createClient();
        new NewGradePage(getDriver())
                .createAndSaveClient(
                        clientName,
                        projectName,
                        description,
                        expert,
                        crmLink);
        new NavigationBar(getDriver())
                .openEstimatesFromNavBar();
        new EstimatesPage(getDriver())
                .openClientProject(clientName);
        new EditGradePage(getDriver())
                .openAddPhaseWindow()
                .clickMobilePhase()
                .savePhase();
    }

    /**
     * Add commentary to task.
     */
    @Epic(value = "Управление задачами фаз")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "EST-15: Добавление описания задачи")
    public final void addCommentaryToTask() {
        new EditGradePage(getDriver())
                .createNewTask()
                .clickCommentaryButton()
                .inputCommentary(ParametersProvider.getProperty("commentary"))
                .clickCommentaryButton()
                .clickCommentaryButton();
    }

    /**
     * Add custom task.
     */
    @Epic(value = "Управление задачами фаз")
    @Feature(value = "Добавление задачи в фазу")
    @Severity(SeverityLevel.NORMAL)
    @Test(description =
            "EST-16: Добавление задачи в фазу вручную не из справочника")
    public final void addCustomTask() {
        String customTask = ParametersProvider.getProperty("customTask");
        new EditGradePage(getDriver())
                .addNewTaskOrFeature()
                .addNewTask()
                .inputCustomTask(customTask)
                .saveTask();

        Assert.assertTrue(PageObjectUtils
                .checkPageContainText(getDriver(), customTask));
    }

    /**
     * Add task from directory.
     */
    @Epic(value = "Управление задачами фаз")
    @Feature(value = "Добавление задачи в фазу")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "EST-17: Добавление задачи в фазу из справочника")
    public final void addTaskInPhaseFromDirectory() {
        new EditGradePage(getDriver())
                .addNewTaskOrFeature()
                .addNewTask()
                .addTask()
                .saveTask();

    }

    /**
     * Add hours in field From.
     */
    @Epic(value = "Управление задачами фаз")
    @Test(description = "EST-18:Добавление часов в поле 'ОТ'")
    @Severity(SeverityLevel.NORMAL)
    public final void addHoursInFieldFrom() {
        String hours = ParametersProvider.getProperty("hoursFrom");
        new EditGradePage(getDriver())
                .createNewTask()
                .inputFieldFrom(hours);

        Assert.assertTrue(PageObjectUtils
                .checkPageContainText(getDriver(), hours));
    }

    /**
     * Add hours in field To.
     */
    @Epic(value = "Управление задачами фаз")
    @Test(description = "EST-19:Добавление часов в поле 'ДО'")
    @Severity(SeverityLevel.NORMAL)
    public final void addHoursInFieldTo() {
        String hours = ParametersProvider.getProperty("hoursTo");
        new EditGradePage(getDriver())
                .createNewTask()
                .inputFieldTo(hours);

        Assert.assertTrue(PageObjectUtils
                .checkPageContainText(getDriver(), hours));
    }

    /**
     * Delete phase.
     */
    @Epic(value = "Управление фазами")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "EST-20:Удаление фазы")
    public final void deletePhase() {
        new EditGradePage(getDriver())
                .createNewTask()
                .editPhase()
                .deletePhase()
                .confirmDelete();
    }

    /**
     * Clear data after tests.
     */
    @AfterMethod
    public final void clearData() {
        String clientName = ParametersProvider.getProperty("clientName");
        new NavigationBar(getDriver())
                .openEstimatesFromNavBar();
        new EstimatesPage(getDriver())
                .clientDelete(clientName);
        new NavigationBar(getDriver())
                .logout();
    }
}
