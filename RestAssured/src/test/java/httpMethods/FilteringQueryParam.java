package httpMethods;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class FilteringQueryParam {
	@Test
	public void getQueryParameter(){ //param are passed ingiven
	RestAssured.baseURI="https://jsonplaceholder.typicode.com/";
	Response response=RestAssured.given().queryParam("userId","1").when().get("posts/");
	System.out.println("status code--->"+response.getStatusCode());
	Assert.assertEquals(response.getStatusCode(), 200);
	
	JsonPath jspath=new JsonPath(response.getBody().asPrettyString());
	System.out.println("title-->"+jspath.get("title"));		//list of title are there in form of Array
	System.out.println("title size--->"+jspath.get("title.size")); //10
	System.out.println("first Title-->"+jspath.get("title[0]"));
	System.out.println("Second Title-->"+jspath.get("title[1]"));
	System.out.println("third Title-->"+jspath.get("title[2]"));
	System.out.println("fourth Title-->"+jspath.get("title[3]"));
	Assert.assertEquals(jspath.get("title[0]"),"sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
	
	ArrayList<String>actData=new ArrayList<String>();
	ArrayList<String>expData=new ArrayList<String>();
	expData.add("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
	expData.add("qui est esse");
	expData.add("ea molestias quasi exercitationem repellat qui ipsa sit aut");
	expData.add("eum et est occaecati");
	expData.add("nesciunt quas odio");
	expData.add("dolorem eum magni eos aperiam quia");
	expData.add("magnam facilis autem");
	expData.add("dolorem dolore est ipsam");
	expData.add("nesciunt iure omnis dolorem tempora et accusantium");
	expData.add("optio molestias id quia eum");
	
	int count=jspath.get("title.size");
	
	for(int i=0;i<count;i++){
		String text=(jspath.get("title["+i+"]"));
		actData.add(text);
	}
	Assert.assertEquals(actData, expData);
	}
}
