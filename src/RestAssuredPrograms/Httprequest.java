package RestAssuredPrograms;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class Httprequest {
	
	@Test
	void grtUser() {
		//RestAssured.baseURI("https://reqres.in/");
		given().queryParam("page", 2).
		when().get("https://reqres.in/api/users").
		then().log().all().statusCode(200);
		
	}
	@Test(priority=1)
	void PostUser() {
	HashMap<String, String> hs=new HashMap();
	hs.put("name", "morpheus");
	hs.put("job","zion rest");

	given().header("Content-Type","application/json").body(hs).
	when().put("api/users/2").jsonPath().getInt("id");
	//then().log().all().statusCode(201);
	}	

}
