package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import simulation.TuringMachineSimulator;

public class Main {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		
		//Start GUI
		GUI app = new GUI();
		app.openGUI();
		app.closeGUI();
		
		TuringMachineSimulator t = new TuringMachineSimulator();
		t.run();
		
	}

}