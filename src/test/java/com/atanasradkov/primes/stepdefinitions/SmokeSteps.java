package com.atanasradkov.primes.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class SmokeSteps extends AbstractSteps {

    @Given("I open {string} address")
    public void iOpenAddress(String healthStatus) {
        executeGetRequest(healthStatus);
    }

    @Then("I should see status {int}")
    public void iShouldSeeStatus(int status) {
        this.checkStatus(status, false);
    }

    @When("I check on {string} if {int} is prime")
    public void iCheckOnIfIsPrime(String isPrimeUrl, int number) {
        executeGetRequest(isPrimeUrl+number);
    }

    @Then("I should see {string}")
    public void iShouldSee(String result) {
        this.checkStatus(200, false);
        this.checkBodyContains(result);
    }

    @When("I check on {string} if {string} is prime")
    public void iCheckOnIfIsPrime(String isPrimeUrl, String invalidString) {
        executeGetRequest(isPrimeUrl+invalidString);
    }
}
