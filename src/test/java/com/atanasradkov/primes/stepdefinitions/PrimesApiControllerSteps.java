package com.atanasradkov.primes.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PrimesApiControllerSteps extends AbstractSteps {

    @And("response should be json")
    public void responseShouldBeJson() {
    }

    @When("I open address {string} to check {string} is prime")
    public void iOpenAddressToCheckIsPrime(String isPrimeUrl, String input) {
        this.executeGetRequest(isPrimeUrl+input);
    }

    @Then("I should get status {int}")
    public void iShouldGetStatus(int status) {
        this.checkStatus(status, false);
    }

    @And("I should get {string}")
    public void iShouldGet(String message) {
        this.checkBodyContains(message);
    }
}
