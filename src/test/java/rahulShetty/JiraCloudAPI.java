package rahulShetty;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utilities.ParseJson;
import utilities.PayLoad;

import static io.restassured.RestAssured.*;

import java.io.*;
public class JiraCloudAPI {
	
	static String IssueKey;
	
	@Test(priority = 1)
	public void CreateIssue()
	{
		RestAssured.baseURI="https://manikandanmk0218.atlassian.net";
		String response=
		given()
		.header("Content-Type","application/json")
		.header("Authorization","Basic bWFuaWthbmRhbm1rMDIxOEBnbWFpbC5jb206QVRBVFQzeEZmR0YwbHQ4WndhQkU4TVR0V1FLNzlFZ2pJZUh6b0R1ejlybEcyQVhkSHBKZmhaTXR1T2NLeUtrdm92WDRGNWdRNVF4THE4eFhHMGlTNTZpbnF2OFhEOGlzUmtLeW01bHpobG5jc0JqT0NNUVQ3MF9qcHYtNFRBaW90WXM5dkpFM0VWTWJkY0VDbnBKVmJKZUtnWEZsT2NnbUkxUUxScWxmQ1Qwb05NZzBCQ2xIcUFJPTkxMTlBRjlG")
		.body(PayLoad.JiraCreateIssue()).when().log().all()
		.post("rest/api/3/issue").asString();
		
		System.out.println(response);
		JsonPath js=ParseJson.Json(response);
		 IssueKey=js.getString("key");
		System.out.println(IssueKey);
	}
	
	@Test(priority = 2)
	public void getIssue()
	{
		RestAssured.baseURI="https://manikandanmk0218.atlassian.net";
		String response=
		given().pathParam("issueKey", IssueKey)
		.header("Content-Type","application/json")
		.header("Authorization","Basic bWFuaWthbmRhbm1rMDIxOEBnbWFpbC5jb206QVRBVFQzeEZmR0YwbHQ4WndhQkU4TVR0V1FLNzlFZ2pJZUh6b0R1ejlybEcyQVhkSHBKZmhaTXR1T2NLeUtrdm92WDRGNWdRNVF4THE4eFhHMGlTNTZpbnF2OFhEOGlzUmtLeW01bHpobG5jc0JqT0NNUVQ3MF9qcHYtNFRBaW90WXM5dkpFM0VWTWJkY0VDbnBKVmJKZUtnWEZsT2NnbUkxUUxScWxmQ1Qwb05NZzBCQ2xIcUFJPTkxMTlBRjlG")
		.when().log().all().get("rest/api/2/issue/{issueKey}").asString();
		System.out.println(response);
	}
	
	@Test
	public void AddAttachmentToIssue()
	{
		RestAssured.baseURI="https://manikandanmk0218.atlassian.net";
		String response=
		given()
		.pathParam("issueKey", "QAJIRA-8")
		.header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic bWFuaWthbmRhbm1rMDIxOEBnbWFpbC5jb206QVRBVFQzeEZmR0YwbHQ4WndhQkU4TVR0V1FLNzlFZ2pJZUh6b0R1ejlybEcyQVhkSHBKZmhaTXR1T2NLeUtrdm92WDRGNWdRNVF4THE4eFhHMGlTNTZpbnF2OFhEOGlzUmtLeW01bHpobG5jc0JqT0NNUVQ3MF9qcHYtNFRBaW90WXM5dkpFM0VWTWJkY0VDbnBKVmJKZUtnWEZsT2NnbUkxUUxScWxmQ1Qwb05NZzBCQ2xIcUFJPTkxMTlBRjlG")
		.multiPart("file", new File("src/test/resources/Screenshot/Screenshot.png"))
		.when()
		.post("https://manikandanmk0218.atlassian.net/rest/api/3/issue/{issueKey}/attachments")
		.then().extract().response().asString();
		
		System.out.println(response);
	}
	
}
