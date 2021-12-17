package main;

import org.eclipse.swt.widgets.Display;

import simulation.TuringMachineSimulator;

public class RunSimulator implements Runnable {

	private boolean running = false;
	private Display display;
	private TuringMachineSimulator simulator;

	public RunSimulator(Display display, TuringMachineSimulator simulator) {
		this.display = display;
		this.simulator = simulator;
	}

	@Override
	public void run() {
		if (running) {
			simulator.step();
			setGUIState();
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

	public void setGUIState() {
		
	}
	
}
