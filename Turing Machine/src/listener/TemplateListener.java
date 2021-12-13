package listener;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TemplateListener extends SelectionAdapter {

	private static final String TRANSITION_FUNCTION_TEMPLATE="{\r\n"
															+ "	\"previousState\" : \"statename\",\r\n"
															+ "	\"readSymbol\" : \"char\", \r\n"
															+ "	\"newState\" : \"statename\",\r\n"
															+ "	\"writtenSymbol\" : \"char\",\r\n"
															+ "	\"movement\" : \"L | R | N\"\r\n"
															+ "}";
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(new StringSelection(TRANSITION_FUNCTION_TEMPLATE), null);
	}
	
}
