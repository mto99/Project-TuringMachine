package listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import main.FileIO;

public class OpenFileListener extends SelectionAdapter {

	private Shell shell;
	private Text textField;

	public OpenFileListener(Shell shell, Text textField) {
		this.shell = shell;
		this.textField = textField;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
		String filePath = fileDialog.open();
		String text = FileIO.readExternalFile(filePath);
		textField.setText(text);
	}

}
