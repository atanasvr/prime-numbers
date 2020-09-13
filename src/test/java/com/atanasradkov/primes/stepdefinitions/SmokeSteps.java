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

    @Then("I should see result true")
    public void iShouldSeeResultTrue() throws IOException {
        this.checkStatus(200, false);
        this.checkBodyContains("true");
    }
}
