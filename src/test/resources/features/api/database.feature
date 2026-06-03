Feature: Database Validation

  Scenario: Verify database connection works
    * def DbUtils = Java.type('utils.DbUtils')
    * def rows = DbUtils.query("SELECT 1 As test")
    * print rows
    * match rows[0].test == 1
