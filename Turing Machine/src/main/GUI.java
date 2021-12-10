package main;

import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import listener.ExampleFileListener;
import listener.OpenFileListener;
import listener.SaveFileListener;

public class GUI {

	private static Display display;

	private Shell shell;

	private MenuItem newFile;
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
	
	
	//------------------------------
	//Initialiaze GUI
	//------------------------------
	private void initalizeGUI() {
		createDisplay();
		createShell();
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
	

	//------------------------------
	//Create GUI Components
	//------------------------------
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
		this.shell.setLayout(new FillLayout());
	}
	
	
	private void createGUIComponents() {
		createMenuBar();
		createBody();
	}
	
	
	//--------------------------------
	//Create listeners
	//--------------------------------
	private void setListener() {
		openFile.addSelectionListener(new OpenFileListener(this.shell, this.editingField));
		saveFile.addSelectionListener(new SaveFileListener(this.shell, this.editingField));
		example1.addSelectionListener(new ExampleFileListener("/examples/template.json", this.editingField));
	}

	
	//--------------------------------
	//Create menubar
	//--------------------------------
	private void createMenuBar() {
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		createFileMenu(menu);
		createTemplateMenu(menu);
	}
	
	
	private void createFileMenu(Menu parent) {
		Menu fileDropDown = createMenuDropDown(parent, "&Datei");

		newFile = new MenuItem(fileDropDown, SWT.PUSH);
		newFile.setText("&Neu");

		openFile = new MenuItem(fileDropDown, SWT.PUSH);
		openFile.setText("&�ffnen");

		Menu examples = createMenuDropDown(fileDropDown, "&Beispiele");
		createExampleDropDown(examples);

		saveFile = new MenuItem(fileDropDown, SWT.PUSH);
		saveFile.setText("&Speichern");
	}

	
	private void createTemplateMenu(Menu parent) {
		Menu templateDropDown = createMenuDropDown(parent, "&Templates");
		copyTransitionFunctionTemplate = new MenuItem(templateDropDown, SWT.PUSH);
		copyTransitionFunctionTemplate.setText("&�bergangsfunktion");
	}

	
	private void createExampleDropDown(Menu parent) {
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
	
	

	//------------------------------
	//Create body
	//------------------------------
	
	private void createBody() {
		//create composite of simulator and text field
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new GridLayout(2, true));
		composite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		
		//create simulator 
		Group groupSimulator = new Group(composite, SWT.FILL);
		groupSimulator.setLayout(new GridLayout(1,true));
		groupSimulator.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		groupSimulator.setText("Simulator");
		createSimulatorField(groupSimulator);
		
		//create text field
		Group groupTextField = new Group(composite, SWT.FILL);
		groupTextField.setLayout(new GridLayout(1,true));
		groupTextField.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		groupTextField.setText("Text Field");
		createEditingTextField(groupTextField);
	}
	
	
	private void createSimulatorField(Group group) {
		Label label = new Label(group, SWT.NONE);
		label.setText("\nThe turing simulator will appear here! ");
		// TODO
	}

	private void createEditingTextField(Group group) {
		//label for description
		Label labelDescription = new Label(group, 0);
		labelDescription.setText("\nHier you can edit your turing settings");
		
		editingField = new Text(group, SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
		editingField.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		editingField.setText("Testtext");
	}

	

}
