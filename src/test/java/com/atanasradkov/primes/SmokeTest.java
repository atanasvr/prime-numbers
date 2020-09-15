package com.atanasradkov.primes;

import com.atanasradkov.primes.rest.PrimeNumbersController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private PrimeNumbersController primeNumbersController;

    @Test
    public void contextLoads() {
        assertNotNull(primeNumbersController);
    }



}
