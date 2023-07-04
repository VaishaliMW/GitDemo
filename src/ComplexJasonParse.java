import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJasonParse {

	public static void main(String[] args) {
		JsonPath js=new JsonPath(Payload.coursePrice());
		
		//1. Print No of courses returned by API
		int noOfCourse=js.getInt("courses.size()");
		System.out.println(noOfCourse);
		
		//2.Print Purchase Amount
		int purchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		//3. Print Title of the first course
		String coursetitle=js.getString("courses[0].title");
		System.out.println(coursetitle);
		
		//4. Print All course titles and their respective Prices
		for(int i=0;i< noOfCourse;i++) {
			String CoursesTitles=  js.get("courses["+i+"].title");
			int prices=js.get("courses["+i+"].price");
			System.out.print( CoursesTitles +":  ");
			System.out.println(prices);
			
			
			
		}
		
		//5. Print no of copies sold by RPA Course
		System.out.println("5. Print no of copies sold by RPA Course");
		for(int i=0;i< noOfCourse;i++) {
			String CoursesTitles=  js.get("courses["+i+"].title");
			if(CoursesTitles.equalsIgnoreCase("RPA")) {
				 int copies=js.get("courses["+i+"].copies");
				 System.out.println(copies);
				 break;
			}
			
			//5. Print no of copies sold by RPA Course
			
		}
	//int noOfCopiesRPA=	js.get("courses[2].copies");
	//System.out.println("No Of Copies of  RPA course : "+noOfCopiesRPA);
		
		
		//6. Verify if Sum of all Course prices matches with Purchase Amount
		System.out.println("6. Verify if Sum of all Course prices matches with Purchase Amount");
		int purchaseAmountcourses=910;
		for(int i=0;i<noOfCourse;i++) {
			//String CoursesTitles=js.get("courses["+i+"].title");
			int copies=js.get("courses["+i+"].copies");
			int price=js.get("courses["+i+"].price");
			int allCourseprices=copies*price;
			System.out.println("allCourseprices :"+allCourseprices);
			
			
		}
		
		
	}

}
