package bookAPI;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matcher.*;
import Files.PayloadJason;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
/*1. Print No of courses returned by API
2.Print Purchase Amount
3. Print Title of the first course
4. Print All course titles and their respective Prices
5. Print no of copies sold by RPA Course
6. Verify if Sum of all Course prices matches with Purchase Amount

*/

public class PracticeJson {
	@Test
	public void Couses() {
		JsonPath js = new JsonPath(PayloadJason.courses());
		// Print No of courses returned by API
		int count = js.get("courses.size()");
		System.out.println("1. Print No of courses returned by API : " + count);
		// 2.Print Purchase Amount
		System.out.print("2.Print Purchase Amount :");
		System.out.println(js.getInt("dashboard.purchaseAmount"));

		// 3. Print Title of the first course
		System.out.println("3. Print Title of the first course");
		System.out.println(js.get("courses[0].title").toString());

	}

	// 4. Print All course titles and their respective Prices
	@Test
	public void Couses1() {
		JsonPath js = new JsonPath(PayloadJason.courses());
		int count = js.get("courses.size()");
		System.out.println("//4. Print All course titles and their respective Prices");
		for (int i = 0; i < count; i++) {
			System.out.println(js.get("courses[" + i + "].title").toString());
		}
	}

	// 5. Print no of copies sold by RPA Course
	@Test
	public void Couses2() {
		JsonPath js = new JsonPath(PayloadJason.courses());
		int count = js.get("courses.size()");
		System.out.println("// 5. Print no of copies sold by RPA Course");

		for (int i = 0; i < count; i++) {
			String coursesTitle = js.get("courses[" + i + "].title");
			if (coursesTitle.equalsIgnoreCase("Selenium Python")) {
				int copies = js.get("courses[" + i + "].copies");
				System.out.println(copies);
			}
		}
	}

	// 6. Verify if Sum of all Course prices matches with Purchase Amount
	
	@Test
	public void Couses3() {
		System.out.println("// 6. Verify if Sum of all Course prices matches with Purchase Amount");
		int sum = 0;
		JsonPath js = new JsonPath(PayloadJason.courses());
		int count = js.get("courses.size()");
		for (int i = 0; i < count; i++) {
			String coursesTitle = js.get("courses[" + i + "].title");
			int price = js.get("courses[" + i + "].price");
			int copies = js.get("courses[" + i + "].copies");
			sum = sum + (price * copies);

		}
		System.out.println(sum);
		int purchaseAmount=js.get("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
	}
}