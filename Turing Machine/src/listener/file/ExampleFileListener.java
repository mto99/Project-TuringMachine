package listener.file;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


import main.FileIO;

public class ExampleFileListener extends SelectionAdapter {

	private String filePath;
	private StyledText textField;

	public ExampleFileListener(String filePath, StyledText textField) {
		this.filePath = filePath;
		this.textField = textField;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		String text = FileIO.readExampleFile(filePath);
		textField.setText(text);
		
	}

}
