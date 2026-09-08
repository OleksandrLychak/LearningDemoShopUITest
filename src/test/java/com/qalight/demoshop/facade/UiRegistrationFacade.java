package com.qalight.demoshop.facade;

import com.microsoft.playwright.Page;
import com.qalight.demoshop.models.User;
import com.qalight.demoshop.pages.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class UiRegistrationFacade {

    private static final Logger LOG = LoggerFactory.getLogger(UiRegistrationFacade.class);

    private final Page page;

    public UiRegistrationFacade(Page page) {
        this.page = page;
    }

    public void registerNewUser(User user) {
        LOG.info("Facade: registering new user {}", user);

        new HomePage(page)
                .open()
                .openRegisterPage()
                .register(user);

        boolean success = page.url().contains("/registerresult");
        Assert.assertTrue(success,
                "Facade: registration should reach the result page for user " + user);
        LOG.info("Facade: registration succeeded");
    }
}