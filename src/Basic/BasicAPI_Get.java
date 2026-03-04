package Basic;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import file.PayLoadBody;

public class BasicAPI_Get {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Add place --> UpDate palace with new address --> Get place to validate is new
		// address is updated or not
		// 1. Given-->> POST method to create new address
		// 2. Given -->> PUT method to change address
		// 3. Given-->> GET method to check new address

//=========================================================================================================================================================		

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		/********************** Add place **************************************/

		String responce = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(PayLoadBody.addPlaceBody()).when().post("maps/api/place/add/json").then().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract()
				.asString();

		System.out.println(responce);

		JsonPath js = new JsonPath(responce);
		String placeID = js.get("place_id");

		System.out.println(placeID);
		/************************
		 * PUT- update address
		 ***************************************/
		String newAddress = "NEWwintasdasdasdsa walk, USA";
		given().log().all().param("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeID + "\",\r\n" + "\"address\":\"" + newAddress + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		/*******************
		 * GET- verify if address is updated
		 ***********************************/
		String getPlaceResponce = given().log().all().param("key", "qaclick123").param("place_id", placeID)
				.when().get("/maps/api/place/get/json")
	
				.then().assertThat().statusCode(200).extract().response().asString();

		JsonPath js1 = new JsonPath(getPlaceResponce);
		String actualAddress = js1.getString("address");	
		System.out.println("Get palace respon======  "+ actualAddress);
		
		Assert.assertEquals(newAddress, actualAddress);
	}

}
