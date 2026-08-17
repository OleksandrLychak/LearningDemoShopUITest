package com.qalight.demoshop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {

    private static final Logger LOG = LoggerFactory.getLogger(LoginPage.class);

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
        LOG.info("Opening login page");
        page.navigate("/login");
        return this;
    }

    public LoginPage fillEmail(String email) {
        LOG.info("Filling email: {}", email);
        emailInput.fill(email);
        return this;
    }

    public LoginPage fillPassword(String password) {
        LOG.info("Filling password field");
        passwordInput.fill(password);
        return this;
    }

    public HomePage submitAndExpectSuccess() {
        LOG.info("Submitting login form (expecting success)");
        loginButton.click();
        return new HomePage(page);
    }

    public LoginPage submitAndExpectFailure() {
        LOG.info("Submitting login form (expecting failure)");
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