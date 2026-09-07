package com.qalight.demoshop.tests;

import com.qalight.demoshop.facade.UiRegistrationFacade;
import com.qalight.demoshop.helpers.TestData;
import com.qalight.demoshop.models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UiFacadePatternTest extends BaseTest {

    @Test
    public void facadeHidesMultiStepRegistrationFlow() {
        User user = TestData.generateUser();

        boolean registered = new UiRegistrationFacade(getPage()).registerNewUser(user);

        Assert.assertTrue(registered, "Facade should report successful registration");
    }
}