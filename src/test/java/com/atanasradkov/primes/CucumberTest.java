package com.atanasradkov.primes;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

/**
 * To run cucumber test
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", plugin = {"pretty",
        "json:target/cucumber-report.json"})
public class CucumberTest {
//    @Test
//    public void test(){}
}
