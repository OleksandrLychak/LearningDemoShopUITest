package com.qalight.demoshop.tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.qalight.demoshop.utils.UrlPatterns;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void userCanSearchForProduct() {
        String searchQuery = "computer";

        openHomePage().searchFor(searchQuery);

        PlaywrightAssertions.assertThat(getPage())
                .hasURL(UrlPatterns.SEARCH_PAGE);

        PlaywrightAssertions.assertThat(getPage())
                .hasTitle(UrlPatterns.SEARCH_TITLE);
    }
}