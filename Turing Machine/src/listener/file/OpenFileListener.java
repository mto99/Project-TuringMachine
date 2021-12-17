package listener.file;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import main.FileIO;

public class OpenFileListener extends SelectionAdapter {

	private Shell shell;
	private StyledText textField;

	public OpenFileListener(Shell shell, StyledText textField) {
		this.shell = shell;
		this.textField = textField;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
		String filePath = fileDialog.open();
		if(filePath==null) {
			return;
		}
		String text = FileIO.readExternalFile(filePath);
		textField.setText(text);
	}

}
