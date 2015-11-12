import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherRetriever{

	private static String URL_BASE = "http://api.openweathermap.org/data/2.5/weather?zip=";
	private static String URL_END = "&units=imperial&APPID=e37e1c7200de09a3599684d13dee3bac";
	private static String zip;
	
	public String retrieveData(int zip){
	
		HttpURLConnection con = null;
		InputStream in = null;
		this.zip = Integer.toString(zip);
		
		try{
		
			con = (HttpURLConnection) ( new URL(URL_BASE + this.zip + URL_END)).openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();
			
			StringBuffer buffer = new StringBuffer();
			in = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = null;
			while((line = br.readLine()) != null){
			
				buffer.append(line + "\n");
			
			}
			
			in.close();
			con.disconnect();
			return buffer.toString();
			
		
		} catch(Throwable t){
			
			System.err.println("Unable to open connection.");
			
		}
		
		return null;
	
	}

}
