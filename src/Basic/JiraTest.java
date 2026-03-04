package Basic;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import java.io.File;
import org.testng.Assert;

public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://localhost:8080/";

		// Create issue
		SessionFilter session = new SessionFilter();
		given().header("Content-Type", "application/json")
				.body("{ \"username\": \"borkar.adeeti\", \"password\": \"Myindia$1\" }").filter(session).when()
				.post("rest/auth/1/session").then().log().all().assertThat().statusCode(200).extract().asString();
		/*
		 * JsonPath js = new JsonPath(createIsuue); String name =
		 * js.get("session.name"); String value = js.get("session.value"); String cookei
		 * = name + value;
		 */

		// Update comment
		String expectedMessage = "This comment is updated using rest api. USING script21548";

		String commentMessage = given().log().all().pathParam("id", "10005").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"body\": \"" + expectedMessage + "\",\r\n" + "    \"visibility\": {\r\n"
						+ "        \"type\": \"role\",\r\n" + "        \"value\": \"Administrators\"\r\n" + "    }\r\n"
						+ "}")
				.filter(session).when().post("rest/api/2/issue/{id}/comment").then().log().all().assertThat()
				.statusCode(201).extract().asString();
		JsonPath js = new JsonPath(commentMessage);
		String commentID = js.getString("id");

		// Add attachment
		System.out.println("********************************************************************");
		given().log().all().header("X-Atlassian-Token", "no-check").pathParam("id", "10005").filter(session)
				.header("Content-Type", "multipart/form-data").multiPart("file", new File("src\\file\\testFile.txt"))
				.when().post("rest/api/2/issue/{id}/attachments").then().log().all().assertThat().statusCode(200)
				.extract().asString();
		// Get method-> get perticular field
		System.out.println("********************************************************************");
		String getIssueDetails = given().filter(session).pathParam("id", "10005").queryParam("fields", "comment").when()
				.get("rest/api/2/issue/{id}").then().log().all().assertThat().statusCode(200).extract().response()
				.asString();
		// System.out.println(getIssue);

		JsonPath js1 = new JsonPath(getIssueDetails);

		int no_comment = js1.getInt("fields.comment.comments.size()");
		System.out.println(no_comment);
//Validate if expected comment matches with get out put
		for (int i = 0; i < no_comment; i++) {

			String commentIdIssue = js1.get("fields.comment.comments[" + i + "].id").toString();
			System.out.println(commentIdIssue);
			if (commentIdIssue.equalsIgnoreCase(commentID)) {

				String issueMessage = js1.get("fields.comment.comments[" + i + "].body").toString();
				System.out.println(issueMessage);
				Assert.assertEquals(issueMessage, expectedMessage);
			}

		}

	}

}
