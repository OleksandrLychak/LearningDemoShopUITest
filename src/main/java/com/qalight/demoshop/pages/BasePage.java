package com.qalight.demoshop.pages;

import com.microsoft.playwright.Page;

public abstract class BasePage {

    protected final Page page;

    protected BasePage(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }
}