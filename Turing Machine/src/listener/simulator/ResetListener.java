package listener.simulator;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import simulation.TuringMachineSimulator;

public class ResetListener extends SimulatorDrawListener {

	public ResetListener(TuringMachineSimulator simulator, Button[] tape, Label currentState) {
		super(simulator, tape, currentState);
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		simulator.reset();
		updateGUI();
	}
	
}
