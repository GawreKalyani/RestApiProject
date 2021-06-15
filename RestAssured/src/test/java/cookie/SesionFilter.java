package cookie;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;


public class SesionFilter {
	
	//Step 1. Create a new session using the Jira REST API and resp body give sessVal for POst
	public SessionFilter getSessionId(){
		SessionFilter session=new SessionFilter();
		RestAssured.baseURI="http://localhost:8080/rest/auth/1/";
		RestAssured.given()
				.header("content-type","application/json")
				.body("{ \"username\": \"yogitagawre\", \"password\": \"yogitagawre\" }")
				.filter(session)
				.when()
				.post("session");

		return session;
	}
	
	@Test(priority=2)
	public void getCommentRequest(){
		RestAssured.baseURI="http://localhost:8080/rest/api/2/";
		Response res=RestAssured.given()
								.filter(getSessionId())
								.when()
								.get("issue/PROJ-14/comment");
		System.out.println("status code-->"+res.getStatusCode());
		System.out.println("get req body:"+res.getBody().asPrettyString());
	}
	
	@Test(priority=1)
	public void deleteCommentReq(){
		RestAssured.baseURI="http://localhost:8080/rest/api/2/";
		Response res=RestAssured.given()
								.filter(getSessionId())
								.when()
								.delete("issue/PROJ-10/comment");
		System.out.println("status code-->"+res.getStatusCode());
		
	}
	
	
	
	
	
	
	
}
