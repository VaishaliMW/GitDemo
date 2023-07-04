import Files.PayloadJason;
import io.restassured.path.json.JsonPath;
public class JsonPractice {
	public static void main(String[] args) {
		

	JsonPath js=new JsonPath(PayloadJason.returenPayload());
	int asize=js.getInt("a.size()");
	System.out.println(asize);
	String name2=js.get("name[2]");
	System.out.println(name2);
	System.out.println(js.get("username[2]").toString());
	System.out.println("Value of Latittude: ");
	for(int i=0;i<asize;i++) {
	float d=js.getFloat("address["+i+"].geo.lat");
	System.out.println(d);
	String email=js.get("email["+i+"]");
	System.out.println(email);
	
	
	}
	System.out.println("Value of Longitude: ");
	for(int i=0;i<asize;i++) {
		
		float lan=js.getFloat("address["+i+"].geo.lng");
		System.out.println(lan);
		
		
		}	
	for(int i=0;i<asize;i++) {
		System.out.println("Company Name : " +js.get("company["+i+"].name").toString());
		System.out.println( "Street Address: "+js.get("address["+i+"].street").toString());
		
	}

	

}
}