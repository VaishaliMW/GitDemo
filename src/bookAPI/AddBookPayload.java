package bookAPI;

public class AddBookPayload {
	@Test

	public static String PostPayload(String aisle ,String isbn) {
		String payload="{\r\n"
		+ "\r\n"
		+ "\"name\":\"Learn Appium Automation with Java Selenium\",\r\n"
		+ "\"isbn\":\""+isbn+"\",\r\n"
		+ "\"aisle\":\""+aisle+"\",\r\n"
		+ "\"author\":\"John foee\"\r\n"
		+ "}\r\n"
		+ "";
		return payload;
		
	}
}
