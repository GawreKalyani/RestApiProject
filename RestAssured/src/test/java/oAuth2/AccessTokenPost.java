package oAuth2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AccessTokenPost {
	@Test
	public void getTokenAndThenPostReq(){
		//GetAcess Token
		RestAssured.baseURI="http://coop.apps.symfonycasts.com/"; 
		Response response=RestAssured
					.given()
					.formParam("client_id", "APISession1")
					.formParam("client_secret", "24d4ea5029443becfac2fa9cf32756f1")
					.formParam("grant_type", "client_credentials")
					.when()
					.post("token");
		System.out.println("body-->"+response.getBody().asPrettyString());
		JsonPath jspath=new JsonPath(response.getBody().asPrettyString());
		String accesstoken=jspath.get("access_token");
		System.out.println("AccessToken found to be:"+accesstoken);
		
		//post Request
		RestAssured.baseURI="http://coop.apps.symfonycasts.com/";
		Response response1=RestAssured
						.given()
						.auth()
						.oauth2(accesstoken)
						.body("{\r\n" + 
						"    \"action\": \"The action (e.g. \\\"barn-unlock\\\")\",\r\n" + 
						"    \"success\": true,\r\n" + 
						"    \"message\": \"Some summary message\",\r\n" + 
						"    \"data\": \"A raw, related piece of data if applicable\"\r\n" + 
						"}\r\n")
						.when()
						.post("api/1959/barn-unlock");
		System.out.println("body-->"+response1.getBody().asPrettyString());
		System.out.println("status code--->"+response1.getStatusCode());
	}
}
