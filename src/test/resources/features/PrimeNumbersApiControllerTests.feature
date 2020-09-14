Feature: PrimeNumbers controller tests

  Scenario: App should return an error if number submitted is < 1
    When I open address "/primes/api/v1/" to check 1 is prime
    Then I should get status 400
    And I should get "Provided input number is invalid" error
    And response should be json

  Scenario: App should return an error if number submitted > maxnumber
    When I open address "/primes/api/v1/" to check 2000001 is prime
    Then I should get status 400
    And I should get "Provided input number is invalid" error
    And response should be json
