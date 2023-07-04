package jira.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import java.io.File;

import org.testng.Assert;

public class JiraTest21 {
	public static void main(String[] args) {
		RestAssured.baseURI = "http://localhost:8080";
		SessionFilter session = new SessionFilter();
		String response = given().log().all().header("Content-Type", "application/json")
				.body("{\"username\": \"vaishaliwadaskar\", \"password\": \"Manish@123\"}").filter(session).when()
				.post("/rest/auth/1/session").then().log().all().extract().response().asString();

		given().log().all().pathParam("id", "10101").header("Content-Type", "application/json")

				// Add comment
				.body("{\r\n" + "    \"body\": \"This is my rest asured 1st comment\",\r\n"
						+ "    \"visibility\": {\r\n" + "        \"type\": \"role\",\r\n"
						+ "        \"value\": \"Administrators\"\r\n" + "    }\r\n" + "}")
				.filter(session).when().post("/rest/api/2/issue/{id}/comment").then().log().all().assertThat()
				.statusCode(201);

		// add attachments
		given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("key", "10101")

				.header("Content-Type", "multipart/form-data")

				.multiPart("file", new File("jira.txt")).when().

				post("rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
	
		//Get Issue

		String issueDetails=given().filter(session).pathParam("key", "10101")

		.queryParam("fields", "comment")

		.log().all().when().get("/rest/api/2/issue/{key}").then()

		.log().all().extract().response().asString();

		System.out.println(issueDetails);

		/*JsonPath js1 =new JsonPath(issueDetails);

		int commentsCount=js1.getInt("fields.comment.comments.size()");

		for(int i=0;i<commentsCount;i++)

		{

		String commentIdIssue =js1.get("fields.comment.comments["+i+"].id").toString();

		if (commentIdIssue.equalsIgnoreCase(commentId))

		{

		String message= js1.get("fields.comment.comments["+i+"].body").toString();

		System.out.println(message);

		Assert.assertEquals(message, expectedMessage);

		}*/
	
	
	}
	
	
	

}
