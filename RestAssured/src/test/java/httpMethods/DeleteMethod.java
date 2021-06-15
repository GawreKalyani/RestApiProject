package httpMethods;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteMethod {
	@Test
	public void deleteMethod(){
	RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
	Response response=RestAssured
								.when()
								.delete("posts/1");
						
	System.out.println("statusCode-->"+response.getStatusCode());
	
	Assert.assertEquals(response.getStatusCode(), 200);
	}
}
