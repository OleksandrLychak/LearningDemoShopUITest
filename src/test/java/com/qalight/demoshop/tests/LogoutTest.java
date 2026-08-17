package com.qalight.demoshop.tests;

import com.qalight.demoshop.config.ConfigReader;
import com.qalight.demoshop.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @Test
    public void loggedInUserCanLogOut() {
        String email = ConfigReader.getTestUserEmail();
        String password = ConfigReader.getTestUserPassword();

        HomePage homePage = openHomePage()
                .openLoginPage()
                .login(email, password);

        Assert.assertTrue(
                homePage.isUserLoggedIn(),
                "Precondition failed: user should be logged in before logout"
        );

        homePage.logout();

        Assert.assertTrue(
                homePage.isLoginLinkVisible(),
                "After logout the 'Log in' link must be visible in the header"
        );
        Assert.assertFalse(
                homePage.isUserLoggedIn(),
                "After logout the account link with user's email must disappear"
        );
    }
}