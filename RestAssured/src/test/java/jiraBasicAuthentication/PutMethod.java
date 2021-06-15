package jiraBasicAuthentication;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PutMethod {
	@Test
	public void putMethod(){
		JiraRequestBodyPost pojo=new JiraRequestBodyPost();
		pojo.setBody("Lorem ipsum is updated as per your need");
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
			.put("issue/PROJ-14/comment/10107");
	System.out.println("statusCode"+response.getStatusCode());
	System.out.println("body-->"+response.getBody().asPrettyString());
	Assert.assertEquals(response.getStatusCode(), 200);
	
	JsonPath jspath=new JsonPath(response.getBody().asPrettyString());
	System.out.println("json body-->"+jspath.get("body"));
	Assert.assertEquals(jspath.get("body"), "Lorem ipsum is updated as per your need");
	
	
	//PUT /rest/api/2/issue/{issueIdOrKey}/comment/{id}

	//we want comment id ,use "get" method "url=http://localhost:8080/rest/api/2/issue/PROJ-14/comment"
	//in response body ,check id of each comment     ex.10107 10104
	}

}
/*"{\r\n" + 
"    \"body\": \"Lorem ipsum is updated as per your need\",\r\n" + 
"    \"visibility\": {\r\n" + 
"        \"type\": \"role\",\r\n" + 
"        \"value\": \"Administrators\"\r\n" + 
"    }\r\n" + 
"}"*/