package httpMethods;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AdvanceBodyPost {
	@Test
	public void jsonPostEx(){
		JSONObject jo=new JSONObject();
		jo.put("name", "morpheus");
		jo.put("job", "leader");
		
		JSONArray ja=new JSONArray();
		ja.put("Java");
		ja.put("C");
		
		jo.put("skills", ja);
		JSONObject de=new JSONObject();
		de.put("companyName", "XYZ");
		de.put("emailId", "morpheus@xyz.com");
		jo.put("details",de);
		RestAssured.baseURI = "https://reqres.in/api/";
		Response response = RestAssured.given().header("Content-type", "application/json; charset=UTF-8")
							.body(jo.toString()).when()
							.post("users");
		System.out.println("status code--->"+response.getStatusCode());
		System.out.println("body-->"+response.getBody().asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 201);
	}
	
	@Test
	public void mixArraylistHash(){
		HashMap<String,Object>map=new HashMap<String,Object>();
		map.put("name","morpheus");
		map.put("job", "leader");
		
		ArrayList<String>sk=new ArrayList<String>();
		sk.add("Java");
		sk.add("C");
		
		map.put("skills", sk);
		
		HashMap<String,Object>de=new HashMap<String,Object>();
		de.put("companyName","XYZ");
		de.put("emailId", "morpheus@xyz.com");
		map.put("details", de);
		RestAssured.baseURI = "https://reqres.in/api/";
		Response response = RestAssured.given().header("Content-type", "application/json")
							.body(map.toString()).when()
							.post("users");
		System.out.println("status code--->"+response.getStatusCode());
		System.out.println("body-->"+response.getBody().asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 400);
	}
}
