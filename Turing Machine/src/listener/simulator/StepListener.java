package listener.simulator;

import org.eclipse.swt.events.SelectionEvent;

import simulation.TuringMachineSimulator;

public class StepListener extends SimulatorListener {

	public StepListener(TuringMachineSimulator simulator) {
		super(simulator);
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		simulator.step();
		//set GUI
	}
	
}
