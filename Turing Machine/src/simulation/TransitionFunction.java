package simulation;

public class TransitionFunction {

	private State previousState;
	private char readSymbol;
	private State newState;
	private char writtenSymbol;
	private char movement;
	
	public TransitionFunction(State previousState, char readSymbol, State newState, char writtenSymbol, char movement) {
		super();
		this.previousState = previousState;
		this.readSymbol = readSymbol;
		this.newState = newState;
		this.writtenSymbol = writtenSymbol;
		this.movement = movement;
	}	
	
	public TransitionFunction() {
		super();
	}

	@Override
	public String toString(){
		String out = "\n";
		out += " prev state is " +  getPreviousState() + "\n";
		out += " ready symbol is " +  getReadSymbol() + "\n";
		out += " new state is " +  getNewState() + "\n";
		out += " wrietten symbol is " +  getWrittenSymbol() + "\n";
		out += " movement is " +  getMovement() + "\n";
		out += "***";
		
		return out;
	}

	public State getPreviousState() {
		return previousState;
	}

	public void setPreviousState(State previousState) {
		this.previousState = previousState;
	}

	public char getReadSymbol() {
		return readSymbol;
	}

	public void setReadSymbol(char readSymbol) {
		this.readSymbol = readSymbol;
	}

	public State getNewState() {
		return newState;
	}

	public void setNewState(State newState) {
		this.newState = newState;
	}

	public char getWrittenSymbol() {
		return writtenSymbol;
	}

	public void setWrittenSymbol(char writtenSymbol) {
		this.writtenSymbol = writtenSymbol;
	}

	public char getMovement() {
		return movement;
	}

	public void setMovement(char movement) {
		this.movement = movement;
	}
	
	
}
