package com.atanasradkov.primes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {PrimeNumberContextInitializer.class})
public class PrimeNumbersControllerTests {
    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void primeNumbersApishouldBeHealthy() {
        String uri = getBaseUrl() + "/actuator/health";
        ResponseEntity<Object> result = this.restTemplate.getForEntity(uri, Object.class);

        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    public void primeNumbersApishouldGetSuccessWhenCallingIsPrime() {
        int inputNumber = 3;
        String uri = getBaseUrl() + "/primes/api/v1/" + inputNumber;
        ResponseEntity<Object> result = this.restTemplate.getForEntity(uri, Object.class);

        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals(true, result.getBody());
    }

    @Test
    public void primeNumbersApiShouldGetSuccessWhenCallingIsPrimeNotPrime() {
        int inputNumber = 4;
        String uri = getBaseUrl() + "/primes/api/v1/" + inputNumber;
        ResponseEntity<Object> result = this.restTemplate.getForEntity(uri, Object.class);

        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals(false, result.getBody());
    }

    @Test
    public void primeApiShouldGetBadRequestWhenCallingIsPrimeInvalidInput() {
        String uri = getBaseUrl() + "/primes/api/v1/abcd";
        ResponseEntity<Object> result = this.restTemplate.getForEntity(uri, Object.class);

        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    public void primeApiShouldGetBadRequestWhenCallingIsPrimeInputOutOfRange() {
        int inputNumber = 31; //The maximum input number for tests is set to 29
        String uri = getBaseUrl() + "/primes/api/v1/" + inputNumber;
        ResponseEntity<Object> result = this.restTemplate.getForEntity(uri, Object.class);

        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    public void primeNumbersApishouldGetSuccessWhenCallingNextPrime() {
        int inputNumber = 2;
        String uri = getBaseUrl() + "/primes/api/v1/nextprime/" + inputNumber;
        ResponseEntity<Object> result = this.restTemplate.getForEntity(uri, Object.class);

        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals(3, result.getBody());
    }

    @Test
    public void primeNumbersApishouldGetBadRequestWhenCallingNextPrime() {
        int inputNumber = 2;
        String uri = getBaseUrl() + "/primes/api/v1/nextprime/" + inputNumber;
        ResponseEntity<Object> result = this.restTemplate.getForEntity(uri, Object.class);

        assertEquals(200, result.getStatusCodeValue());
        assertNotNull(result.getBody());
        assertEquals(3, result.getBody());
    }

    @Test
    public void primeApiShouldGetBadRequestWhenCallingNextPrimeInputOutOfRange() {
        int inputNumber = 31; //The maximum input number for tests is set to 29
        String uri = getBaseUrl() + "/primes/api/v1/nextprime/" + inputNumber;
        ResponseEntity<Object> result = this.restTemplate.getForEntity(uri, Object.class);

        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    public void primeNumbersApishouldGetBadRequestWhenCallingNextPrimeInvalidInput() {
        String uri = getBaseUrl() + "/primes/api/v1/nextprime/abcd";
        ResponseEntity<Object> result = this.restTemplate.getForEntity(uri, Object.class);

        assertEquals(400, result.getStatusCodeValue());
    }



    private String getBaseUrl() {
        return "http://localhost:" + randomServerPort;
    }
}
