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
		
		reset();
	}
	

	public void run() {
		
		printTape();
//		while (!Turing.finished) {
//			step();
//		}
		
		step();
		step();
		step();
		step();
		step();
		step();
		step();
		step();
		reset();
		//step();
		//step();
		//stepback();
		//stepback();

	}
	
	
	public void step() { // return type could be: void / errorcode/ statuscode
		
		try {
			
			//save();
			
			for(State state: Turing.acceptingStates)
			{
				if(state.toString().equals(Turing.currentState.toString()))
				{
					Turing.finished = true;
					
					System.out.println("END");
					return;
				}
			}
			
			//get input
			char input = Turing.tape.get(Turing.head);
			
			for(TransitionFunction tf: parser.getTransitionFunction())
			{
				//System.out.println(Turing.currentState);
				//System.out.println(tf.getPreviousState());
				//System.out.println(Turing.currentState);
				if(tf.getPreviousState().toString().equals(Turing.currentState.toString()) && 
						tf.getReadSymbol() == input)
				{
					//check for output
					if(tf.getWrittenSymbol() != ' ')
					{
						Turing.tape.set(Turing.head, tf.getWrittenSymbol());
					}
					
					//movement
					if (tf.getMovement() == 'R') {
						Turing.head++;
						if (Turing.head+1 > Turing.tape.size())
							Turing.tape.addLast(' ');
					}
					else if (tf.getMovement() == 'L') {
						if (Turing.head == 0)
							Turing.tape.addFirst(' ');
						else 
							Turing.head--;
					}
					else if (tf.getMovement() ==  'N') {
						//
					}
					
					//get next state
					
					if (tf.getNewState().toString() == " "){
						//nothing to do
					}else {
						Turing.currentState = tf.getNewState();
					}
					break;
				}
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
		
		Turing.currentState = new State(saving.get(2)[0]); 
		
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
		
		String[] currentStateArray = {Turing.currentState.toString()};
		
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
		
		
		Turing.acceptingStates = parser.getAcceptStates();
		
		Turing.tape.clear();
		
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
		
		
		Turing.currentState = parser.getStartState();
		
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