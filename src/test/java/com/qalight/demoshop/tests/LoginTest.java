package com.qalight.demoshop.tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.qalight.demoshop.config.ConfigReader;
import com.qalight.demoshop.pages.HomePage;
import com.qalight.demoshop.pages.LoginPage;
import com.qalight.demoshop.utils.UrlPatterns;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentialsProvider() {
        return new Object[][]{
                {"definitely-not-registered@nowhere.test", "wrong-password-123", "unknown email + wrong password"},
                {"", "some-password", "empty email"},
                {"nobody@example.com", "", "empty password"},
                {"not-an-email", "any-password", "invalid email format"}
        };
    }

    @Test
    public void userCanLoginWithValidCredentials() {
        String email = ConfigReader.getTestUserEmail();
        String password = ConfigReader.getTestUserPassword();

        HomePage homePage = openHomePage()
                .openLoginPage()
                .login(email, password);

        Assert.assertTrue(
                homePage.isUserLoggedIn(),
                "After successful login the account link must be visible on Home"
        );
        Assert.assertEquals(
                homePage.getLoggedInUserEmail(),
                email,
                "Account link should display the email of the logged-in user"
        );
    }

    @Test(dataProvider = "invalidCredentials")
    public void userCannotLoginWithInvalidCredentials(String email, String password, String scenario) {
        LoginPage loginPage = openHomePage()
                .openLoginPage()
                .fillEmail(email)
                .fillPassword(password)
                .submitAndExpectFailure();

        PlaywrightAssertions.assertThat(getPage())
                .hasURL(UrlPatterns.LOGIN_PAGE);

        Assert.assertTrue(
                loginPage.isErrorVisible(),
                "Error block must be visible for scenario: " + scenario
        );
    }
}