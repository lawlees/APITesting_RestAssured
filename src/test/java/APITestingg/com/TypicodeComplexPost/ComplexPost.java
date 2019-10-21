package APITestingg.com.TypicodeComplexPost;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.jayway.restassured.http.ContentType;
import static com.jayway.restassured.RestAssured.*;

public class ComplexPost {
	
	/*
	 * Create new JSON server and have the following JSON. Here we are going to GET and POST
	 * this JSON with array type info
	 * 
	 * {
  "posts": [
    {
     "id": 1,
     "title": "JSON Server",
     "author": "Typicode",
	"info":[
	{
	"email":"test@email.com",
	"phone": "123456"	
	},
	{
	"email":"email@test.com",
	"phone": "654321"
	}
	]}
  ]
}
	 */
	
	@Test
	public void GetResponse() {
		Response res=given().
				when().
				get("http://localhost:3000/posts");
		Assert.assertEquals(res.getStatusCode(),200);
						
	}
	
	@Test
	public void PostResponse() {
		Info info1 = new Info();
		info1.setEmail("abc@gmail.com");
		info1.setPhone("123456789");
		
		Info info2 = new Info();
		info2.setEmail("cba@gmail.com");
		info2.setPhone("987654321");
				
		Post post = new Post();
		post.setAuthor("ABC");
		post.setId("2");
		post.setTitle("TITLE");
		post.setInfo(new Info[]{info1, info2});
		
				given().
				when().
				contentType(ContentType.JSON).
				body(post).
				post("http://localhost:3000/posts");	
	}
	
	@Test(dependsOnMethods={"PostResponse"})
	public void VerifyPostMethod() {
		Response res=given().
				when().
				get("http://localhost:3000/posts/2");

		
		System.out.println("Response Posted using Post Response \n"+res.asString());
		
	}
	
	
	@Test(dependsOnMethods={"VerifyPostMethod"})
	public void DeleteRequest_JsonBody2() {
		Response resp =given().
				when().
				delete("http://localhost:3000/posts/2");

		Assert.assertEquals( resp.getStatusCode(),200);
		System.out.println("Deleted post ID 3");
	}
	

}
