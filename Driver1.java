import java.io.*;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * Test driver.
 */
 
public class Driver1{
	
	public static void main(String[] args){
		
		int zip = 96814;
		String data;
		data = ((new WeatherRetriever()).retrieveData(zip));
		Weather weather = new Weather();
		WeatherParser weatherObj = new WeatherParser();
		
		try {
			
			weather = weatherObj.getWeather(data);	
			
		} catch (JSONException e){
			System.err.println ("Invalid entry");
		}
		
		System.out.println("Today's temperature is: " + weather.getTemp() + " Degrees Fahrenheit");
	
	}

}
