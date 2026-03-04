package Basic;

import org.testng.annotations.Test;

import file.PayLoadBody;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	// * 6. Verify if Sum of all Course prices matches with Purchase Amount

public static int getSumOfPrices() {
		
		JsonPath js = new JsonPath(PayLoadBody.complexPayloadBody());
		int count = js.getInt("courses.size()");
		int totalPrice=0;
	
		for (int i=0; i < count; i++) {
		int prices = js.getInt("courses["+i+"].price");
		int copies = js.getInt("courses["+i+"].copies");
		int price= prices * copies;
		System.out.println(totalPrice);
		totalPrice= totalPrice+price;
		}
		System.out.println("Sum of the total prices =  " + totalPrice);
		
		return totalPrice;
		
		
		
	}
	
}
