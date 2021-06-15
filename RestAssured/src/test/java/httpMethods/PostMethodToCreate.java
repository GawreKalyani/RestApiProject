package httpMethods;

import java.util.HashMap;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostMethodToCreate {
	@Test
	public void postMethod() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		Response response = RestAssured
				.given()
				.header("Content-type", "application/json; charset=UTF-8")
				.body("" + "    \"title\": \"foo\",\r\n" + "    \"body\": \"bar\",\r\n" + "    \"userId\": \"1\"\r\n" + "}")
				.when()
				.post("posts");
		System.out.println("response-->" + response);
		System.out.println("body--->" + response.getBody().asPrettyString());
		System.out.println("status code--->" + response.statusCode());
		System.out.println("time-->" + response.time());
		Assert.assertEquals(response.statusCode(), 201);
	}

	@Test
	public void postMethod3() {

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("Host", "<calculated when request is sent>");
		hm.put("User-Agent", "PostmanRuntime/7.28.0");
		hm.put("Accept", "*/*");
		hm.put("Accept-Encoding", "gzip, deflate, br");
		hm.put("Connection", "keep-alive");
		hm.put("Content-Type", "application/json; charset=UTF-8");

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		Response r = RestAssured
								.given()
								.headers(hm)
								.body(
								"" + "    \"title\": \"foo\",\r\n" + "    \"body\": \"bar\",\r\n" + "    \"userId\": \"1\"\r\n" + "}")
								.when()
								.post("posts/1");
		
		System.out.println("header-->"+r.getHeaders());
		Assert.assertEquals(r.getStatusCode(), 400);

		HashMap<String, String> exp = new HashMap<String, String>();
		exp.put("Host", "<calculated when request is sent>");
		exp.put("User-Agent", "PostmanRuntime/7.28.0");
		exp.put("Accept", "*/*");
		exp.put("Accept-Encoding", "gzip, deflate, br");
		exp.put("Connection", "keep-alive");
		exp.put("Content-Type", "application/json; charset=UTF-8");
		Assert.assertEquals(hm, exp);

	}

	@Test
	public void postMethod2() {

		String o = "{\"title\": \"foo\",\"body\": \"bar\",\"userId\": \"1\"}";

		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		Response response = RestAssured.given().header("Content-type", "application/json; charset=UTF-8").body(o).when()
				.post("posts");

		System.out.println("response-->" + response);
		System.out.println("body--->" + response.getBody().asPrettyString());
		System.out.println("status code--->" + response.statusCode());
		System.out.println("time-->" + response.time());
		Assert.assertEquals(response.statusCode(), 201);
	}
	

}
// Setting to be done for Body
// Windows ---Preferences--(java--Editors-Typing)--RightHand Page --string with
// literal--Checked(right marks on option)
// wrap text automatically
// Escape text when passing into a string literal (right mark)
