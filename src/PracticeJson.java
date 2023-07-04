import Files.Payload;
import Files.PayloadJason;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import java.util.ArrayList;
import java.util.List;

public class PracticeJson {

	public static void main(String[] args) {
		
		JsonPath js=new JsonPath(PayloadJason.returenPayload());
		double count=js.getDouble("a.size()");
		System.out.println(count);
		String name=js.get("name[9]");
		System.out.println(name);
		for(int i=0;i<count;i++) {
			String street=js.get("address["+i+"].street");
			float lat=js.getFloat("address["+i+"].geo.lat");
			float lng=js.getFloat("address["+i+"].geo.lng");
			System.out.println(street);
			System.out.println("lattiude = "+lat);
			System.out.println("longitude = "+lng);
			//break;
		}
		for(int i=0;i<count;i++) {
			//int id=js.getInt("id["+i+"]");
			System.out.println(js.getInt("id["+i+"]"));
			//String companyName=js.get("company["+i+"].name");
			System.out.println(js.get("company["+i+"].name").toString());
		}
		
		
	}

}
