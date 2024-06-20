package tests;

import helpers.PageObjectUtils;
import helpers.ParametersProvider;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.EstimatesPage;
import pages.LoginPage;
import pages.NavigationBar;
import pages.NewGradePage;


public class GradeManagementTests extends BaseTest {

    /**
     * DataProvider with client data.
     *
     * @return Object with client data
     */
    @DataProvider(name = "clientData")
    public Object[][] clientData() {
        return new Object[][]{
                {
                        ParametersProvider.getProperty("clientName"),
                        ParametersProvider.getProperty("projectName"),
                        ParametersProvider.getProperty("description"),
                        ParametersProvider.getProperty("expert"),
                        ParametersProvider.getProperty("crmLink")
                },
                {
                        ParametersProvider.getProperty("clientName2"),
                        ParametersProvider.getProperty("projectName2"),
                        ParametersProvider.getProperty("description2"),
                        ParametersProvider.getProperty("expert2"),
                        ParametersProvider.getProperty("crmLink2")
                }
        };
    }

    /**
     * Login as Administrator.
     */
    @BeforeMethod
    public final void loginAsAdmin() {
        String adminLogin = ParametersProvider.getProperty("adminLogin");
        String adminPassword = ParametersProvider.getProperty("adminPassword");
        new LoginPage(getDriver())
                .login(adminLogin, adminPassword);
    }

    /**
     * Test case EST-5.
     *
     * @param clientName  client name
     * @param projectName project name
     * @param crmLink     link to CRM
     * @param description project description
     * @param expert      expert name
     */
    @Epic(value = "Управление оценками")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "EST-5:Создание оценки", dataProvider = "clientData")
    public final void createNewGrade(final String clientName,
                                     final String projectName,
                                     final String crmLink,
                                     final String description,
                                     final String expert) {
        new EstimatesPage(getDriver())
                .createNewClient()
                .chooseRuClient();
        new NewGradePage(getDriver())
                .inputClientName(clientName)
                .inputProjectName(projectName)
                .inputCrmLink(crmLink)
                .inputDescription(description)
                .inputExpert(expert)
                .chooseQaDepartment()
                .saveClient()
                .closePhaseWindow();
        new NavigationBar(getDriver())
                .openNavigationBar()
                .openEstimatesPage();

        Assert.assertTrue(PageObjectUtils.checkPageIsPresentByUrl(getDriver(),
                        EstimatesPage.getUrl()),
                "Ошибка перехода на страницу "
                        + "/estimates из навигационной панели");
        Assert.assertTrue(PageObjectUtils.checkPageContainText(getDriver(),
                        clientName),
                "Созданный клиент не найден на странице");
        new EstimatesPage(getDriver()).clientDelete(clientName);
    }

    /**
     * Test case EST-6.
     *
     * @param clientName  client name
     * @param projectName project name
     * @param description project description
     * @param expert      expert name
     * @param crmLink     link to CRM
     */
    @Epic(value = "Управление оценками")
    @Severity(SeverityLevel.NORMAL)
    @Test(description = "EST-6:Удаление оценки", dataProvider = "clientData")
    public final void deleteGrade(final String clientName,
                                  final String projectName,
                                  final String description,
                                  final String expert,
                                  final String crmLink) {
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
                .clientDelete(clientName);

        Assert.assertFalse(PageObjectUtils.checkPageContainText(getDriver(), clientName));
    }

    /**
     * Logout from account.
     */
    @AfterMethod
    public final void logout() {
        new NavigationBar(getDriver())
                .logout();
    }
}
