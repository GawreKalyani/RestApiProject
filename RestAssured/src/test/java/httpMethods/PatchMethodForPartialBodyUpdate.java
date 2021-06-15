package httpMethods;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PatchMethodForPartialBodyUpdate {
	@Test
	public void patchtMethod(){
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		Response response=RestAssured
				.given()
				.header("Content-type","application/json; charset=UTF-8")
				.body("{\r\n" + 
						"    \"title\": \"foo\"\r\n" + 
						"  }")
				.when()
				.put("posts/1");
				
		System.out.println("statusCode-->"+response.getStatusCode());
		System.out.println("body-->"+response.getBody().asPrettyString());
		System.out.println("header--->"+response.getHeader("Date"));
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
