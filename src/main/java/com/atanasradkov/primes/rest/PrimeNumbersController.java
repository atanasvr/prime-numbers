package com.atanasradkov.primes.rest;

import com.atanasradkov.primes.NotSupportedInputException;
import com.atanasradkov.primes.service.NextPrime;
import com.atanasradkov.primes.service.NumberIsPrime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("primes/api/v1")
@Api(value = "prime-numbers-api")
public class PrimeNumbersController {

    private static final int MAX_INPUT_NUMBER = 5000000;

    private NumberIsPrime numberIsPrime;
    public PrimeNumbersController(NumberIsPrime numberIsPrime) {
        this.numberIsPrime = numberIsPrime;
    }
    /**
     * Find out if a given number is a prime number.
     *
     * @param number the number to check
     * @return true if it's prime, false otherwise.
     */
    @ApiOperation(value = "Find out if a given number is prime.", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Check for prime number was successful"),
            @ApiResponse(code = 400, message = "The provided input is invalid or is not in range between 2 " +
                    "and " + MAX_INPUT_NUMBER)
    })
    @GetMapping(path = "/{number}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean isPrime(@PathVariable int number) {
       int input = validateInputNumber(number);
       return numberIsPrime.isPrime(input);
    }
    //TODO BDD Testing
    //TODO dependency injection check diff @Service and @Component in spring
    //TODO Rate limiter
    @ApiOperation(value = "Find out the next prime after given number.", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Check for prime number was successful"),
            @ApiResponse(code = 400, message = "The provided input is invalid or is not in range between 2 " +
                    "and " + MAX_INPUT_NUMBER)
    })
    @GetMapping(path = "/nextprime/{number}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public int findNextPrime(@PathVariable int number) {
        int input = validateInputNumber(number);
        return numberIsPrime.nextPrime(number);
    }

    private int validateInputNumber(int num) {
        try {
            if (num < 2 || num > MAX_INPUT_NUMBER) {
                throw new NotSupportedInputException("Provided input number is invalid. Input must be in the range of 2 and " + MAX_INPUT_NUMBER);
            }
            return num;
        } catch (NumberFormatException e) {
            throw new NotSupportedInputException("Invalid number: " + num);
        }
    }
}
