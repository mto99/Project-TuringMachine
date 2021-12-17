package listener.simulator;

import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import main.GUI;
import simulation.Turing;
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
		currentState.setText(Turing.currentState.getName());
		updateTape();
	}

	private void updateTape() {
		List<Character> tape =Turing.tape;
		int head = Turing.head;
		char blank = Turing.blank;
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
