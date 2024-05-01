Feature: HomePage

  Scenario: HomePage
    Then Check : Access to website
    Given Click Home Page Sign In Button
    Given Login with valid users
    When Fill in : Main Page : Enter "tank" Searchbox
    And Find : Product Page : First product which one "$29.00" dolar
    Then Verify : Product Page : Check this product
