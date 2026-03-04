package pojo_Deserialization;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.List;

import io.restassured.parsing.Parser;

public class Oauth2_Test_DeSeri {

	public static void main(String[] args) throws InterruptedException {
		/*************************************************************************************
		 * 
		 */
		// TODO Auto-generated method stub
		// RestAssured.baseURI= "https://rahulshettyacademy.com/";
		// Create currentURI by executing the link on chrome--->>
		// https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=asdasdasdasdasd
		// Then capture the URI after login and use it as currentURI
		String currentURI = "https://rahulshettyacademy.com/getCourse.php?state=asdasdasdasdasd&code=4%2F0AX4XfWhLkTZPOcElbXDlds0JjRTjqAy6Z8LKbd18yMKNlZjRLgu0H5_7v8SIIxoVYk9qBw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
		String partialcode = currentURI.split("code=")[1];
		String code = partialcode.split("&scope")[0];

		String access_token_Rsponce = given().urlEncodingEnabled(false).queryParam("code", code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when()
				.post("https://www.googleapis.com/oauth2/v4/token").then().log().all().extract().response().asString();

		JsonPath js = new JsonPath(access_token_Rsponce);
		String access_token = js.getString("access_token");

		GetCources responce = given().queryParam("access_token", access_token).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCources.class);

		System.out.println(responce.getLinkedIn());
		System.out.println(responce.getServices());
		System.out.println(responce.getCourses().getWebAutomation().get(1).getCourseTitle()+":"+responce.getCourses().getWebAutomation().get(1).getPrice());
		List<Api> apiCources = responce.getCourses().getApi();
		for (int i=0; i<apiCources.size();i++) {
			String apicourceTitle= apiCources.get(i).getCourseTitle();
			if(apicourceTitle.equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(apicourceTitle +" : "+ apiCources.get(i).getPrice());
			}
		}
		// Print all cource Titles of WebAutomation:
		
	List<WebAutomation>  webCources = responce.getCourses().getWebAutomation();
	for (int i=0; i<webCources.size(); i++) {
		String titles= webCources.get(i).getCourseTitle();
		System.out.println("WebAutomation courseTitle :  "+titles);
	}
		
		
	}

}
