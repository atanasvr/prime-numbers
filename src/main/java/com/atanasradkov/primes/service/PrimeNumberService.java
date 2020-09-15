package com.atanasradkov.primes.service;

import com.atanasradkov.primes.configuration.PrimeCalculationConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The {@link PrimeNumberService}
 * After the service is started it will start filling a local cache with numbers in range of 2 and max number.
 * <p>
 * The max number for filling the cache and for input can be found in {@link com.atanasradkov.primes.configuration.PrimeCalculationConfig}
 * Complexity:
 * For isPrime check the complexity is O(√N/2) and for generatePrimeNumbers is O(N/2)
 * For each iteration we have O(√N/2) * O(N/2) = O(N√N/4)
 * <p>
 * Memory:
 * The HashSet that we fill with primes is currently with upper number limit primes.cacheUpperLimit=1_000_000
 * For up to 1_000_000 the primes are 78 498
 * ~78 000 primes x 4 bytes(int) = 312000 bytes ~ 0,3 Mb
 * <p>
 */
@Service
@Slf4j
public class PrimeNumberService {

    private final PrimeCalculationConfig primesConfig;

    private final Set<Integer> primeNumbers;

    private final AtomicInteger currentMaxCache = new AtomicInteger(0);

    public PrimeNumberService(PrimeCalculationConfig primesConfig) {
        primeNumbers = Collections.synchronizedSet(new HashSet<>());
        this.primesConfig = primesConfig;
    }

    /**
     * Initialize the cache filling
     * {@link #generatePrimeNumbers()}
     */
    @PostConstruct
    public void initCache() {
        Executors.newCachedThreadPool().submit(this::generatePrimeNumbers);
    }

    /**
     * Method that check if a given number is prime
     * @param num int input for validation if prime
     *
     * @return boolean true if prime, false otherwise
     */
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
    }

    /**
     * Checks if a given number is prime by looking in cache or calling isPrime check
     * @param num the number that we want to check
     * {@link #isPrime(int)}
     * @return boolean by checking if number is in cache or the result form isPrime
     */
    public boolean isPrimeSearchInCache(int num) {
        // first check in cache
        if (num < currentMaxCache.get()) {
            log.info("Served from cache for {}", num);
            return primeNumbers.contains(num);
        }
        log.info("Calculated for {}", num);
        return isPrime(num);

    }

    /**
     * Gets the next prime after given number
     * @param num the input number that we want to get next prime
     * {@link #isPrimeSearchInCache(int)}
     * @return the next prime calculated
     */
    public int nextPrime(int num) {
        while(!isPrimeSearchInCache(++num));
        return num;
    }

    /**
     * Generates prime numbers and populates the local cache
     * {@link #primeNumbers}
     */
    public void generatePrimeNumbers() {
        log.info("Start building cache");
        primeNumbers.add(2);
        for (int i = 1; i <= primesConfig.getCacheUpperLimit(); i+=2) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
            currentMaxCache.set(i);
        }
        log.info("Finished building cache");
    }

}
