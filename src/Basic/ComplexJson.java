package Basic;

import org.testng.Assert;

import file.PayLoadBody;
import io.restassured.path.json.JsonPath;

public class ComplexJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 1. Print No of courses returned by API
		 * 
		 * 2.Print Purchase Amount
		 * 
		 * 3. Print Title of the first course
		 * 
		 * 4. Print All course titles and their respective Prices
		 * 
		 * 5. Print no of copies sold by RPA Course
		 * 
		 * 6. Verify if Sum of all Course prices matches with Purchase Amount
		 */

		JsonPath js = new JsonPath(PayLoadBody.complexPayloadBody());
		// * 1. Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println("Number of cource = " + count);
		// * 2. Print purchase amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase amt is = " + purchaseAmount);

		// * 3. Print Title of the first course
		String courseTitle = js.get("courses[1].title");
		String prices = js.get("courses[1].price").toString();
		System.out.println(courseTitle);
		System.out.println(prices);
		// * 4. Print All course titles and their respective Prices
		System.out.println("Print All course titles and their respective Prices  : " + "\n");
		for (int i = 0; i < count; i++) {

			System.out.println(
					js.get("courses[" + i + "].title") + "  Price:   " + js.get("courses[" + i + "].price").toString());

		}
		//	 * 5. Print no of copies sold by RPA Course
		for (int i = 0; i < count; i++) {
			String courceTitle= js.get("courses[" + i + "].title");
			if (courceTitle.equalsIgnoreCase("RPA"))
			{
				System.out.println("No.of copies for RPA :  "+ js.get("courses["+i+"].copies") );
				break;
			}
		}
	// * 6. Verify if Sum of all Course prices matches with Purchase Amount

		int sumOfPrices= SumValidation.getSumOfPrices();
		Assert.assertEquals(sumOfPrices, purchaseAmount);
		
	}
}
