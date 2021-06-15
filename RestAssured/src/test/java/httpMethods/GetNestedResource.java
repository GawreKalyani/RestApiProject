package httpMethods;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetNestedResource {

	@Test
	public void test(){
		RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
		Response response=RestAssured
									.given()
									.when()
									.get("posts/1/comments");
		System.out.println("status code-->"+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//we get body in response
		JsonPath jspath=new JsonPath(response.getBody().asPrettyString());
		System.out.println("email attribute-->"+jspath.getString("email"));//[-,-,-,-,-]
		System.out.println("email size-->"+jspath.get("email.size")); //5
		System.out.println("first email-->"+jspath.get("email[0]"));
		System.out.println("second email-->"+jspath.get("email[1]"));
		System.out.println("third email-->"+jspath.get("email[2]"));
		System.out.println("fourth email-->"+jspath.get("email[3]"));
		System.out.println("fifth email-->"+jspath.get("email[4]"));
		Assert.assertEquals(jspath.get("email[3]"), "Lew@alysha.tv");
		
		ArrayList<String>actData=new ArrayList<String>();
		ArrayList<String>expData=new ArrayList<String>();
		expData.add("id labore ex et quam laborum");
		expData.add("quo vero reiciendis velit similique earum");
		expData.add("odio adipisci rerum aut animi");
		expData.add("alias odio sit");
		expData.add("vero eaque aliquid doloribus et culpa");
		
		int count=jspath.get("name.size");
		
		for(int i=0;i<count;i++){
			String text=(jspath.get("name["+i+"]"));
			actData.add(text);
		}
		Assert.assertEquals(actData, expData);
	}
}
