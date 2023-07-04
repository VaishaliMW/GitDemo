import java.util.List;

import Files.PayloadParctice;
import io.restassured.path.json.JsonPath;

public class JsonPathPractice {
	public static void main(String[] args) {
		/*
		 * JsonPath js=new JsonPath(PayloadParctice.postId()); int count=
		 * js.getInt("a.size()"); System.out.println(count); System.out.println(
		 * js.get("body[1]").toString()); for(int i=0;i<count;i++) { int id=
		 * js.getInt("id["+i+"]"); System.out.println(id); System.out.println(
		 * js.get("name["+i+"]").toString()); }
		 */
		JsonPath jc = new JsonPath(PayloadParctice.comment());
		int count1 = jc.getInt("posts.size()");
		System.out.println("Count1 = " + count1);
		System.out.println(jc.get("profile.name").toString());
		System.out.println(jc.get("comments[0].body").toString());
		int Coomentcount1 = jc.getInt("comments.size()");
		for (int i = 0; i < Coomentcount1; i++) {
			int id = jc.getInt("comments.id[" + i + "]");
			System.out.println(id);
		}

		JsonPath jm = new JsonPath(PayloadParctice.mina());
		int count2 = jm.getInt("a.size()");
		System.out.println("count2 = "+count2);
		for (int i = 0; i < count2; i++) {
			System.out.println(jm.get("name["+i+"]").toString());
			System.out.println(jm.get("breed["+i+"]").toString());
			System.out.println(jm.get("color["+i+"]").toString());
			System.out.println( jm.get("kittens[" + i + "].name").toString());
			System.out.println( jm.get("kittens[" + i + "].gender").toString());
		}
		JsonPath cs=new JsonPath(PayloadParctice.webAutomation());
		int count3=cs.getInt("a.size()");
		System.out.println(count3);
		int webcount=cs.getInt("WebAutomation.size()");
		System.out.println("WebAutomation Course Titles are : ");
		for(int i=0;i<count3;i++) {
			List<String> Courses=cs.get("courses");
			System.out.println(Courses);
			List<String> ApiCourseTitles=cs.get("courses.Api["+i+"].courseTitle");
			System.out.println(ApiCourseTitles);
		}
		for(int i=0;i<count3;i++) {
			List<String> webAutomationCourseTitles=cs.get("courses.WebAutomation["+i+"].courseTitle");
			System.out.println(webAutomationCourseTitles);
			List<String> webAutomationCourseprice=cs.get("courses.WebAutomation["+i+"].price");
			System.out.println(webAutomationCourseprice);
		}

	}

}
