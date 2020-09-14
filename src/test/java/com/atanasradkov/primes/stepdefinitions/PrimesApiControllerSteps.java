package com.atanasradkov.primes.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class PrimesApiControllerSteps extends AbstractSteps {

    @And("response should be json")
    public void responseShouldBeJson() {
    }

    @When("I open address {string} to check {int} is prime")
    public void iOpenAddressToCheckIsPrime(String isPrimeUrl, int number) {
        this.executeGetRequest(isPrimeUrl+number);
    }

    @Then("I should get status {int}")
    public void iShouldGetStatus(int status) {
        this.checkStatus(status, false);
    }

    @And("I should get {string} error")
    public void iShouldGetError(String err) {
        this.checkBodyContains(err);
    }
}
