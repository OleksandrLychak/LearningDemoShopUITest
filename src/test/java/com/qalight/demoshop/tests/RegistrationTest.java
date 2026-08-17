package com.qalight.demoshop.tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.qalight.demoshop.helpers.TestData;
import com.qalight.demoshop.models.User;
import com.qalight.demoshop.pages.RegisterPage;
import com.qalight.demoshop.utils.UrlPatterns;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void newUserCanRegisterWithUniqueEmail() {
        User user = TestData.generateUser();

        RegisterPage registerPage = openHomePage().openRegisterPage();
        registerPage.register(user);

        PlaywrightAssertions.assertThat(getPage())
                .hasURL(UrlPatterns.REGISTER_RESULT_PAGE);
    }
}