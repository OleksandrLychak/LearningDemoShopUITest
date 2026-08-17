package com.qalight.demoshop.tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.qalight.demoshop.config.ConfigReader;
import com.qalight.demoshop.pages.HomePage;
import com.qalight.demoshop.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void userCanLoginWithValidCredentials() {
        String email = ConfigReader.getTestUserEmail();
        String password = ConfigReader.getTestUserPassword();

        HomePage homePage = new HomePage(getPage())
                .open()
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

    @Test
    public void userCannotLoginWithInvalidCredentials() {
        String email = "definitely-not-registered@nowhere.test";
        String password = "wrong-password-123";

        LoginPage loginPage = new HomePage(getPage())
                .open()
                .openLoginPage()
                .fillEmail(email)
                .fillPassword(password)
                .submitAndExpectFailure();

        PlaywrightAssertions.assertThat(getPage())
                .hasURL(java.util.regex.Pattern.compile(".*/login.*"));

        Assert.assertTrue(
                loginPage.isErrorVisible(),
                "Validation summary error block must be visible after failed login"
        );
        Assert.assertTrue(
                loginPage.getErrorText().contains("Login was unsuccessful"),
                "Error message should mention that login was unsuccessful"
        );
    }
}