package file;

public class PayLoadBody {

	public static String addPlaceBody() {

		String addPlacebody = "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n" + "  },\r\n" + "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Frontline adsasdsadsadhouse\",\r\n" + "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n" + "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n" + "  ],\r\n" + "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n" + "}";

		return addPlacebody;
	}

	public static String complexPayloadBody() {

		return "{\r\n" + "\r\n" + "\"dashboard\": {\r\n" + "\r\n" + "\"purchaseAmount\": 910,\r\n" + "\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n" + "\r\n" + "},\r\n" + "\r\n" + "\"courses\": [\r\n"
				+ "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"Selenium Python\",\r\n" + "\r\n" + "\"price\": 50,\r\n"
				+ "\r\n" + "\"copies\": 6\r\n" + "\r\n" + "},\r\n" + "\r\n" + "{\r\n" + "\r\n"
				+ "\"title\": \"Cypress\",\r\n" + "\r\n" + "\"price\": 40,\r\n" + "\r\n" + "\"copies\": 4\r\n" + "\r\n"
				+ "},\r\n" + "\r\n" + "{\r\n" + "\r\n" + "\"title\": \"RPA\",\r\n" + "\r\n" + "\"price\": 45,\r\n"
				+ "\r\n" + "\"copies\": 10\r\n" + "\r\n" + "}\r\n" + "\r\n" + "]\r\n" + "\r\n" + "}\r\n" + "";
	}
	
	public static String addBook(String isbn_value, String aisle_value) {
		{

			String payload=  "{\r\n"
					+ "    \"name\": \"Learn Appium Automation with Java\",\r\n"
					+ "    \"isbn\": \""+isbn_value+"\",\r\n"
					+ "    \"aisle\": \""+aisle_value+"\",\r\n"
					+ "    \"author\": \"John foe\"\r\n"
					+ "}";
			return payload;
			}
	}
	
	public static String bookId(String isbn_value, String aisle_value ) {
		String payload= "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+isbn_value+aisle_value+"\"\r\n"
				+ " \r\n"
				+ "} ";
		return payload;
	}
	public static String bookIdToGet(String isbn_value, String aisle_value ) {
		String payload= isbn_value+aisle_value;
		return payload;
		
	}
	
}
