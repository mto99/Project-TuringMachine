package listener.file;

import java.nio.file.Files;
import java.nio.file.Path;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;


import main.FileIO;

public class SaveFileListener extends SelectionAdapter {

	private Shell shell;
	private StyledText textField;

	public SaveFileListener(Shell shell, StyledText textField) {
		this.shell = shell;
		this.textField = textField;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
		String filePath = fileDialog.open();
		if (filePath == null) {
			return;
		}
		if (Files.exists(Path.of(filePath))) {
			MessageBox messageBox = new MessageBox(shell, SWT.YES | SWT.NO | SWT.ICON_WARNING);
			messageBox.setText("Datei �berschreiben?");
			messageBox.setMessage("Die Datei existiert schon. Soll die Datei �berschrieben werden?");
			if (messageBox.open() == SWT.NO) {
				return;
			}
		}
		FileIO.write(textField.getText(), filePath);
	}

}
