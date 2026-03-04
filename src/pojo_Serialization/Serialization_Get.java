package pojo_Serialization;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.List;


public class Serialization_Get {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com/";
		BodyJSON_Main body= new BodyJSON_Main();
		body.setAccuracy(50);
		body.setAddress("29, side layout, cohen 09");
		body.setLanguage("French-IN");
		body.setName("Aditidfgfdgdfgfdgdfgfbdfgdf");
		body.setPhone_number("(+91) 983 893 3937");
		body.setWebsite("http://google.com");
		List <String> mylist= new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		body.setTypes(mylist);
		Location l= new Location();
		l.setLng(33.427362);
		l.setLat(-38.383494);
		body.setLocation(l);
		
		/********************** Add place **************************************/

		Response responce = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(body).when().post("maps/api/place/add/json").then().log().all()
				.assertThat()
				.statusCode(200).extract().response();

		
		System.out.println(responce.asString());

	}

}
