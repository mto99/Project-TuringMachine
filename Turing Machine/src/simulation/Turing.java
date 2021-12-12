package simulation;

import java.util.ArrayList;
import java.util.LinkedList;

import org.json.simple.JSONObject;

public class Turing {
	
	protected static String currentState;
	protected static String nextState;
	protected static ArrayList<String> allStates = new ArrayList<String>();
	protected static int head;
	protected static JSONObject transitionFunction;
	protected static LinkedList<Character> tape = new LinkedList<Character>();
	protected static ArrayList<String[]> history = new ArrayList<String[]>();
	protected static Boolean finished = false;
}