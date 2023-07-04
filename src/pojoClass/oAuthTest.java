package pojoClass;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class oAuthTest {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.edgedriver().setup();
		WebDriver driver=new EdgeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");
		WebElement email=driver.findElement(By.xpath("//div[@data-email='vaishaliwadaskar12@gmail.com']"));
				email.click();
		Thread.sleep(3000);
		String url=driver.getCurrentUrl();
		//String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AbUR2VNMoJBE-avG3I8Y24bfzaDyiZYIJRjkQKuUEZ05Jr4uo4w_58gokWX4xtqZWtUm9Q&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent";
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		System.out.println(code);
		//RestAssured.baseURI="";
		String tokenResponse = given().urlEncodingEnabled(false).queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams( "client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath js=new JsonPath(tokenResponse);
		String accessToken=js.getString("access_token");
		//System.out.println(accessToken);
		
		GetCourse gc=given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php")
		.as(GetCourse.class);
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
	System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
	List<Api> apiCourses=gc.getCourses().getApi();
	for(int i=0;i<apiCourses.size();i++) {
		if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Web Services")) {
			System.out.println(apiCourses.get(i).getPrice());
		}
		
	}
	}

}
