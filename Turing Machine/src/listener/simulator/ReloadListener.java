package listener.simulator;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import simulation.TuringMachineSimulator;

public class ReloadListener extends SimulatorDrawListener {

	private StyledText editingField;
	private Label infoLog;
	private Button reset;
	private Button play;
	private Button step;
	private Button pause;
	private Button stepBack;
	
	
	public ReloadListener(TuringMachineSimulator simulator, Button[] tape, Label currentState,
			StyledText editingField, Label infoLog, Button reset, Button step, Button play, Button pause, Button stepBack) {
		super(simulator, tape, currentState);
		this.editingField = editingField;
		this.reset = reset;
		this.step = step;
		this.stepBack = stepBack;
		this.play = play;
		this.pause=pause;
		this.infoLog = infoLog;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		String erg = simulator.reload(editingField.getText());
		if(erg.equals("")) {
			infoLog.setText("Text erfolgreich geparsed");
			simulator.reset();
			reset.setEnabled(true);
			play.setEnabled(true);
			step.setEnabled(true);
			pause.setEnabled(true);
			stepBack.setEnabled(true);
			updateGUI();
		}else {
			infoLog.setText(erg);
			reset.setEnabled(false);
			play.setEnabled(false);
			step.setEnabled(false);
			pause.setEnabled(false);
			stepBack.setEnabled(false);
			return;
		}
	}

}
