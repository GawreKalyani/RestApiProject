package oauth2WithPojo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostReq {

	public String getAccessToken() {
		RestAssured.baseURI="http://coop.apps.symfonycasts.com/"; 
		Response response=RestAssured
					.given()
					.formParam("client_id", "APISession1")
					.formParam("client_secret", "24d4ea5029443becfac2fa9cf32756f1")
					.formParam("grant_type", "client_credentials")
					.when()
					.post("token");
		AccessTokenPojo accessTokenPojo = response.getBody().as(AccessTokenPojo.class);
		
		return accessTokenPojo.getAccess_token();
	}
	
	@Test
	public void postMethod(){
		//pass request body
		RequestBodyPojo pojo=new RequestBodyPojo();
		pojo.setAction("The action (e.g. \"barn-unlock\")");
		pojo.setData("A raw, related piece of data if applicable");
		pojo.setMessage("Some summary message");
		pojo.setSuccess("true");
		
		
		
		RestAssured.baseURI="http://coop.apps.symfonycasts.com/";
		Response response=RestAssured
						.given()
						.auth()
						.oauth2(getAccessToken())
						.body(pojo)
						.when()
						.post("api/1959/barn-unlock");
		
		BarnUnlockPojo barnUnlockPojo=response.getBody().as(BarnUnlockPojo.class);
		
		System.out.println("Response body-->"+response.getBody().asPrettyString());
		System.out.println("Status code-->"+response.getStatusCode());
		
		Assert.assertEquals(barnUnlockPojo.getAction(), "barn-unlock");
		Assert.assertEquals(barnUnlockPojo.getSuccess(), "true");
		Assert.assertEquals(barnUnlockPojo.getMessage(), "You just unlocked your barn! Watch out for strangers!");
		Assert.assertEquals(barnUnlockPojo.getData(), null);
	}
}
