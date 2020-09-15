package com.atanasradkov.primes.rest;

import com.atanasradkov.primes.configuration.PrimeCalculationConfig;
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

    private final PrimeCalculationConfig primesConfig;

    private final PrimeNumberService primeNumberService;

    @Autowired
    public PrimeNumbersController(PrimeCalculationConfig primesConfig, PrimeNumberService primeNumberService) {
        this.primesConfig = primesConfig;
        this.primeNumberService = primeNumberService;
    }
    /**
     * Find out if a given number is a prime number.
     *
     * @param number the number to check
     * @return boolean true if it's prime, false otherwise.
     */
    @ApiOperation(value = "Find out if a given number is prime", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Check for prime number was successful"),
            @ApiResponse(code = 400, message = "The provided input is invalid or is not in range between 2 and max range")
    })
    @GetMapping(path = "/{number}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean isPrime(@PathVariable String number) {
       int input = validateInputNumber(number);
       return primeNumberService.isPrimeSearchInCache(input);
    }

    /**
     * Finds out the next prime after given number
     *
     * @param number the number to check
     * @return int next prime number
     */
    @ApiOperation(value = "Find out the next prime after given number", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Check for prime number was successful"),
            @ApiResponse(code = 400, message = "The provided input is invalid or is not in range between 2 and max range")
    })
    @GetMapping(path = "/nextprime/{number}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public int findNextPrime(@PathVariable String number) {
        int input = validateInputNumber(number);
        return primeNumberService.nextPrime(input);
    }

    /**
     * Validates if the input is alphanumeric string
     *
     * @param input String for validation
     * @return parsed input as int
     */
    private int validateInputNumber(String input) {
        try {
            int num = Integer.parseInt(input);
            if (num < 2 || num > primesConfig.getMaxNumberToCalculate()) {
                log.info("The passed number is out of range");
                throw new NotSupportedInputException("Provided input number is invalid. Input must be in the range of 2 and " + primesConfig.getMaxNumberToCalculate());
            }
            return num;
        } catch (NumberFormatException e) {
            log.error("Invalid input", e);
            throw new NotSupportedInputException("Invalid input: " + input);
        }
    }
}
