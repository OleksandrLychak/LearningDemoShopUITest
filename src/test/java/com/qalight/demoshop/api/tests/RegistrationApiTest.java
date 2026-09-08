package com.qalight.demoshop.api.tests;

import com.qalight.demoshop.api.facade.ApiAuthFacade;
import com.qalight.demoshop.api.helpers.ApiTestData;
import org.testng.annotations.Test;

public class RegistrationApiTest extends ApiBaseTest {

    @Test
    public void newUserCanRegisterAndReceiveSessionData() {
        String email = ApiTestData.generateUniqueEmail();
        String password = ApiTestData.defaultPassword();

        new ApiAuthFacade(getApiClient()).registerAndVerifySessionData(email, password);
    }
}