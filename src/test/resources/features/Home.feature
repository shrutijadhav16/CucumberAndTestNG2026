Feature: Home Page Functionality

@home
Scenario Outline: Add Order
Given user is already on Login page
When user enters "<username>" and "<password>"
And user clicks on login button
Then user is on home page
And user adds "ZARA COAT 3" product to cart
Examples:
|username    | password |
|shruti1651997@gmail.com|Demo@123 |