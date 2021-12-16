package simulation;

import java.util.HashMap;

public class State {

	private String name;
	public State(){
		
	}
	
	public State(String state) {
		this.name = state;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public HashMap<Character, TransitionFunction> getTransitionFunctions() {
		return transitionFunctions;
	}


	public void setTransitionFunctions(HashMap<Character, TransitionFunction> transitionFunctions) {
		this.transitionFunctions = transitionFunctions;
	}


	private HashMap<Character, TransitionFunction> transitionFunctions;
	
	
	public void addTransitionFunction(char input, TransitionFunction function) {
		
		
	}
	
	
	public TransitionFunction geTransitionFunction(char input) {
		
		return null;
	}
}
