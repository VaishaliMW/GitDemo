import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	//6. Verify if Sum of all Course prices matches with Purchase Amount
	@Test
	  public void sumOfCourses() {
		JsonPath js=new JsonPath(Payload.coursePrice());
		int count=js.get("courses.size()");
		//int purchaseAmount=910;
		int sum=0;
		for(int i=0;i<count;i++) {
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			int totalPrice=price*copies;
			//System.out.print(js.getString("courses.title"));
			System.out.println(totalPrice);
			sum=sum+totalPrice;
			//System.out.println("Sum of all Course prices is = "+sum);
			//Assert.assertEquals(sum, purchaseAmount);
			
		}
		System.out.println("Sum of all Course prices is = "+sum);
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
	  }

	  
}
