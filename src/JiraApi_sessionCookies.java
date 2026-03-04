import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraApi_sessionCookies {

//	 1. get seesion value from login api 
//   2. create issue using same seesion value SessionFilter 
//   3. using MultiPart attache file to issue

	String value;
	@BeforeTest
	public void ini() {
		RestAssured.baseURI =  "http://localhost:8080";
	}
	
	@Test
	public void  jiralogin() {

//		 1. get seesion value from login api 		
	//	RestAssured.baseURI =  "http://localhost:8080";
		SessionFilter session = new  SessionFilter();
		
		given().log().all().header("Content-Type", "application/json") .body("{ \"username\": \"borkar.adeeti\", \"password\": \"Myindia$1\" }")
		.filter(session).when().post("rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();


//   2. create issue using same seesion value SessionFilter
		// Create new issue, used session filter to get values from response (response contains session {})
		given().log().all().header("Content-Type", "application/json")
			.filter(session)
		.body("{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": {\r\n"
				+ "            \"key\": \"DEMO\"\r\n"
				+ "        },\r\n"
				+ "        \"description\": \"This is my issue 31\",\r\n"
				+ "        \"summary\": \"Issue to add comment\",\r\n"
				+ "        \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}")		
		.when().post("rest/api/2/issue")
		.then().log().all().extract().response().asString();
//   3. using MultiPart attache file to issue		
		
		
	}

}
