package com.atanasradkov.primes.rest;

import com.atanasradkov.primes.service.PrimeNumberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("primes/api/v1")
@Api(value = "prime-numbers-api")
public class PrimeNumbersController {

    private static final int MAX_INPUT_NUMBER = 2_000_000;

    private PrimeNumberService primeNumberService;

    @Autowired
    public PrimeNumbersController(PrimeNumberService primeNumberService) {
        this.primeNumberService = primeNumberService;
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
    public boolean isPrime(@PathVariable String number) {
       int input = validateInputNumber(number);
       return primeNumberService.isPrimeSearchInCache(input);
    }
    //TODO BDD Testing
    //TODO Rate limiter
    @ApiOperation(value = "Find out the next prime after given number.", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Check for prime number was successful"),
            @ApiResponse(code = 400, message = "The provided input is invalid or is not in range between 2 " +
                    "and " + MAX_INPUT_NUMBER)
    })
    @GetMapping(path = "/nextprime/{number}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public int findNextPrime(@PathVariable String number) {
        int input = validateInputNumber(number);
        return primeNumberService.nextPrime(input);
    }

    private int validateInputNumber(String input) {
        try {
            int num = Integer.parseInt(input);
            if (num < 2 || num > MAX_INPUT_NUMBER) {
                log.info("The passed number is out of range");
                throw new NotSupportedInputException("Provided input number is invalid. Input must be in the range of 2 and " + MAX_INPUT_NUMBER);
            }
            return num;
        } catch (NumberFormatException e) {
            log.error("Invalid input", e);
            throw new NotSupportedInputException("Invalid input: " + input);
        }
    }
}
