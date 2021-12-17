package listener.simulator;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import simulation.TuringMachineSimulator;

public class ReloadListener extends SimulatorDrawListener {

	public ReloadListener(TuringMachineSimulator simulator, Button[] tape, Label currentState) {
		super(simulator, tape, currentState);
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		//simulator.reload();
		updateGUI();
	}
	
}
