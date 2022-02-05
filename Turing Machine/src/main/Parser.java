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
	// TODO Check if blank symbol is in alphabet if it does throw error 
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayNode an;
		Iterator<JsonNode> iterator;
		JsonNode node;
		State state;
		TransitionFunction tf;
		
		JsonNode root = null;
		String s_State = "";
		String tmp = "";
		String erg = "";
		//String cancel = "0";
		String[] jsonKeys;
		String[] jsonKeysArray;
		String[] jsonKeysTransFunc;
		
		

		if(text.isBlank())
		{
			return "EMPTY";
		}
		
		try 
		{
			root = mapper.readTree(text);
			
			jsonKeys = "alphabet,tape,startState,startPosition".split(",");
			
			for(String s: jsonKeys )
			{
				
				if(!root.has(s))
				{
					//cancel = "1";
					System.out.println(s + " MISSING OR WRONG SYNTAX");
				}
				
				node = root.get(s);
				
				
				if(root.get(s).asText().equals("") || root.get(s).asText().equals(" "))
				{	
					//cancel  = "1";
					System.out.println(s + " BLANK OR NO VALUE");
					//System.out.println(root.get(s));
				}
			}
			
			jsonKeysArray = "allStates,rejectStates,acceptStates,transitionFunction".split(",");
			jsonKeysTransFunc = "previousState,readSymbol,newState,writtenSymbol,movement".split(",");
			
			for(String s: jsonKeysArray)
			{
				
				if(!root.has(s))
				{
					System.out.println(s + " MISSING OR WRONG SYNTAX");
					//cancel  = "1";
				}
				else
				{
					an =  (ArrayNode) root.get(s);
					iterator = an.elements();
					while(iterator.hasNext())
					{
						node = iterator.next();
						if(!s.equals("transitionFunction"))
						{
							state = new State(node.asText());
							if(state.toString().equals("") || state.toString().equals(" "))
							{
								//cancel  = "1";
								System.out.println(s + " BLANK OR NO VALUE");
								System.out.println(root.get(s));
							}
						}
						else
						{
							for(String sT: jsonKeysTransFunc)
							{
								if(!node.has(sT))
								{
									System.out.println(sT + " MISSING OR WRONG SYNTAX");
									//cancel  = "1";
								}
								else
								{
									
									state = new State(node.get(sT).asText());
									if(state.toString().equals(""))
									{
										//cancel  = "1";
										System.out.println(sT + " BLANK OR NO VALUE");
										//System.out.println(root.get(s));
									}
								}
							}
						}
					}
				}
			}
		}
		catch (Exception e) {
			
			return "INVALID JSON SYNTAX";
		}
		
		//if(cancel.equals("0"))
		//{
			
		
			//ALPHABET
			if(root.get("alphabet").asText().contains(" "))
			{
				erg += " BLANK Alphabet";
				System.out.println("BLANK Alphabet");
			}
			else
			{
				setAlphabet(root.get("alphabet").asText());	
			}
	
			
			//TAPE
			if(!root.get("tape").asText().equals(""))
			{
				if(root.get("tape").asText().matches("[" + getAlphabet()+"]*"))
				{
					
					System.out.println(root.get("tape").asText());
					System.out.println("matches");
					setTape(root.get("tape").asText());
				}
				else
				{
					erg += "BLANK tape";
					System.out.println("BLANK tape");
				}
			}
			
			
			//ALLSTATES
			setAllStates(new ArrayList<State>());
			an =  (ArrayNode) root.get("allStates");
			
			iterator = an.elements();
			
			while(iterator.hasNext())
			{
				node = iterator.next();
				state = new State(node.asText());
				if(!(state.toString().equals(" ") || state.toString().equals("")))
				{
					allStates.add(state);
				}
			}
			
			
			//STARTSTATE
			s_State = root.get("startState").asText();
		
			
			for(State s: getAllStates() )
			{
				/*System.out.println(s);
				System.out.println(getStartState());*/
				
				if(s.toString().equals(s_State))
				{
					//System.out.println("VALID StartState");
					setStartState(new State(s_State));
					tmp = "";
					break;
				}
				else
				{
					tmp = ", INVALID StartState";
				}
	
			}
			erg += tmp;
				
			
			//STARTPOSITION
			setStartPosition(root.get("startPosition").asText());
			
			
			//REJECTSTATES
			setRejectStates(new ArrayList<State>());
			an =  (ArrayNode) root.get("rejectStates");
			iterator = an.elements();
			
			while(iterator.hasNext())
			{
				node = iterator.next();
				state = new State(node.asText());
				
			
				
				for(State s: getAllStates() )
				{
										
					if(s.toString().equals(state.toString()))
					{
					
						//System.out.println("VALID RejectState");
						rejectStates.add(state);
						tmp="";
						break;
						
					}
					else
					{
						tmp = ", INVALID RejectState";
					}
				}
			}
			erg += tmp;
			
			
			//ACCEPTSTATES
			setAcceptStates(new ArrayList<State>());
			
			an =  (ArrayNode) root.get("acceptStates");
			iterator = an.elements();
			
			while(iterator.hasNext())
			{
				node = iterator.next();
				state = new State(node.asText());
				
					for(State s: getAllStates() )
					{
											
						if(s.toString().equals(state.toString()) )
						{
							//System.out.println("VALID AcceptState");
							acceptStates.add(state);
							tmp="";
							for(State i: getRejectStates())
							{
								if(!(state.toString().equals(i.toString())))
								{
									//System.out.println("VALID AcceptState");
									acceptStates.add(state);
									tmp="";
									//break;
								}
								else
								{
									tmp = ", INVALID AcceptState=RejectState";
									break;
								}
							}
							
						}
						else
						{
							tmp = ", INVALID AcceptState";
						}
					}
				}
			erg += tmp;
		
			
			//TRANSITIONFUNCTIONS
			setTransitionFunction(new ArrayList<TransitionFunction>());
			
			
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
		//}
	
		//System.out.println(toString());		
		System.out.println(erg);
		return erg;
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