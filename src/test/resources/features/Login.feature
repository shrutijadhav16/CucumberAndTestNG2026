Feature: Login Functionality


Scenario Outline: Login with valid creds
Given user is already on Login page
When user enters "<username>" and "<password>"
And user clicks on login button
Then user is on home page
Examples:
|username    | password |
|shruti1651997@gmail.com|Demo@123 |


Scenario Outline: Login with invalid creds
Given user is already on Login page
When user enters "<username>" and "<password>"
And user clicks on login button
Then user encounter error
Examples:
|username    | password |
|abc@gmail.com|Demo@123 |

