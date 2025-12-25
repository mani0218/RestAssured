package rahulShetty;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utilities.ParseJson;
import utilities.PayLoad;

public class GoogleMaps {
	
	static String place_Id;
	
	@Test
	public void AddAPI() throws IOException
	{
		RestAssured.baseURI="https://rahulshettyacademy.com/";
	    String response=
		given()
			.log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body(new String(Files.readAllBytes(Paths.get("src/test/resources/TestData/Add.json"))))
		.when()
			.post("/maps/api/place/add/json")
		.then()
			.assertThat()
			.statusCode(200)
			.body("scope", equalTo("APP"))
			.header("Content-Type", containsString("application/json"))
			.extract()
			.asString();
			System.out.println("Output of Post Method is "+ response);
			JsonPath js=ParseJson.Json(response);
			 place_Id=js.getString("place_id");
	}
	
	@Test
	public void DeleteAPI()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com/";
	    String response=
		given()
			.log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body(PayLoad.DeleteAPI(place_Id))
		.when()
			.delete("/maps/api/place/delete/json")
		.then()
			.assertThat()
			.statusCode(200)
			.body("status", equalTo("OK"))
			.header("Content-Type", containsString("application/json"))
			.extract()
			.asString();
			System.out.println("Output of Post Method is "+ response);
	}
	@Test(enabled=false)
	public void GetAPI()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		given()
		.log().all()
		.queryParam("key", "qaclick123").queryParam("place_id", place_Id).
		when()
		.get("/maps/api/place/get/json").
		then()
		.log().all()
		.body("name", equalTo("Frontline house"));
		
	}
	@Test(enabled=false)
	public void PutAPI()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		
		given()
		.log().all()
		.queryParam("key", "qaclick123")
		.body("{\r\n"
				+ "\"place_id\":\""+place_Id+"\",\r\n"
				+ "\"address\":\"70 Summer walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when()
		.put("/maps/api/place/update/json")
		.then().log().all()
		.assertThat().statusCode(200).extract();
	}
	@Test(enabled=false)
	public void GetChangedAPI()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		String response=
		given()
		.log().all()
		.queryParam("key", "qaclick123").queryParam("place_id", place_Id).
		when()
		.get("/maps/api/place/get/json").
		then()
		.log().all()
		.body("name", equalTo("Frontline house"))
		.extract()
		.response()
		.asString();
		JsonPath actualAddr=ParseJson.Json(response);
		String actualResult=actualAddr.getString("address");
		Assert.assertEquals("29, side layout, cohen 09", actualResult);
		
	}

}
