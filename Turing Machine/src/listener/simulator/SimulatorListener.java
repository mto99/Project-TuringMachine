package listener.simulator;

import org.eclipse.swt.events.SelectionAdapter;

import simulation.TuringMachineSimulator;

public class SimulatorListener extends SelectionAdapter {

	protected TuringMachineSimulator simulator;
	
	public SimulatorListener(TuringMachineSimulator simulator) {
		this.simulator = simulator;
	}
	
}
