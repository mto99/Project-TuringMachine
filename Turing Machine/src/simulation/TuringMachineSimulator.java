package simulation;

import java.util.ArrayList;
import java.util.LinkedList;

import org.json.simple.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import main.FileIO;
import main.Parser;


public class TuringMachineSimulator {
	
	
	Parser parser = new Parser();
	JSONObject jsonObject = new JSONObject();
	String text;

	
	public TuringMachineSimulator() throws JsonMappingException, JsonProcessingException {
		
		text = parser.parseAndValidate(FileIO.readExternalFile("Data/newNewReplaceChars.json"));
		//enter inputData to tape
		System.out.println(parser);
		
		Turing.acceptingStates = parser.getAcceptStates();
		
		for( int i=0; i<parser.getTape().length();i++)
		{
			Turing.tape.add(parser.getTape().charAt(i));
		}
		
		Turing.allStates = parser.getAllStates();
		
		
		if (parser.getStartPosition().equals("first")) {
			Turing.head = 0;
		}
		else if (parser.getStartPosition().equals("last")) {
			Turing.head = Turing.tape.size()-1;
		}
		
		
		Turing.currentState = parser.getStartState().toString();
		
		Turing.transitionFunction = parser.getTransitionFunction();
		
		
	}
	

	public void run() {
		
		printTape();
//		while (!Turing.finished) {
//			step();
//		}
		
		step();
		step();
		reset();
		step();
		step();
		stepback();
		stepback();

	}
	
	
	public void step() { // return type could be: void / errorcode/ statuscode
		
		try {
			
			save();
			
			JSONObject currentStateObject = (JSONObject) Turing.transitionFunction.get(Turing.currentState);
			
			if (currentStateObject.isEmpty() || currentStateObject == null) {
				Turing.finished = true;
				
				System.out.println("END");
			}
			
			if(Turing.acceptingStates.contains(Turing.currentState))
			{
				
			}
			
			//get input
			JSONArray currentStateArray = (JSONArray) currentStateObject.get(Turing.tape.get(Turing.head).toString());
			
			
			//check for output
			if (currentStateArray.toArray()[0] == "") {
				//nothing to do
			}
			else { //replace input with output
				char c = (currentStateArray.toArray()[0]).toString().charAt(0);
				Turing.tape.set(Turing.head, c);
			}
			
			
			//movement
			if (currentStateArray.toArray()[1].toString().charAt(0) == 'R') {
				Turing.head++;
				if (Turing.head+1 > Turing.tape.size())
					Turing.tape.addLast(' ');
			}
			else if (currentStateArray.toArray()[1].toString().charAt(0) == 'L') {
				if (Turing.head == 0)
					Turing.tape.addFirst(' ');
				else 
					Turing.head--;
			}
			else if (currentStateArray.toArray()[1].toString().charAt(0) == 'N') {
				//
			}

			
			//get next state
			if (currentStateArray.toArray()[2] == ""){
				//nothing to do
			}else {
				Turing.currentState = (String) currentStateArray.toArray()[2];
			}
				
			printTape();
			
			
		} catch (Exception e) {
			e.getMessage();
		}
		
	}
	
	
	public void stepback() {
		
		try {
			ArrayList<String[]> saving = Turing.history.getLast();
		Turing.tape = new LinkedList<Character>();
		for (var strings : saving.get(0)) {
			Turing.tape.add(strings.charAt(0));
		}
		
		Turing.head = Integer.parseInt(saving.get(1)[0]);
		
		Turing.currentState = saving.get(2)[0];
		
		//delete last entry from history
		Turing.history.removeLast();
		
		printTape();
		
		} catch (Exception e) {
			e.getMessage();
		}
		
		
	}
	
	
	public void save() {
		
		String[] arr = new String[Turing.tape.size()];
		for (int i=0; i < arr.length; i++) {
			arr[i] = Turing.tape.get(i).toString();
		}
		
		String[] headArray = {Integer.toString(Turing.head)};
		
		String[] currentStateArray = {Turing.currentState};
		
		ArrayList<String[]> saving = new ArrayList<>();
		saving.add(arr);
		saving.add(headArray);
		saving.add(currentStateArray);
		
		Turing.history.add(saving);

	}
	
	
	public void printTape() {
		Turing.tape.forEach(item -> System.out.print(item + "\t"));
		System.out.print("\n");
		
		for (int i =0; i<Turing.head; i++) {
			System.out.print("\t");
		}
		
		System.out.println("^");
		
		
		for (int i =0; i<Turing.tape.size(); i++) {
			System.out.print("-------");
		}
		
		System.out.println();
		
	}
	
	
	public void reset() {
		
		
		for( int i=0; i<parser.getTape().length();i++)
		{
			Turing.tape.add(parser.getTape().charAt(i));
		}
		
		ArrayList<State> allStates = parser.getAllStates();
		for (State state: allStates) {
			Turing.allStates.add(state.toString());
		}
		
		
		if (parser.getStartPosition().equals("first")) {
			Turing.head = 0;
		}
		else if (parser.getStartPosition().equals("last")) {
			Turing.head = Turing.tape.size()-1;
		}
		
		
		Turing.currentState = parser.getStartState().toString();
		
		Turing.transitionFunction = parser.getTransitionFunction();
		
		printTape();
	}
	
	
	public void setStartState(State state) {
		
	}
	
	
	public void setAcceptState (State state) {
		
	}
	
	
	public void setRejectState(State state) {
		
	}
	
	
}