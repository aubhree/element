import java.io.*;
import java.util.*;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * Test driver.
 */
 
public class Driver1{
	
	public static void main(String[] args){
		
		final int zip = 96814; //Sets the zip code to check temp
		final int cycle = 30000; //Controls the lenght of time the temp will refresh
		String data; //Data gathered from webpage stored in here
		WeatherRetriever wr = new WeatherRetriever(); //Object that opens webpage and retrieves JSON data
		WeatherParser weatherObj = new WeatherParser(); //Object to parse JSON data
		Weather weather = new Weather(); //Object to hold temperature and humidity data
		
		while(true){
			
			data = wr.retrieveData(zip);
			try {
				
				//Gathers weather data and parses it
				weather = weatherObj.getWeather(data);	
			
				//Prints to screen the weather
				System.out.println("Today's temperature is: " + weather.getTemp() + 
				" Degrees Fahrenheit @ zip: " + zip);
			
			} catch (JSONException e){
			
				System.err.println ("Invalid entry");
			
			}
		
			try {
			
				Thread.sleep(cycle);           //Sleeps program for x amount of miliseconds
			
			} catch(InterruptedException ex) {
			
				Thread.currentThread().interrupt();
			
			}
			
			
		}
		
		
	}

}