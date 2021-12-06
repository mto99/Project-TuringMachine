package main;

import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import listener.OpenFileListener;
import listener.SaveFileListener;

public class GUI {

	private static Display display;

	private Shell shell;

	private MenuItem saveFile;
	private MenuItem openFile;
	
	private MenuItem example1;
	
	private Text editingField;
	
	private MenuItem copyTransitionFunctionTemplate;

	public GUI() {
		initalizeGUI();
		createGUIComponents();
		setListener();
	}

	private void initalizeGUI() {
		createDisplay();
		createShell();
	}

	private void createGUIComponents() {
		createMenuBar();
		createSimulatorWindow();
		createEditingTextField();
	}

	private void setListener() {
		openFile.addSelectionListener(new OpenFileListener(this.shell, this.editingField));
		saveFile.addSelectionListener(new SaveFileListener(this.shell, this.editingField));
	}
	
	public void openGUI() {
		this.shell.pack();
		this.shell.open();

		while (!this.shell.isDisposed()) {
			if (!GUI.display.readAndDispatch())
				GUI.display.sleep();
		}
	}

	public void closeGUI() {
		GUI.display.dispose();
	}

	private void createDisplay() {
		if (GUI.display == null) {
			GUI.display = new Display();
		}
	}

	private void createShell() {
		this.shell = new Shell(GUI.display);

		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

		this.shell.setMinimumSize(screenWidth >> 1, screenHeight >> 1);
		this.shell.setText("Turing Machine");
		this.shell.setLocation(screenWidth >> 2, screenHeight >> 2);
		this.shell.setLayout(new GridLayout(2, true));
	}

	private void createMenuBar() { 
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		createFileMenu(menu);
		createTemplateMenu(menu);
	}	
	
	private void createSimulatorWindow() {
		Label label = new Label(shell, 0);
		// TODO
	}

	private void createEditingTextField() {
		editingField = new Text(shell, SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
		editingField.setText("Testtext");
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		editingField.setLayoutData(layoutData);
	}

	private void createFileMenu(Menu parent) {
		Menu fileDropDown = createMenuDropDown(parent, "&Datei");
		openFile = new MenuItem(fileDropDown, SWT.PUSH);
		openFile.setText("&Öffnen");
		
		Menu examples = createMenuDropDown(fileDropDown, "&Beispiele");
		createExampleDropDown(examples);
		
		saveFile = new MenuItem(fileDropDown, SWT.PUSH);
		saveFile.setText("&Speichern");
	}
	
	private void createTemplateMenu(Menu parent) {
		Menu templateDropDown = createMenuDropDown(parent, "&Templates");
		copyTransitionFunctionTemplate = new MenuItem(templateDropDown, SWT.PUSH);
		copyTransitionFunctionTemplate.setText("&Übergangsfunktion");
	}
	
	private void createExampleDropDown (Menu parent) {
		example1 = new MenuItem(parent, SWT.CASCADE);
		example1.setText("&Beispiel 1");
	}
	
	private Menu createMenuDropDown(Menu parent, String text) {
		MenuItem menu = new MenuItem(parent, SWT.CASCADE);
		menu.setText(text);
		Menu menuDropDown = new Menu(shell, SWT.DROP_DOWN);
		menu.setMenu(menuDropDown);
		return menuDropDown;
	}
	
}
