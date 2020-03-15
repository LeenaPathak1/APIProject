package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	static String inputFilepath;
	public static RequestSpecification req;
	
	public RequestSpecification reqSpec() throws IOException{
		
		if(req==null)
		{
		
		 req=new RequestSpecBuilder().setBaseUri(getValue("BaseURI")).addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		 return req;
		}
		return req;
		
	}
	
	
	public  static String getValue(String key) throws IOException{
		
		FileInputStream fp = new FileInputStream(inputFilepath);
		Properties p = new Properties();
		p.load(fp);
		return p.getProperty(key);
		
	}
	
	public String getRespValue(Response response,String key){
		
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();
		
		
	}
	
	@BeforeSuite
	@Parameters({"envFile"})
	public static void getFilePath(String envFile){
		
		inputFilepath= envFile;
		
	}

}
