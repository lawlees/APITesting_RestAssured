package APITestingg.com.TypiCodePost;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

//Install the JSON server from here: https://github.com/typicode/json-server
//Working with provided db in this module
public class JSONServerRequest {

	@Test
	public void GetResponse() {
		Response res = given().
				when().
				get("http://localhost:3000/posts/1");
		Assert.assertEquals(res.getStatusCode(),200);
		System.out.println("Found FIRST Post");

	}

	//post response
	@Test
	public void PostResponse() {
		given().
		body("{\r\n" + 
				"  \"id\": 4,\r\n" + 
				"  \"title\": \"test4\",\r\n" + 
				"  \"author\": \"I am lawlees\"\r\n" + 
				"}").
		when().
		contentType(ContentType.JSON).
		post("http://localhost:3000/posts/");
		System.out.println("Posted with ID 4");
	}

	@Test
	public void PostResponse_JsonBody() {
		Posts post= new Posts();
		post.setAuthor("lawful");
		post.setId("3");
		post.setTitle("Another title");

		given().
				body(post).
				when().
				contentType(ContentType.JSON).
				post("http://localhost:3000/posts/");

		//Assert.assertEquals( resp.getStatusCode(),200);
		System.out.println("Posted with ID 3");

	}


	@Test(dependsOnMethods={"PostResponse_JsonBody"})
	public void PutResquest_JsonBody() {
		Posts post= new Posts();
		post.setAuthor("lawfulness");
		post.setId("3");
		post.setTitle("Another title");

		Response resp =given().
				body(post).
				when().
				contentType(ContentType.JSON).
				put("http://localhost:3000/posts/"+post.getId());

		Assert.assertEquals( resp.getStatusCode(),200);
		System.out.println("Put Operation for post ID 3");

	}

	@Test(dependsOnMethods={"PostResponse"})
	public void PatchRequest_JsonBody() {
		Posts post= new Posts();
		post.setTitle("title updated by PATCH");

		Response resp =given().
				body(post).
				when().
				contentType(ContentType.JSON).
				put("http://localhost:3000/posts/4");

		Assert.assertEquals( resp.getStatusCode(),200);
		System.out.println("Patch Operation with Post ID 4");

	}

	@Test(dependsOnMethods={"PatchRequest_JsonBody"})
	public void DeleteRequest_JsonBody() {
		Response resp =given().
				when().
				delete("http://localhost:3000/posts/4");

		Assert.assertEquals( resp.getStatusCode(),200);
		System.out.println("Deleted Post ID 4");
	}
	
	@Test(dependsOnMethods={"PutResquest_JsonBody"})
	public void DeleteRequest_JsonBody2() {
		Response resp =given().
				when().
				delete("http://localhost:3000/posts/3");

		Assert.assertEquals( resp.getStatusCode(),200);
		System.out.println("Deleted post ID 3");
	}

}
