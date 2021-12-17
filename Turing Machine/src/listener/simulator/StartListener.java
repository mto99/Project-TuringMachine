package listener.simulator;

import org.eclipse.swt.events.SelectionEvent;

import main.RunSimulator;
import simulation.TuringMachineSimulator;

public class StartListener extends SimulatorListener {

	RunSimulator runSimulator;

	public StartListener(TuringMachineSimulator simulator, RunSimulator runSimulator) {
		super(simulator);
		this.runSimulator = runSimulator;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		this.runSimulator.start();
	}

}
