package com.atanasradkov.primes.service;

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
}
