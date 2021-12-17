package listener.simulator;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import simulation.TuringMachineSimulator;

public class StepListener extends SimulatorDrawListener {

	public StepListener(TuringMachineSimulator simulator, Button[] tape, Label currentState) {
		super(simulator, tape, currentState);
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		simulator.step();
		updateGUI();
	}
	
}
