package com.qalight.demoshop.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {

    private static final Logger LOG = LoggerFactory.getLogger(HomePage.class);
    private final Locator registerLink;
    private final Locator loginLink;
    private final Locator logoutLink;
    private final Locator accountLink;
    private final Locator searchInput;
    private final Locator searchButton;

    public HomePage(Page page) {
        super(page);
        this.registerLink = page.locator(".ico-register");
        this.loginLink = page.locator(".ico-login");
        this.logoutLink = page.locator(".ico-logout");
        this.accountLink = page.locator(".account:not(:has-text('My account'))");
        this.searchInput = page.locator("#small-searchterms");
        this.searchButton = page.locator(".search-box-button");
    }

    public HomePage open() {
        LOG.info("Opening home page");
        page.navigate("/");
        return this;
    }

    public RegisterPage openRegisterPage() {
        LOG.info("Clicking 'Register' link");
        registerLink.click();
        return new RegisterPage(page);
    }

    public LoginPage openLoginPage() {
        LOG.info("Clicking 'Log in' link");
        loginLink.click();
        return new LoginPage(page);
    }

    public HomePage logout() {
        LOG.info("Clicking 'Log out' link");
        logoutLink.click();
        return this;
    }

    public HomePage searchFor(String query) {
        LOG.info("Searching for: '{}'", query);
        searchInput.fill(query);
        searchButton.click();
        return this;
    }

    public String getLoggedInUserEmail() {
        return accountLink.textContent();
    }

    public boolean isUserLoggedIn() {
        return accountLink.isVisible();
    }

    public boolean isLoginLinkVisible() {
        return loginLink.isVisible();
    }
}