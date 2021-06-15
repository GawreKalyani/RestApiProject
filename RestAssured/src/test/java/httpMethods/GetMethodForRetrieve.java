package httpMethods;

import org.testng.Assert;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetMethodForRetrieve {

	@Test
	public void getMethod() {
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		Response response = RestAssured.given()
					.when()
					.get("posts/1");
		System.out.println("status code --->"+response.getStatusCode());
		System.out.println("body ---->"+response.getBody().asString());
		System.out.println("coockies---->"+response.getCookies());
		System.out.println("header--->"+response.getHeader("Content-Type"));
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.getHeader("Content-Type"),"application/json; charset=utf-8");
	}
	
	@Test
	public void test(){
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		Response r=RestAssured
							.given()
							.when()
							.get("posts/2");
		System.out.println("status code--->"+r.getStatusCode());
		System.out.println("body--->"+r.getBody().asPrettyString());
		System.out.println("header-->"+r.getHeader("Connection"));
		
		Assert.assertEquals(r.getStatusCode(), 200);
		Assert.assertEquals(r.getHeader("Connection"), "keep-alive");
	}
}
