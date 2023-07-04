import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import Files.Payload;
import Files.PayloadJason;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class BasicPractice {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
				
			
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(Payload.addPlace())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		System.out.println(response);
		//response.get("$.[*].geo.*.");
		
		JsonPath js=new JsonPath(response);
		String pid=js.get("place_id");
		System.out.println("Place ID = " +pid);
		
		// update palce
		String address= "delhi lane1";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "   \r\n"
				+ "    \"place_id\": \"9be537d05f7867ed182edb15c1d5243f\",\r\n"
				+ "   \"address\": \""+address+"\",\r\n"
				+ "    \"key\": \"qaclick123\"\r\n"
				+ "}")
				.when().put("/maps/api/place/add/json")
				.then().assertThat().log().all().statusCode(200);
				//System.out.println(response);
		
		//get place
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", pid)
		.when().get("/maps/api/place/add/json")
		.then().assertThat().statusCode(0);
		
	}

}
