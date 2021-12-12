package main;

import java.io.FileReader;
import java.util.List;

import org.json.simple.*;
import org.json.simple.parser.*;

import simulation.State;


public class Parser {
	
	// Variables
	private String alphabet;
	private List<State> states;
	private State startState;
	private State acceptState;
	private State rejectState;
	private String tape;
	
	
	
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
