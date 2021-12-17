package main;

import java.util.ArrayList;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

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
		display.timerExec(2000, this);
	}

	public void updateGUI() {
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
