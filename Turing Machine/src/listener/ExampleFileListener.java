package listener;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import main.FileIO;

public class ExampleFileListener extends SelectionAdapter {

	private String filePath;
	private Text textField;

	public ExampleFileListener(String filePath, Text textField) {
		this.filePath = filePath;
		this.textField = textField;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		String text = FileIO.readExampleFile(filePath);
		textField.setText(text);
	}

}
