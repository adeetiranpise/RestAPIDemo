package Oauth2;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Oauth2_Test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		// RestAssured.baseURI= "https://rahulshettyacademy.com/";
		// Create currentURI by executing the link on chrome--->>
		// https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=asdasdasdasdasd
		// Then capture the URI after login and use it as currentURI
		String currentURI = "https://rahulshettyacademy.com/getCourse.php?state=asdasdasdasdasd&code=4%2F0AX4XfWgUg5TD15p2TyJjLVUWpbpiXNtobEUHVMaapVtK7OKzTXyEQq-52VYS7WSQrOrFvg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";
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

		String responce = given().queryParam("access_token", access_token).when()
				.get("https://rahulshettyacademy.com/getCourse.php").then().log().all().statusCode(200).extract()
				.response().asString();

		System.out.println(responce);

	}

}
