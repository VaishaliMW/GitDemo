package Serialization;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {
	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress("29, side layout, cohen 09");
		ap.setLanguage("French-IN");

		ap.setName("Frontline house");
		ap.setPhone_number("(+91) 983 893 3937");
		List<String> ls = new ArrayList<String>();
		ls.add("shoe park");
		ls.add("shop");
		ap.setTypes(ls);
		Location lo = new Location();
		lo.setLat(-38.383494);
		lo.setLng(33.427362);
		ap.setLocation(lo);
		ap.setWebsite("http://google.com");

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		ResponseSpecification ResponseSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		RequestSpecification res = given().spec(req).body(ap);

		Response response = res.when().post("/maps/api/place/add/json")

				.then().spec(ResponseSpec).extract().response();
		String sc = response.asString();
		System.out.println(sc);

	}

}
