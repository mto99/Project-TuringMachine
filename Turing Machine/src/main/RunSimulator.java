package main;

import java.util.List;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

import simulation.Turing;
import simulation.TuringMachineSimulator;

public class RunSimulator implements Runnable {

	private boolean running = false;
	private Display display;
	private TuringMachineSimulator simulator;
	private Button[] tape;
	private Label currentState;

	public RunSimulator(Display display, TuringMachineSimulator simulator, Button[] tape, Label currentState) {
		this.display = display;
		this.simulator = simulator;
		this.tape = tape;
		this.currentState = currentState;
	}

	@Override
	public void run() {
		if (running) {
			simulator.step();
			updateGUI();
			display.timerExec(2000, this);
		}
	}

	public void stop() {
		this.running = false;
	}

	public void start() {
		this.running = true;
		display.timerExec(750, this);
	}

	public void updateGUI() {
		if(Turing.currentState==null)
			return;
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
