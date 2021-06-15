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
	{	//passing body in request
		JiraRequestBodyPost pojo=new JiraRequestBodyPost();
		pojo.setBody("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque eget venenatis elit. Duis eu justo eget augue iaculis fermentum. Sed semper quam laoreet nisi egestas at posuere augue semper.");
		Visibility visibility=new Visibility();
		visibility.setType("role");
		visibility.setValue("Administrators");
		
	RestAssured.baseURI="http://localhost:8080/rest/api/2/";
	Response response=RestAssured
			.given()
			.auth()
			.preemptive()
			.basic("yogitagawre", "yogitagawre")
			.header("Content-type", "application/json; charset=UTF-8")
			.body(pojo)
			.when()
			.post("issue/PROJ-14/comment");
	System.out.println("statusCode"+response.getStatusCode());
	System.out.println("body-->"+response.getBody().asPrettyString());
	Assert.assertEquals(response.getStatusCode(), 201);
	
	JsonPath jspath=new JsonPath(response.getBody().asPrettyString());
	System.out.println("json body-->"+jspath.get("body"));
	
	}
}
