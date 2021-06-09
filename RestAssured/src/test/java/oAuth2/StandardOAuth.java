package oAuth2;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class StandardOAuth {
	
		public String getAccessToken(){
			RestAssured.baseURI="http://coop.apps.symfonycasts.com/"; 
			Response response=RestAssured
						.given()
						.formParam("client_id", "APISession1")
						.formParam("client_secret", "24d4ea5029443becfac2fa9cf32756f1")
						.formParam("grant_type", "client_credentials")
						.when()
						.post("token");
			System.out.println("Resp body-->"+response.getBody().asPrettyString());
			JsonPath jspath=new JsonPath(response.getBody().asPrettyString());
			return jspath.get("access_token");
			}
		
		@Test                  //Unlock the Barn	   /api/USER_ID/barn-unlock	  POST	    try it
		public void oauth2(){
			RestAssured.baseURI="http://coop.apps.symfonycasts.com/";
			Response response=RestAssured
							.given()
							.auth()
							.oauth2(getAccessToken())
							.body("{\r\n" + 
							"    \"action\": \"The action (e.g. \\\"barn-unlock\\\")\",\r\n" + 
							"    \"success\": true,\r\n" + 
							"    \"message\": \"Some summary message\",\r\n" + 
							"    \"data\": \"A raw, related piece of data if applicable\"\r\n" + 
							"}")
							.when()
							.post("api/1959/barn-unlock");
			System.out.println("body-->"+response.getBody().asPrettyString());
			System.out.println("status code--->"+response.getStatusCode());
		
		}
}
