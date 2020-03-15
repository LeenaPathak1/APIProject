package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinitions extends Utils {
	
	RequestSpecification res;
	TestDataBuild data =new TestDataBuild();
	ResponseSpecification resspec;
	Response resp;
	public static String place_id;
	
	@Given("^Add Place Payload with \"([^\"]*)\"  \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_Place_Payload_with(String name, String language, String address) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		RestAssured.useRelaxedHTTPSValidation();
	    res = given().spec(reqSpec())
	    		.body(data.createPayload(name,language,address));
	    		
	}

	@When("^user calls \"([^\"]*)\" with http \"([^\"]*)\" request$")
	public void user_calls_with_http_request(String resource, String method) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		APIResources apiresource = APIResources.valueOf(resource);
		System.out.println(apiresource.getResource());
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equals("POST")){
			
			resp = res.when().post(apiresource.getResource());
		}
		else if(method.equals("GET")){
			resp = res.when().get(apiresource.getResource());
		}
		
	}

	@Then("^the API call got success with status code (\\d+)$")
	public void the_API_call_got_success_with_status_code(int arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		Assert.assertEquals(resp.getStatusCode(), 200);
	   
	}

	@Then("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void in_response_body_is(String status, String expectedcode) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Assert.assertEquals(getRespValue(resp, status), expectedcode);
	   
	}
	
	@Then("^verify place_Id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
	public void verify_place_Id_created_maps_to_using(String ename, String resource) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		place_id=getRespValue(resp, "place_id");
		res= given().spec(reqSpec()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualname = getRespValue(resp, "name");
		Assert.assertEquals(actualname, ename);
		
	   
	}
	
	@Given("^DeletePlace Payload$")
	public void deleteplace_Payload() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    res= given().spec(reqSpec())
	    		.body(data.deletePlacePayload(place_id));
	}

	

}
