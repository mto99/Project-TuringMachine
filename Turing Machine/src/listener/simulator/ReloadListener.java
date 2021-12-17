package listener.simulator;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import simulation.TuringMachineSimulator;

public class ReloadListener extends SimulatorDrawListener {

	private StyledText editingField;

	public ReloadListener(TuringMachineSimulator simulator, Button[] tape, Label currentState,
			StyledText editingField) {
		super(simulator, tape, currentState);
		this.editingField = editingField;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		simulator.reload(editingField.getText());
		simulator.reset();
		updateGUI();
	}

}
