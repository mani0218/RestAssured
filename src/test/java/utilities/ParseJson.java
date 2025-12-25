package utilities;

import java.net.ResponseCache;

import io.restassured.path.json.JsonPath;

public class ParseJson {
	
	public static JsonPath Json(String Response)
	{
		JsonPath js=new JsonPath(Response);
		return js;
	}

}
