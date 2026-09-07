package com.qalight.demoshop.facade;

import com.microsoft.playwright.Page;
import com.qalight.demoshop.models.User;
import com.qalight.demoshop.pages.HomePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UiRegistrationFacade {

    private static final Logger LOG = LoggerFactory.getLogger(UiRegistrationFacade.class);

    private final Page page;

    public UiRegistrationFacade(Page page) {
        this.page = page;
    }

    public boolean registerNewUser(User user) {
        LOG.info("Facade: registering new user {}", user);

        new HomePage(page)
                .open()
                .openRegisterPage()
                .register(user);

        boolean success = page.url().contains("/registerresult");
        LOG.info("Facade: registration {}", success ? "succeeded" : "failed");
        return success;
    }
}