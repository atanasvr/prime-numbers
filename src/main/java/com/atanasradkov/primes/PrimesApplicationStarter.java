package com.atanasradkov.primes;

import com.atanasradkov.primes.service.NumberIsPrime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PrimesApplicationStarter {

    private static final int MAX_INPUT_NUMBER = 5000000;

    private final NumberIsPrime numberIsPrime;

    @Autowired
    public PrimesApplicationStarter(NumberIsPrime numberIsPrime) {
        this.numberIsPrime = numberIsPrime;
    }


    @PostConstruct
    public void GeneratePrimes() {
        numberIsPrime.generatePrimeNumbers(MAX_INPUT_NUMBER);
    }
}
