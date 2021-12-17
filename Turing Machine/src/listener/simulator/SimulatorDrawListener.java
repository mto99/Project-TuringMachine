package listener.simulator;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import main.GUI;
import simulation.TuringMachineSimulator;

public class SimulatorDrawListener extends SimulatorListener {

	protected Button[] tape;
	protected Label currentState;

	public SimulatorDrawListener(TuringMachineSimulator simulator, Button[] tape, Label currentState) {
		super(simulator);
		this.tape = tape;
		this.currentState = currentState;
	}
	
	protected void updateGUI() {
		//TODO currentState.setText(simulator.getCurrentState().getName());
		currentState.setText("newState");
		updateTape();
	}

	private void updateTape() {
		//TODO List<Character> tape = simulator.getTape().getName();
		ArrayList<Character> tape = new ArrayList<Character>() ;
		tape.add('0');
		tape.add('1');
		tape.add('0');
		tape.add('1');
		tape.add('0');
		tape.add('1');
		tape.add('1');
		//TODO  int head = simulator.getHead();
		int head = 5;
		// TODO char blankChar = simulator.getBlankChar();
		char blank = ' ';
		for(int i=0; i<GUI.TAPE_ELEMENT_NUMBER; i++) {
			try {
				Character currentTapeValue = tape.get(i+head-GUI.TAPE_ELEMENT_NUMBER/2);
				this.tape[i].setText(""+currentTapeValue);
			}catch(IndexOutOfBoundsException e) {
				this.tape[i].setText(""+blank);
			}
		}
	}
}
