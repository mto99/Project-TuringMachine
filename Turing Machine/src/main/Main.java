package main;

import simulation.TuringMachineSimulator;

public class Main {

	public static void main(String[] args) {

		// Start GUI
		GUI app = new GUI();
		app.openGUI();
		app.closeGUI();

		TuringMachineSimulator t;
		t = new TuringMachineSimulator();
		t.run();

	}

}