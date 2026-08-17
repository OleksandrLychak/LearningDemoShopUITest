package com.qalight.demoshop.tests;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.qalight.demoshop.pages.HomePage;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

public class SearchTest extends BaseTest {

    @Test
    public void userCanSearchForProduct() {
        String searchQuery = "computer";

        new HomePage(getPage())
                .open()
                .searchFor(searchQuery);

        PlaywrightAssertions.assertThat(getPage())
                .hasURL(Pattern.compile(".*/search.*"));

        PlaywrightAssertions.assertThat(getPage())
                .hasTitle(Pattern.compile(".*Search.*"));
    }
}