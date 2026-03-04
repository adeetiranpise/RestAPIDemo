import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import file.PayLoadBody;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Practice2_dataPro {

	@Test(dataProvider = "testData")
	public void addBook(String isbn, String aisle) {

		RestAssured.baseURI = "http://216.10.245.166";
		String addBResponse = given().header("Content-Type", "application/json").body(PayLoadBody.addBook(isbn, aisle))
				.when().post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().body()
				.asString();

		JsonPath js = new JsonPath(addBResponse);
		String newID = isbn.concat(aisle);
		String msg = js.get("Msg");
		String id = js.get("ID");
		Assert.assertEquals(msg, "successfully added");
		Assert.assertEquals(id, newID);

		// Delete book

		given().header("ID", newID).body(PayLoadBody.bookId(isbn, aisle)).when().post("/Library/DeleteBook.php").then()
				.log().all().assertThat().statusCode(200).body("msg", equalTo("book is successfully deleted"));

	}

	@DataProvider
	public String[][] testData() {
		String[][] data = { { "Ganesh", "675713" }, { "Test", "223431" } };
		return data;
	}

}
