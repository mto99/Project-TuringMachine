package simulation;

import java.util.ArrayList;
import java.util.LinkedList;


public class Turing {
	
	
	protected static State currentState;
	protected static State nextState;
	protected static ArrayList<State> allStates;
	protected static ArrayList<State> acceptingStates;
	protected static int head;
	protected static ArrayList<TransitionFunction> transitionFunction;
	protected static LinkedList<Character> tape = new LinkedList<Character>();
	protected static LinkedList<ArrayList<String[]>> history = new LinkedList<>();
	protected static Boolean finished = false;
}