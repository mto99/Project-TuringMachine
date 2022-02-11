package simulation;

import java.util.ArrayList;
import java.util.LinkedList;


public class Turing {
	
	
	public static State currentState;
	protected static State nextState;
	protected static ArrayList<State> allStates;
	protected static ArrayList<State> acceptingStates;
	public static int head;
	public static char blank;
	protected static ArrayList<TransitionFunction> transitionFunction;
	public static LinkedList<Character> tape = new LinkedList<Character>();
	protected static LinkedList<ArrayList<String[]>> history = new LinkedList<>();
	protected static Boolean finished = false;
	
}