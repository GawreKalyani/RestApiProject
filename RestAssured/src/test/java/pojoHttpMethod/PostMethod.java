package pojoHttpMethod;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PostMethod {
	@Test
	public void postMethod(){
		//request body pojo(Serialization)---->use set method (GetterAndSetter)
		ReqBodyPojoForPlac reqBodyPojoForPlac=new ReqBodyPojoForPlac();
		reqBodyPojoForPlac.setTitle("foo");
		reqBodyPojoForPlac.setBody("bar");
		reqBodyPojoForPlac.setUserId("1");
		
		
	RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
	Response response=RestAssured.given()
								.header("Content-type","application/json; charset=UTF-8")
								.body(reqBodyPojoForPlac)
								.when()
								.post("posts");
	
	//response body pojo(Deserialization)---->get Method (GetterAndSetter)
	PostPojo postPojo=response.getBody().as(PostPojo.class);
	Assert.assertEquals(response.getStatusCode(),201);
	
	//body attribute for assertion
	Assert.assertEquals(postPojo.getId(), "101");
	Assert.assertEquals(postPojo.getTitle(), "foo");
	Assert.assertEquals(postPojo.getBody(), "bar");
	Assert.assertEquals(postPojo.getUserId(), "1");
	
	}
}
