package com.atanasradkov.primes;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class PrimeNumberContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        TestPropertyValues.of(
                "primes.cacheUpperLimit=29",
                "primes.maxNumberToCalculate=29"
        ).applyTo(configurableApplicationContext.getEnvironment());
    }
}
