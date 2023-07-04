package bookAPI;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
/*Dyanamically build json PayLoad with external data inputs*/
public class DynamicJson {
	
	
	//@Test()
	public void addBooks() {
		RestAssured.baseURI="http://216.10.245.166";
		String response =given().log().all().header("Content-Type","application/json")
				.body(Payload.addBook("abfdg","3547"))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=new JsonPath(response);
		System.out.println(js.get("ID").toString());
		
	}
	
	
	/* Parameterise Api test with multiple data sets*/
	
	@Test(dataProvider="BooksDatas")
	public void addBooks(String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String response =given().log().all().header("Content-Type","application/json")
				.body(Payload.addBook("isbn","aisle"))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=new JsonPath(response);
		System.out.println(js.get("ID").toString());
		
	}
	
	
@DataProvider(name="BooksDatas")
public Object[][] getdata1() {
	//array: Collection of elements
	//Multidimention Array: Collection of arrays
	return new Object[][]{{"avbs","2544t"},{"afdh","3546"},{"sfdfg","47667"},{"rtyt","8793"},{"runv","5649"}};
	
}
	
	
//	@Test(dataProvider="BooksData")
	public void addBook(String isbn,String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type","application/json")
		.body(AddBookPayload.PostPayload(isbn,aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();

		JsonPath js=new JsonPath(response);
		String id=js.get("ID");
		System.out.println(id);
		
		

		
	}
//	@DataProvider(name="BooksData")
        public Object[][] getdata() {
		
		return new Object[][] {{"abcnfg","123456"},{"bcvnfh","9653"},{"ggdhhdttrgh","0865"}};
        	
        }
}
