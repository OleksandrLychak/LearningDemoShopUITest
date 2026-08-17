package com.qalight.demoshop.tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.qalight.demoshop.helpers.TestData;
import com.qalight.demoshop.pages.RegisterPage;
import com.qalight.demoshop.utils.UrlPatterns;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void newUserCanRegisterWithUniqueEmail() {
        String email = TestData.generateUniqueEmail();
        String password = TestData.defaultPassword();
        String firstName = TestData.generateFirstName();
        String lastName = TestData.generateLastName();

        RegisterPage registerPage = openHomePage().openRegisterPage();
        registerPage.registerAsMale(firstName, lastName, email, password);

        PlaywrightAssertions.assertThat(getPage())
                .hasURL(UrlPatterns.REGISTER_RESULT_PAGE);
    }
}