Feature: Smoke Test

  Scenario: App should be up and running
    Given I open "/actuator/health" address
    Then I should see status 200

  Scenario: App should successfully respond to check if number is prime
    When I check on "/primes/api/v1/" if 2 is prime
    Then I should see result true