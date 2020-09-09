package com.atanasradkov.primes.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class NumberIsPrime {

    ArrayList primeNumbers;
    public NumberIsPrime() {
        primeNumbers = new ArrayList<Integer>();
    }

    public boolean isPrime(int num) {
        /*//check if n is a multiple of 2
        if (num%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=num;i+=2) {
            if(num%i==0)
                return false;
        }
        return true;*/
        return primeNumbers.contains(num);
    }

    public int nextPrime(int num) {
        while(!isPrime(++num));
        return num;
    }

    public void generatePrimeNumbers(int number) {

        boolean[] prime = new boolean[number + 1];
        Arrays.fill(prime, true);

        for(int p = 2; p * p <= number; p++) {
            for(int i = p * p; i <= number; i += p)
                prime[i] = false;
        }

        for(int i = 2; i <= number; i++)
        {
            if(prime[i])
                primeNumbers.add(i);
        }
    }

}
