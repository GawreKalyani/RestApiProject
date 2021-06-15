package jiraBasicAuthentication;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteMethod {
	@Test	//DELETE /rest/api/2/issue/{issueIdOrKey}/comment/{id}
	public void getComments() {
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
		Response response = RestAssured
				.given()
				.auth()
				.preemptive()
				.basic("yogitagawre", "yogitagawre")
				.when()
				.delete("issue/PROJ-14/comment/10104");
		System.out.println("Statuscode--->" + response.getStatusCode());
		System.out.println("body" + response.getBody().asPrettyString());//no body is there after delete comment
		Assert.assertEquals(response.getStatusCode(), 204);
		}
}
