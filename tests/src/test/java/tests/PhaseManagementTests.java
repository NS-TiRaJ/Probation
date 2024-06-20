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


public class PhaseManagementTests extends BaseTest {

    /**
     * Login and create new client.
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
    }

    /**
     * Add phase from directory.
     */
    @Epic(value = "Управление фазами")
    @Feature(value = "Управление фазами")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "EST-7: Добавление фазы из справочника")
    public final void addPhaseFromDirectory() {
        String clientName =
                ParametersProvider.getProperty("clientName");
        String phaseFromDirectory =
                ParametersProvider.getProperty("directoryPhase");
        new EstimatesPage(getDriver())
                .openClientProject(clientName);
        new EditGradePage(getDriver())
                .openAddPhaseWindow()
                .clickMobilePhase()
                .savePhase();

        Assert.assertTrue(PageObjectUtils
                .checkPageContainText(getDriver(), phaseFromDirectory));
    }

    /**
     * Add custom phase.
     */
    @Epic(value = "Управление фазами")
    @Feature(value = "Управление фазами")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "EST-8: Добавление кастомной фазы не из справочника")
    public final void addCustomPhase() {
        String clientName = ParametersProvider.getProperty("clientName");
        String customPhase = ParametersProvider.getProperty("customPhase");
        new EstimatesPage(getDriver())
                .openClientProject(clientName);
        new EditGradePage(getDriver())
                .openAddPhaseWindow()
                .inputCustomPhase(customPhase)
                .savePhase();

        Assert.assertTrue(PageObjectUtils
                .checkPageContainText(getDriver(), customPhase));
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
