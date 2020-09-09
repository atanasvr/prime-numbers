package com.atanasradkov.primes.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class NumberIsPrime {
    public boolean isPrime(int num) {
        //check if n is a multiple of 2
        if (num%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=num;i+=2) {
            if(num%i==0)
                return false;
        }
        return true;
        /*// Corner cases
        if (num <= 1)  return false;
        if (num <= 3)  return true;

        if (num % 2 == 0 || num % 3 == 0) return false;

        for (int i = 5; i * i <= num; i = i + 6)
            if (num % i == 0 || num % (i + 2) == 0)
                return false;
        return true;*/
    }

    public boolean checkIfPrime()

    public ArrayList<Integer> generatePrimeNumbers(int number) {
        var primeNumbers = new ArrayList<Integer>();

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

        return primeNumbers;
    }

}
