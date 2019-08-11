Feature: Verify Mortgage Rate Functionality

Scenario: Navitage to https://nationwide.co.uk
Given user launch the browser
When user enter the url
And user should see Nationwide screen
And user to move to Mortgages option
And navigate to New Mortgage customer and click on Mortgage Rates
And select option NO for Nationwide Mortgage
And select CHANGING LENDER as Mortgage type
And user to enter property value
And user to enter Mortgage Amount
And user to enter Term details
And user to click Find Mortgage Rate
And user to select Mortgage Type
And user to select Product fee
And user to check the details displayed in the screen
And user to click on MORE INFO and APPLY for 5 Yr Fixed
And user to click APPLY button
And user to validate the text START YOUR REMORTGAGE APPLICATION
Then user to close the screen

