package com.qalight.demoshop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RegisterPage extends BasePage {

    private final Locator genderMale;
    private final Locator genderFemale;
    private final Locator firstNameInput;
    private final Locator lastNameInput;
    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator confirmPasswordInput;
    private final Locator registerButton;

    public RegisterPage(Page page) {
        super(page);
        this.genderMale = page.locator("#gender-male");
        this.genderFemale = page.locator("#gender-female");
        this.firstNameInput = page.locator("#FirstName");
        this.lastNameInput = page.locator("#LastName");
        this.emailInput = page.locator("#Email");
        this.passwordInput = page.locator("#Password");
        this.confirmPasswordInput = page.locator("#ConfirmPassword");
        this.registerButton = page.locator("#register-button");
    }

    public RegisterPage open() {
        page.navigate("/register");
        return this;
    }

    public RegisterPage selectMale() {
        genderMale.check();
        return this;
    }

    public RegisterPage selectFemale() {
        genderFemale.check();
        return this;
    }

    public RegisterPage fillFirstName(String firstName) {
        firstNameInput.fill(firstName);
        return this;
    }

    public RegisterPage fillLastName(String lastName) {
        lastNameInput.fill(lastName);
        return this;
    }

    public RegisterPage fillEmail(String email) {
        emailInput.fill(email);
        return this;
    }

    public RegisterPage fillPassword(String password) {
        passwordInput.fill(password);
        return this;
    }

    public RegisterPage fillConfirmPassword(String confirmPassword) {
        confirmPasswordInput.fill(confirmPassword);
        return this;
    }

    public RegisterPage submit() {
        registerButton.click();
        return this;
    }

    public RegisterPage registerAsMale(String firstName,
                                       String lastName,
                                       String email,
                                       String password) {
        return selectMale()
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillEmail(email)
                .fillPassword(password)
                .fillConfirmPassword(password)
                .submit();
    }
}