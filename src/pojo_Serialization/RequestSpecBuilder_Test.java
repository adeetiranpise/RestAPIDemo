package pojo_Serialization;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.List;


public class RequestSpecBuilder_Test  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	//	RestAssured.baseURI = "";
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
		RequestSpecification  req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		ResponseSpecification repspec= new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType("application/json").build();
		

		RequestSpecification res= given().spec(req).body(body);
		Response response = res.when().post("maps/api/place/add/json").then().spec(repspec).extract().response();
	System.out.println(response.asString());

	}

}
