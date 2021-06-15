package jiraBasicAuthentication;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostMethod {
	//status code:415 means something missing,add header there
	//changes done in body for body attribute"This comment is added from postman"
	
	@Test         //Add Comment   //POST /rest/api/2/issue/{issueIdOrKey}/comment
	public void postMethod()
	{
	RestAssured.baseURI="http://localhost:8080/rest/api/2/";
	Response response=RestAssured
			.given()
			.auth()
			.preemptive()
			.basic("yogitagawre", "yogitagawre")
			.header("Content-type", "application/json; charset=UTF-8")
			.body("{\r\n" + 
					"    \"body\": \"This comment is added from postman.\",\r\n" + 
					"    \"visibility\": {\r\n" + 
					"        \"type\": \"role\",\r\n" + 
					"        \"value\": \"Administrators\"\r\n" + 
					"    }\r\n" + 
					"}")
			.when()
			.post("issue/PROJ-14/comment");
	System.out.println("statusCode"+response.getStatusCode());
	System.out.println("body-->"+response.getBody().asPrettyString());
	Assert.assertEquals(response.getStatusCode(), 201);
	
	JsonPath jspath=new JsonPath(response.getBody().asPrettyString());
	System.out.println("json body-->"+jspath.get("body"));
	
	}
}
