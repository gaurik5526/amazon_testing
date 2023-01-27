Feature: Test Automation Scenario

@smoke
Scenario Outline: Search for Gel Pen and Empty Cart
	Given Application is opened and on Home Page
	When User perform search for a "<SearchItem>"
	Then goto Cart and empty cart
	Examples:
		|SearchItem|
		|Gel Pen|

