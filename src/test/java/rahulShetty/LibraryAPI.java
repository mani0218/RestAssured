package rahulShetty;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import io.restassured.path.json.JsonPath;
import utilities.ParseJson;
import utilities.PayLoad;

public class LibraryAPI {
	
	static int count;
	static int PurchaseAmount;
	
	@Test(priority = 1)
	public void NoOfCourses()
	{
		String json=PayLoad.Academy();
//		System.out.println(json);
		JsonPath js=ParseJson.Json(json);
		 count=js.getInt("courses.size()");
		System.out.println("courses count is --> "+count);
		
	}
	@Test(priority = 2)
	public void PurchaseAmount()
	{
		String json=PayLoad.Academy();
		JsonPath js=ParseJson.Json(json);
		 PurchaseAmount=js.getInt("dashboard.purchaseAmount");
		System.out.println("PurchaseAmount is --> "+PurchaseAmount);
		
	}
	@Test(priority = 3)
	public void firstCourseTitle()
	{
		String json=PayLoad.Academy();
//		System.out.println(json);
		JsonPath js=ParseJson.Json(json);
		String Title=js.getString("courses[0].title");
		
		System.out.println("First Course Title is --> "+Title);
		
	}
	
	@Test(priority = 4)
	public void AllCourseTitle()
	{
		System.out.println("All Course Title and prices are");
		String json=PayLoad.Academy();
		JsonPath js=ParseJson.Json(json);
		for(int i=0;i<count;i++)
		{
			String AllTitle=js.getString("courses["+i+"].title");
			int AllPrice=js.getInt("courses["+i+"].price");
			System.out.println(AllTitle+" --> "+ AllPrice);
		}
		
		
		
	}
	@Test(priority = 5)
	public void NoOfcopyByRPA()
	{
		String json=PayLoad.Academy();
//		System.out.println(json);
		JsonPath js=ParseJson.Json(json);
		for(int i=0;i<count;i++)
		{
			String title= js.getString("courses["+i+"].title");
			if(title.equals("RPA"))
			{
				int copies=js.getInt("courses["+i+"].copies");
				System.out.println("RPA copies are  -->"+copies);
			}
			
		}
		
	}
	@Test(priority = 6)
	public void sumOfCourseAmount()
	{
		int sum=0;
		String json=PayLoad.Academy();
		JsonPath js=ParseJson.Json(json);
		for(int i=0;i<count;i++)
		{
			int Price=js.getInt("courses["+i+"].price");
			int copies=js.getInt("courses["+i+"].copies");
			int amount =Price*copies;
			sum=sum+amount;
			
		}
		System.out.println(sum);
		Assert.assertEquals(PurchaseAmount, sum);
	}
	

}
