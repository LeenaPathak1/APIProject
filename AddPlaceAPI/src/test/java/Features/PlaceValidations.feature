Feature: Validating place APIs
 
  Scenario Outline: Verify if place is being added successfully
    Given Add Place Payload with "<name>"  "<language>" "<address>"
    When user calls "AddPlaceAPI" with http "POST" request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"
    
   Examples:
   |name |language | address |
   |LLhouse | English | Park Street | 
    
    
    Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with http "POST" request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
    