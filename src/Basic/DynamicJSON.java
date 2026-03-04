package Basic;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import file.PayLoadBody;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJSON {
	
	
	@Test(dataProvider = "BooksData",priority =0)
	public void aadBookPOST(String isbn, String aisle) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String addBookResp= given().header("Content-Type", "application/json").body(PayLoadBody.addBook(isbn, aisle))
		.when().post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js= new JsonPath(addBookResp);
		String id= js.get("ID");
		System.out.println(id);
	}
	@Test(dataProvider = "BooksData",priority =1)
	public void getBook(String isbn, String aisle) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String id=PayLoadBody.bookIdToGet(isbn, aisle);
		String getbookResp = given().log().all().queryParam("ID",id ).when().get("/Library/GetBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().toString();
		System.out.println(getbookResp);
		
	}

	@Test(dataProvider = "BooksData",priority = 2)
	public void deleteBook(String isbn, String aisle){
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String deletebookResp= given().log().all().body(PayLoadBody.bookId(isbn, aisle)).when().post("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println("Delete Book Responce /n" +deletebookResp);
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData(){

	return new Object[][]{{"Anvit","2014"},{"Mihika","2016"},{"Gnesh","1958"}};
	}	
	
}
