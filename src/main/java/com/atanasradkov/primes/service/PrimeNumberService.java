package com.atanasradkov.primes.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class PrimeNumberService {

    private final static int MAX_CACHE = 1_000_000;

    private Set<Integer> primeNumbers;

    private AtomicInteger currentMaxCache = new AtomicInteger(0);

    public PrimeNumberService() {
        primeNumbers = Collections.synchronizedSet(new HashSet<>());
    }

    @PostConstruct
    public void initCache() {
        Executors.newCachedThreadPool().submit(this::generatePrimeNumbers);
    }

    public boolean isPrime(int num) {
        if (num <=1 ) {
            return false;
        }
        if (num == 2 ) {
            return true;
        }
        //check if n is a multiple of 2
        if (num%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=num;i+=2) {
            if(num%i==0)
                return false;
        }
        return true;
        // return primeNumbers.contains(num);
    }

    public boolean isPrimeSearchInCache(int num) {
        // first check in cache
        if (num < currentMaxCache.get()) {
            log.info("Served from cache for {}", num);
            return primeNumbers.contains(num);
        }
        log.info("Calculated for {}", num);
        return isPrime(num);

    }

    public int nextPrime(int num) {
        while(!isPrime(++num));
        return num;
    }

    public void generatePrimeNumbers() {
        log.info("Start building cache");
        primeNumbers.add(2);
        for (int i = 1; i <= MAX_CACHE; i+=2) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
            currentMaxCache.set(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("Finished building cache");
    }

    /*public void generatePrimeNumbers(int number) {

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
    }*/

}
