package com.qalight.demoshop.api.client;

import com.qalight.demoshop.utils.StringUtils;

import static com.qalight.demoshop.api.client.EndpointConstants.*;

public final class ApiEndpoints {

    private static final String REGISTER_PATH =
            StringUtils.joinSegments(AUTH_SEGMENT, V2_SEGMENT, REGISTER_SEGMENT);
    private static final String LOGIN_PATH =
            StringUtils.joinSegments(AUTH_SEGMENT, LOGIN_SEGMENT);
    private static final String GUEST_SESSION_PATH =
            StringUtils.joinSegments(AUTH_SEGMENT, GUEST_SEGMENT);
    private static final String ACTIVATE_PROMO_CODE_PATH =
            StringUtils.joinSegments(API_V2_SEGMENT, PROMOCODES_SEGMENT, ACTIVATE_SEGMENT);

    private static final String ON_DEVICE_QUERY_PARAM = "on_device=true";

    private ApiEndpoints() {
        // constants holder, no instances
    }

    public static String register() {
        return REGISTER_PATH + "?" + ON_DEVICE_QUERY_PARAM;
    }

    public static String login() {
        return LOGIN_PATH + "?" + ON_DEVICE_QUERY_PARAM;
    }

    public static String guestSession() {
        return GUEST_SESSION_PATH;
    }

    public static String activatePromoCode() {
        return ACTIVATE_PROMO_CODE_PATH;
    }
}