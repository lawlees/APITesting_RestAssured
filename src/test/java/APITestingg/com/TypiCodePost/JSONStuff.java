package APITestingg.com.TypiCodePost;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

import java.util.ArrayList;
public class JSONStuff {
	//finding element in JSON using JSON path. Online at: https://jsonpath.com
	// JSON viewer: http://jsonviewer.stack.hu/
	//API Resources
	@Test
	public void GettingFirstPostTitle_JSONPath() {
		String firstTitle=given().
				when().
				get("https://jsonplaceholder.typicode.com/posts/1").
				then().
				contentType(ContentType.JSON).
				extract().
				path("title");

		String actualTitle="sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
		Assert.assertEquals(actualTitle, firstTitle);
	}
	
	//calculating the time using inbuilt rest assured library
	@Test
	public void TimeOfResponse() {
		Response res=given().
				when().
				get("https://jsonplaceholder.typicode.com/posts/1");

		Long time = res.
				then().
				extract().
				time();
		
		System.out.println("Get Response Time for method --> GetResponse: "+time);

	}

	@Test
	public void GettingAllPostTitleByUser1_JSONPath() {
		ArrayList<String> titleList = new ArrayList<String>();
		titleList=given().
				when().
				parameters("userId", "1").
				get("https://jsonplaceholder.typicode.com/posts").
				then().
				contentType(ContentType.JSON).
				extract().
				path("title");

		System.out.println("Number of Post by User 1: "+titleList.size());
		for(String title: titleList) {
			System.out.println("Title : "+title);
		}
	}

}
