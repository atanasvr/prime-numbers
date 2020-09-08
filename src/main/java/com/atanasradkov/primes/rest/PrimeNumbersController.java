package com.atanasradkov.primes.rest;

import com.atanasradkov.primes.service.NextPrime;
import com.atanasradkov.primes.service.NumberIsPrime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("primes/api/v1")
@Api(value = "prime-numbers-api")
public class PrimeNumbersController {

    /**
     * Find out if a given number is a prime number.
     *
     * @param number the number to check
     * @return true if it's prime, false otherwise.
     */
    @ApiOperation(value = "Find out if a given number is prime.", produces = "application/json")
    @GetMapping(path = "/{number}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean isPrime(@PathVariable int number) {
       return new NumberIsPrime().isPrime(number);
    }

    @ApiOperation(value = "Find out the next prime after given number.", produces = "application/json")
    @GetMapping(path = "/nextprime/{number}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public int findNextPrime(@PathVariable int number) {
        return new NextPrime().nextPrime(number);
    }
    /*public PrimeNumbers numbers(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new PrimeNumbers(counter.incrementAndGet(), String.format(template, name));
    }*/
}
