Feature: Smoke Test

  Scenario: App should be up and running
    Given I open "/actuator/health" address
    Then I should see status 200

  Scenario: App should successfully respond to check if number is prime
    When I check on "/primes/api/v1/" if 2 is prime
    Then I should see "true"

  Scenario: App should successfully respond when number not prime
    When I check on "/primes/api/v1/" if 4 is prime
    Then I should see "false"

  Scenario: App should return bad response when called with invalid input
    When I check on "/primes/api/v1/" if "abcd" is prime
    Then I should see status 400