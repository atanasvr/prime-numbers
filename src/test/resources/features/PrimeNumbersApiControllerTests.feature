Feature: PrimeNumbers controller tests

  Scenario: Prime check should return an error if number submitted is < 2
    When I open address "/primes/api/v1/" to submit "1"
    Then I should get status 400
    And I should get "Provided input number is invalid"
    And response should be json

  Scenario: Prime check should return ok response if number is 3
    When I open address "/primes/api/v1/" to submit "3"
    Then I should get status 200
    And I should get "true"

  Scenario: Prime check should return ok response if number submitted equals maxnumber
    When I open address "/primes/api/v1/" to submit "2000000"
    Then I should get status 200
    And I should get "false"

  Scenario: Prime check should return an error if number submitted > maxnumber
    When I open address "/primes/api/v1/" to submit "2000001"
    Then I should get status 400
    And I should get "Provided input number is invalid"
    And response should be json

  Scenario Outline: Prime check should return error if input is invalid alpha numeric string
    When I open address "<address>" to submit "<input>"
    Then I should get status 400
    And I should get "Invalid input"
    And response should be json
    Examples:
      | address         | input |
      | /primes/api/v1/ | $1    |
      | /primes/api/v1/ | 0,1   |
      | /primes/api/v1/ | 5,6   |
      | /primes/api/v1/ | 1.1   |
      | /primes/api/v1/ | $(*&  |
      | /primes/api/v1/ | 3,0   |
      | /primes/api/v1/ | 5.0   |

  Scenario Outline: Prime check should return correct response in input is valid
    When I open address "<address>" to submit "<input>"
    And I should get "<result>"
    Examples:
      | address         | input | result |
      | /primes/api/v1/ | 2     | true   |
      | /primes/api/v1/ | 002   | true   |
      | /primes/api/v1/ | 0101  | true   |
      | /primes/api/v1/ | 0100  | false  |
      | /primes/api/v1/ | 0002  | true   |
      | /primes/api/v1/ | 23765 | false  |


  Scenario: Next prime should return error if number submitted is < 2
    When I open address "primes/api/v1/nextprime/" to submit "1"
    Then I should get status 400
    And I should get "Provided input number is invalid"
    And response should be json

  Scenario: Next prime should return error if number submitted is > max number
    When I open address "primes/api/v1/nextprime/" to submit "2000001"
    Then I should get status 400
    And I should get "Provided input number is invalid"
    And response should be json
    
  Scenario: Next prime should calculate correct if input is valid
    When I open address "primes/api/v1/nextprime/" to submit "3"
    Then I should get status 200
    And I should get "5"

  Scenario: Next prime should calculate correct if input is valid
    When I open address "primes/api/v1/nextprime/" to submit "2000000"
    Then I should get status 200
    And I should get "2000003"

  Scenario Outline: Next prime should return error if input is invalid alpha numeric string
    When I open address "<address>" to submit "<input>"
    Then I should get status 400
    And I should get "Invalid input"
    And response should be json
    Examples:
      | address         | input |
      | /primes/api/v1/nextprime | $1    |
      | /primes/api/v1/nextprime | 0,1   |
      | /primes/api/v1/nextprime | 5,6   |
      | /primes/api/v1/nextprime | 1.1   |
      | /primes/api/v1/nextprime | $(*&  |
      | /primes/api/v1/nextprime | 3,0   |
      | /primes/api/v1/nextprime | 5.0   |
      | /primes/api/v1/nextprime | abc   |
      | /primes/api/v1/nextprime | 1xe2  |
      | /primes/api/v1/nextprime | 5.0   |
      | /primes/api/v1/nextprime | 7C    |

  Scenario Outline: Next prime should return correct response if input is valid
    When I open address "<address>" to submit "<input>"
    Then I should get status 200
    And I should get "<result>"
    Examples:
      | address         | input | result |
      | /primes/api/v1/nextprime/ | 2      | 3     |
      | /primes/api/v1/nextprime/ | 123    |127    |
      | /primes/api/v1/nextprime/ | 02     | 3     |
      | /primes/api/v1/nextprime/ | 020    | 23    |
      | /primes/api/v1/nextprime/ | 0002000|2003   |
      | /primes/api/v1/nextprime/ | 7639   |7643   |
      | /primes/api/v1/nextprime/ | 7643   |7649   |
      | /primes/api/v1/nextprime/ | 7649   |7669   |
      | /primes/api/v1/nextprime/ | 123321 |123323 |
      | /primes/api/v1/nextprime/ | 123321 |123323 |


