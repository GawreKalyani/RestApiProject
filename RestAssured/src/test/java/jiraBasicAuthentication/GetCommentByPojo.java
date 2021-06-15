package jiraBasicAuthentication;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojosForJira.Comment;
import pojosForJira.Root;

public class GetCommentByPojo {
	
//Get comments   GET /rest/api/2/issue/{issueIdOrKey}/comment
	@Test	
	public void getComments() {
		RestAssured.baseURI = "http://localhost:8080/rest/api/2/";
		Response response = RestAssured
				.given()
				.auth()
				.preemptive()
				.basic("yogitagawre", "yogitagawre").when()
				.get("issue/PROJ-14/comment");
		Assert.assertEquals(response.getStatusCode(),200);
		
		//System.out.println("body->"+response.getBody().asPrettyString());
		//output response ko copy karo
		//convert it into pojo using https://json2csharp.com/json-to-pojo
		
		Root root=response.body().as(Root.class);
		Assert.assertEquals(root.getMaxResults(),1048576);
		Assert.assertEquals(root.getStartAt(),0);
		Assert.assertEquals(root.getTotal(),6);
		
	//Output ko copy karo and paste to 	https://jsonformatter.curiousconcept.com/#
	//Easy to Assert
		List<Comment>comment=root.getComments();
		System.out.println(comment.get(0).getId());
		System.out.println(comment.get(2).getAuthor().getName());
		System.out.println(comment.get(3).getUpdateAuthor().getKey());
		System.out.println(comment.get(1).getBody());
		System.out.println(comment.get(4).getCreated());
		System.out.println(comment.get(4).getUpdated());
		Assert.assertEquals(comment.get(1).getId(),"10101");
		
		//just for Assertion
		ArrayList<String>act=new ArrayList<String>();
		ArrayList<String>exp=new ArrayList<String>();
		exp.add("10005");
		exp.add("10101");
		exp.add("10105");
		exp.add("10106");
		exp.add("10107");
		exp.add("10108");
		for(Comment comment1:root.getComments())
		{
			String allIds=comment1.getId();
			act.add(allIds);
			//System.out.println(comment1.getAuthor().getEmailAddress()); //all comments EmaiAdd is obtained
			//System.out.println(comment1.getBody());
		}
		Assert.assertEquals(act, exp);
	}
}
