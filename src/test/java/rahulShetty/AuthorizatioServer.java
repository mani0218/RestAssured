package rahulShetty;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utilities.ParseJson;

import static io.restassured.RestAssured.*;
public class AuthorizatioServer {
	
	static String accessToken;
	
	@Test(priority = 1)
	public void getToken()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com/";
	String response =
		given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when()
		.post("oauthapi/oauth2/resourceOwner/token")
		.asString();
		System.out.println(response);
		JsonPath js=ParseJson.Json(response);
		accessToken=js.getString("access_token");
		System.out.println(accessToken);
		
	}
	@Test(priority = 2)
	public void getCourseDetails()
	{
		RestAssured.baseURI="https://rahulshettyacademy.com/";
		String response=
		given()
		.queryParam("access_token", accessToken)
		.header("Content-Type","application/json")
		.get("oauthapi/getCourseDetails")
		.asString();
		
		System.out.println(response);
	}

}
