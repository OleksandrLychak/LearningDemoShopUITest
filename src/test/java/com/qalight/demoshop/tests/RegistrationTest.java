package com.qalight.demoshop.tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.qalight.demoshop.helpers.TestData;
import com.qalight.demoshop.pages.HomePage;
import com.qalight.demoshop.pages.RegisterPage;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class RegistrationTest extends BaseTest {

    @Test
    public void newUserCanRegisterWithUniqueEmail() {
        String email = TestData.generateUniqueEmail();
        String password = TestData.defaultPassword();
        String firstName = TestData.generateFirstName();
        String lastName = TestData.generateLastName();

        RegisterPage registerPage = new HomePage(getPage())
                .open()
                .openRegisterPage();

        registerPage.registerAsMale(firstName, lastName, email, password);

        PlaywrightAssertions.assertThat(getPage())
                .hasURL(Pattern.compile(".*/registerresult.*"));
    }
}