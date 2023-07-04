package Serialization;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.ArrayList;

import io.restassured.RestAssured;
import io.restassured.response.Response;


public class serializeTest {
	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		AddPlace ap=new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");
		
		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		List<String> ls=new ArrayList<String>();
		ls.add("shoe park");
		ls.add("shop");
		ap.setTypes(ls);
		Location lo=new Location();
		lo.setLat(-38.383494);
		lo.setLng(33.427362);
		ap.setLocation(lo);
		ap.setWebsite("http://google.com");
		
		Response res =given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body(ap)
		.when().post("/maps/api/place/add/json")
	
.then().assertThat().statusCode(200).extract().response();
		String sc=res.asString();
		System.out.println(sc);
		
}}
