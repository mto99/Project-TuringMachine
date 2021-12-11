package main;

import java.io.FileReader;
import org.json.simple.*;
import org.json.simple.parser.*;


public class Parser {
	
	// Variables
	
	
	public JSONObject parseAndValidate(String filename) {
		
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader(filename));
			JSONObject jsonObject = (JSONObject)obj;			
			return jsonObject;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		
		
		
	}
}
