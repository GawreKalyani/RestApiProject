package jira;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetMethod {
	
	@Test	//GetComments     //GET /rest/api/2/issue/{issueIdOrKey}/comment
	public void getComments() {
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
		Response response = RestAssured.given().auth().preemptive().basic("yogitagawre", "yogitagawre").when()
				.get("issue/PROJ-14/comment");
		System.out.println("Statuscode--->" + response.getStatusCode());
		System.out.println("body" + response.getBody().asPrettyString());

		//body me ka attribute fetch karn ho then use Jsonpath
		JsonPath jspath=new JsonPath(response.getBody().asPrettyString());
		System.out.println("key-->"+jspath.get("comments[0].author.key"));
		System.out.println("json body-->"+jspath.get("comments[0].body"));
		System.out.println("json maxResults-->"+jspath.get("maxResults"));
		
		Assert.assertEquals(jspath.get("comments[0].author.key"), "JIRAUSER10000");
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(jspath.get("comments[0].body"),"Joined Sample Sprint 2 1 days 4 hours 10 minutes ago");
	}
	@Test
	public void getMethod1(){
		//GetComment   GET /rest/api/2/issue/{issueIdOrKey}/comment/{id}
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
		Response response = RestAssured.given().auth().preemptive().basic("yogitagawre", "yogitagawre").when()
				.get("issue/PROJ-14/comment/10107");
		System.out.println("Statuscode--->" + response.getStatusCode());
		System.out.println("body" + response.getBody().asPrettyString());

		//body me ka attribute fetch karn ho then use Jsonpath
		JsonPath jspath=new JsonPath(response.getBody().asPrettyString());
		System.out.println("key-->"+jspath.get("author.key"));
		System.out.println("json body-->"+jspath.get("body"));
		System.out.println("json displayName-->"+jspath.get("author.displayName"));
		Assert.assertEquals( response.getStatusCode(), 200);
	}
	
	//In Given "pass Authentication",      response me "body","status code"
	
		@Test      //Get issue   //GET /rest/api/2/issue/{issueIdOrKey}
			public void getIssue() {
			RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
			Response response = RestAssured.given().auth().preemptive().basic("yogitagawre", "yogitagawre").when()
					.get("issue/PROJ-14");
			System.out.println("Statuscode--->" + response.getStatusCode());
			System.out.println("body" + response.getBody().asPrettyString());
			Assert.assertEquals(response.getStatusCode(), 200);
		}
}
