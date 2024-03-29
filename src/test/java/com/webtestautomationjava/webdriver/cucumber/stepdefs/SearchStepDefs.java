package com.webtestautomationjava.webdriver.cucumber.stepdefs;

import com.webtestautomationjava.webdriver.cucumber.CukesSharedDriver;
import com.webtestautomationjava.webdriver.cucumber.pageobjects.SearchPageObject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class SearchStepDefs {
    private SearchPageObject searchPage;
    private WebDriver webDriver;

    public SearchStepDefs(CukesSharedDriver webDriver) {
        this.webDriver = webDriver;
        searchPage = new SearchPageObject(webDriver);
    }

    @Given("^I am on the website '(.+)'$")
    public void I_am_on_the_website(String url) throws Throwable {
        webDriver.get(url);
    }

    @When("^I submit the search term '(.+)'$")
    public void I_submit_the_search_term(String searchTerm) throws Throwable {
        searchPage.enterSearchTerm(searchTerm);
        searchPage.submitSearch();
    }

    @When("^accept the first search result$")
    public void accept_the_first_search_result() throws Throwable {
        searchPage.acceptSearchResult(0);

        //wait up to 5 seconds for redirect to complete
        for(int i=0; i<5; i++) {
            if(!webDriver.getCurrentUrl().contains("google")) { break; }
            Thread.sleep(1000);
        }
    }

    @Then("^I should be on the page '(.+)'$")
    public void I_should_be_on_the_page(String url) throws Throwable {
        assertEquals(url, webDriver.getCurrentUrl());
    }
}
