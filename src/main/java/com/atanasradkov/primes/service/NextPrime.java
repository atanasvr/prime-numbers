package com.atanasradkov.primes.service;

public class NextPrime {
    private NumberIsPrime checkPrime = new NumberIsPrime();

    public int nextPrime(int num) {
        while(!checkPrime.isPrime(++num));
        return num;
    }
}
