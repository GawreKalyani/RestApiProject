package pojoHttpMethod;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetMethod {
@Test
public void getMethod(){
	RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
	Response response=RestAssured.given().when().get("posts/1");
	
	PostPojo postPojo=response.getBody().as(PostPojo.class);

	
	Assert.assertEquals(response.getStatusCode(), 200);
	Assert.assertEquals(postPojo.getUserId(), "1");
	Assert.assertEquals(postPojo.getId(),"1");
	Assert.assertEquals(postPojo.getTitle(), "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
	Assert.assertEquals(postPojo.getBody(), "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
	
}
}
