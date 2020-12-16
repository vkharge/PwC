Feature: Contact Us 

Scenario: To validate different options are presented for Contact Us 
	Given I navigate to the PwC Digital Pulse website
	When I select 'Contact us' from the hamburger menu
	Then I am taken to the 'Contact us' page
	And I am presented with the below options for contacts
		| Heading     					|   Contact Link  							 |
		| PwC Digital Services  		| https://digital.pwc.com/en/contact-us.html |
		| Digital Pulse editorial team	| 											 |
		| Careers at PwC 				| https://www.pwc.com/gx/en/careers.html 	 |
		| General enquiries				| https://www.pwc.com/gx/en.html 			 |