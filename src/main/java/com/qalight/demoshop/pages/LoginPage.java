package com.qalight.demoshop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorBlock;

    public LoginPage(Page page) {
        super(page);
        this.emailInput = page.locator("#Email");
        this.passwordInput = page.locator("#Password");
        this.loginButton = page.locator(".login-button");
        this.errorBlock = page.locator(".validation-summary-errors");
    }

    public LoginPage open() {
        page.navigate("/login");
        return this;
    }

    public LoginPage fillEmail(String email) {
        emailInput.fill(email);
        return this;
    }

    public LoginPage fillPassword(String password) {
        passwordInput.fill(password);
        return this;
    }

    public HomePage submitAndExpectSuccess() {
        loginButton.click();
        return new HomePage(page);
    }

    public LoginPage submitAndExpectFailure() {
        loginButton.click();
        return this;
    }

    public HomePage login(String email, String password) {
        return fillEmail(email)
                .fillPassword(password)
                .submitAndExpectSuccess();
    }

    public boolean isErrorVisible() {
        return errorBlock.isVisible();
    }

    public String getErrorText() {
        return errorBlock.textContent().trim();
    }
}