package cookie;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AuthenticationByCookies {
@Test
public void getSessionId(){
	//Step 1. Create a new session using the Jira REST API and resp body give sessVal
	RestAssured.baseURI="http://localhost:8080/rest/auth/1/";
	Response response=RestAssured.given()
								.header("content-type","application/json")
								.body("{ \"username\": \"yogitagawre\", \"password\": \"yogitagawre\" }")
								.when()
								.post("session");
	
	System.out.println("status code-->"+response.getStatusCode());
	System.out.println("body-->"+response.getBody().asPrettyString());
	
	JsonPath jspath=new JsonPath(response.getBody().asPrettyString());
	System.out.println("session id val"+jspath.get("session.value"));

	String sessIdVal="JSESSIONID"+"="+jspath.get("session.value");
	
	
	//Step 2. Use the session cookie in a request
	//session cookie in request "GET"
	RestAssured.baseURI="http://localhost:8080/rest/api/2/";
	Response res=RestAssured.given()
							.headers("cookie", sessIdVal)
							.when()
							.get("issue/PROJ-14/comment");
	System.out.println("status code-->"+res.getStatusCode());
	System.out.println("get req body:"+res.getBody().asPrettyString());
	
	}
}
//https://developer.atlassian.com/server/jira/platform/cookie-based-authentication/
//In this step, to get a session cookie from Jira, you will need 
//to create a new session using the " sessionresource" in the Jira REST API. 
//End Point:     http://jira.example.com:8090/jira/rest/auth/1/session
//End Point:     http://localhost:8080/rest/auth/1/session
//	RestAssured.baseURI="http://localhost:8080/rest/auth/1/";
//                       .post("session");               //resource:session

//    api/2/issue Expand all methods  here issue is resource
//https://docs.atlassian.com/software/jira/docs/api/REST/8.5.15/#api/2/issue-addComment

//URI=  http://host:port/context/rest/api-name/api-version/resource-name
//URI=  http://localhost:8080/rest/api/2/issue           resName=issue

//Get comment
//GET /rest/api/2/issue/{issueIdOrKey}/comment/{id}
//base.uri=http://localhost:8080/rest/api/2/
//      .get("issue/PROJ-14/comment/10107")

//Get comments
//GET /rest/api/2/issue/{issueIdOrKey}/comment
//base.uri=http://localhost:8080/rest/api/2/
//.get("issue/PROJ-14/comment")