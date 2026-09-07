package com.qalight.demoshop.api.client;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApiRequestBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(ApiRequestBuilder.class);

    private final APIRequestContext request;
    private RequestOptions options = RequestOptions.create();

    public ApiRequestBuilder(APIRequestContext request) {
        this.request = request;
    }

    public ApiRequestBuilder withHeader(String name, String value) {
        options = options.setHeader(name, value);
        return this;
    }

    public ApiRequestBuilder withJsonContentType() {
        return withHeader("Content-Type", "application/json");
    }

    public ApiRequestBuilder withBody(String jsonBody) {
        options = options.setData(jsonBody);
        return this;
    }

    public APIResponse post(String url) {
        LOG.info("Fluent request: POST {}", url);
        return request.post(url, options);
    }

    public APIResponse get(String url) {
        LOG.info("Fluent request: GET {}", url);
        return request.get(url, options);
    }
}