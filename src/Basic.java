import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Files.Payload;

public class Basic {
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//VAlidate if add place API is working as expected or not
		// given :All input details
		// when : submit the API {resource & http methods}
		// Then : Validate the response
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\vaish\\OneDrive\\Documents\\AddPlace.json"))))
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200)
				.body("scope", equalTo("APP")).header("Content-Type", "application/json;charset=UTF-8").extract()
				.response().asString();
		System.out.println(response);

		// Add Place--> Update with new id ---> Get place to validate if new address is
		// present in response

		JsonPath js = new JsonPath(response);
		String placeId=js.getString("place_id");
		System.out.println("placeId is : "+placeId);
		
		//Update  place 
		String newAddress="70 winter walk, USA";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated")).log().all();
		
		//get place
		String getPlaceResponse =given().log().all().queryParam("place_id",placeId)
		.queryParam("key", "qaclick123").header("Cache-Control","no-cache")
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		JsonPath jsget=new JsonPath(getPlaceResponse);
		String actualAddress=jsget.getString("address");
		System.out.println(actualAddress);
		
	
		
	}

}
