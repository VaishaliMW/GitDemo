package oAuthTest15;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojoClass.MainJson;
import pojoClass.Photos;

import static  io.restassured.RestAssured.*;

import java.util.List;

import Files.PayloadJason;

public class GetPhotos {

	public static void main(String[] args) {
		RestAssured.baseURI="https://my-json-server.typicode.com";
		MainJson mj=given().expect().defaultParser(Parser.JSON)
		.when().get("/typicode/demo/db")
		.as(MainJson.class);
		
		System.out.println( mj.getComments());
		
		//JsonPath js=new JsonPath(PayloadJason.returenPayload());
				//double count=js.getDouble("a.size()");
				/*JsonPath js=new JsonPath(PayloadJason.photos());
				int count=js.getInt("x.size()");
				System.out.println(count);
			
				for(int i=0;i<count;i++) {
				//List<String> title=js.get("title");
				List<String> title=js.getList("title");
				System.out.println(title);
				List<String> url=js.getList("url");
			    System.out.println(url);
			  List <Integer> id=js.getList("id");
			   System.out.println(id);
				
				
			}*/

		
}}
