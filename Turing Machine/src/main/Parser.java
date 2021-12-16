package main;
import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import simulation.State;
import simulation.TransitionFunction;


public class Parser {
	
	// Variables
	private String alphabet;
	private ArrayList<State> allStates;
	private State startState;
	private ArrayList<State> acceptStates;
	private ArrayList<State> rejectStates;
	private ArrayList<TransitionFunction> transitionFunction;
	private String tape;
	private String startPosition;


	public String parseAndValidate(String text) throws JsonMappingException, JsonProcessingException {
	
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode root = mapper.readTree(text);
		
		setAlphabet(root.get("alphabet").asText());
		
		
		setAllStates(new ArrayList<State>());
		ArrayNode an =  (ArrayNode) root.get("allStates");
		Iterator<JsonNode> iterator = an.elements();
		
		JsonNode node;
		State state;
		
		while(iterator.hasNext())
		{
			node = iterator.next();
			state = new State(node.asText());
			allStates.add(state);
		}
		
		
		setStartState(new State(root.get("startState").asText()));
		
		setRejectStates(new ArrayList<State>());
		an =  (ArrayNode) root.get("rejectStates");
		iterator = an.elements();
		
		while(iterator.hasNext())
		{
			node = iterator.next();
			state = new State(node.asText());
			rejectStates.add(state);
		}
		
		
		setAcceptStates(new ArrayList<State>());
		an =  (ArrayNode) root.get("acceptStates");
		iterator = an.elements();
		
		while(iterator.hasNext())
		{
			node = iterator.next();
			state = new State(node.asText());
			acceptStates.add(state);
		}
		
		setTransitionFunction(new ArrayList<TransitionFunction>());
		
		TransitionFunction tf;
		an = (ArrayNode) root.get("transitionFunction");
		iterator = an.elements();
		
		
		while(iterator.hasNext())
		{
			node = iterator.next();
			
			tf = new TransitionFunction(new State(node.get("previousState").asText()),  
										node.get("readSymbol").asText().charAt(0),
										new State(node.get("newState").asText()),
										node.get("writtenSymbol").asText().charAt(0),
										node.get("movement").asText().charAt(0));
			
			this.transitionFunction.add(tf);
			
		}
		
		setTape(root.get("tape").asText());
		
		setStartPosition(root.get("startPosition").asText());
		
		return null;
	}
	
	@Override
	public String toString(){
		String out = "\n";
		out += " alphabet: " +  getAlphabet() + "\n";
		out += " tape: " +  getTape() + "\n";
		out += " startState:" +  getStartState() + "\n";
		out += " startPosition: " +  getStartPosition() + "\n";
		out += " allStates: " + getAllStates() + "\n";
		out += " rejectStates: " +  getRejectStates() + "\n";
		out += " acceptStates: " +  getAcceptStates() + "\n";
		out += " transitionFunction:" +  getTransitionFunction();
		
		return out;
	}
	
	public String getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}

	public ArrayList<State> getAllStates() {
		return allStates;
	}

	public void setAllStates(ArrayList<State> states) {
		this.allStates = states;
	}

	public State getStartState() {
		return startState;
	}

	public void setStartState(State startState) {
		this.startState = startState;
	}

	public ArrayList<State> getAcceptStates() {
		return acceptStates;
	}

	public void setAcceptStates(ArrayList<State> acceptStates) {
		this.acceptStates = acceptStates;
	}

	public ArrayList<State> getRejectStates() {
		return rejectStates;
	}

	public void setRejectStates(ArrayList<State> rejectStates) {
		this.rejectStates = rejectStates;
	}

	public ArrayList<TransitionFunction> getTransitionFunction() {
		return transitionFunction;
	}
	public void setTransitionFunction(ArrayList<TransitionFunction> transitionFunction) {
		this.transitionFunction = transitionFunction;
	}
	public String getTape() {
		return tape;
	}

	public void setTape(String tape) {
		this.tape = tape;
	}
	public String getStartPosition() {
		return startPosition;
	}
	public void setStartPosition(String startPosition) {
		this.startPosition = startPosition;
	}

}
