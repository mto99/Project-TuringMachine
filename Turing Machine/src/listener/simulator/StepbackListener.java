package listener.simulator;

import org.eclipse.swt.events.SelectionEvent;

import simulation.TuringMachineSimulator;

public class StepbackListener extends SimulatorListener {

	public StepbackListener(TuringMachineSimulator simulator) {
		super(simulator);
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		simulator.stepback();
		//set GUI
	}
	
}
