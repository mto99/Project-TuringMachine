package listener.simulator;

import org.eclipse.swt.events.SelectionEvent;

import simulation.TuringMachineSimulator;

public class ResetListener extends SimulatorListener {

	public ResetListener(TuringMachineSimulator simulator) {
		super(simulator);
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		simulator.reset();
		//set GUI
	}
	
}
