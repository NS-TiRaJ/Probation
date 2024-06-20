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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.EstimatesPage;
import pages.LoginPage;
import pages.NavigationBar;


/**
 * Test suite for authorization page.
 */
public class LoginTests extends BaseTest {

    /**
     * Data for EST-1.
     *
     * @return Login + pass object
     */
    @DataProvider
    public Object[][] autData() {
        return new Object[][]{
                {ParametersProvider.getProperty("adminLogin"),
                        ParametersProvider.getProperty("adminPassword")},
                {ParametersProvider.getProperty("moderator"),
                        ParametersProvider.getProperty("moderatorPassword")},
                {ParametersProvider.getProperty("estimator"),
                        ParametersProvider.getProperty("estimatorPassword")},
        };
    }

    /**
     * Before method for parallel tread.
     */
    @BeforeMethod
    @Parameters({"test-name"})
    public final void openLoginPage() {
        new LoginPage(getDriver());
    }

    /**
     * Test case EST-1.
     *
     * @param login Login data
     * @param pass  Password data
     */
    @Epic(value = "Страница авторизации")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "EST-1:Авторизация с корректным логином и паролем",
            dataProvider = "autData")
    public final void checkCorrectLoginPassAuth(final String login,
                                                final String pass) {
        new LoginPage(getDriver())
                .sendLogin(login)
                .sendPassword(pass)
                .loginButtonClick();

        Assert.assertTrue(PageObjectUtils.checkPageIsPresentByUrl(getDriver(),
                        EstimatesPage.getUrl()),
                "Открыта страница " + EstimatesPage.getUrl());

        new NavigationBar(getDriver())
                .logout();
    }

    /**
     * Test case EST-2.
     */
    @Epic(value = "Страница авторизации")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "EST-2:Авторизация с несуществующим логином и паролем")
    public final void checkIncorrectLoginPassAuth() {
        String incorrectLogin =
                ParametersProvider.getProperty("incorrectLogin");
        String incorrectPassword =
                ParametersProvider.getProperty("incorrectPassword");
        new LoginPage(getDriver())
                .sendLogin(incorrectLogin)
                .sendPassword(incorrectPassword)
                .loginButtonClick()
                .waitErrorMsg();

        Assert.assertTrue(PageObjectUtils.checkPageContainElement(getDriver(),
                        LoginPage.getErrorMsg()),
                "Ошибка 'User not found'");
    }

    /**
     * Test case EST-3.
     */
    @Epic(value = "Страница авторизации")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description =
            "EST-3:Авторизация с корректным логином и пустым паролем")
    public final void checkCorrectLoginEmptyPassAuth() {
        String adminLogin = ParametersProvider.getProperty("adminLogin");
        new LoginPage(getDriver())
                .sendLogin(adminLogin)
                .sendPassword("")
                .loginButtonClick();
    }

    /**
     * Test case EST-4.
     */
    @Epic(value = "Страница авторизации")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "EST-4:Авторизация с пустыми логином и паролем")
    public final void checkEmptyLoginPassAuth() {
        new LoginPage(getDriver())
                .loginButtonClick();

        Assert.assertTrue(PageObjectUtils.checkPageContainElement(getDriver(),
                        LoginPage.getLoginButtonIsPressed()),
                "Кнопка Login не нажата");
    }

    /**
     * Clear all input field on login page.
     */
    @AfterMethod
    public final void clearFields() {
        new LoginPage(getDriver())
                .clearLoginInput()
                .clearPasswordInput();
    }
}
