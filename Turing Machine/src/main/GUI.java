package main;

import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class GUI {

	private static Display display;

	private Shell shell;

	public Text editingField;

	public GUI() {
		initalizeGUI();
		createGUIComponents();

	}

	public void initalizeGUI() {
		createDisplay();
		createShell();
	}

	public void createGUIComponents() {
		createMenuBar();
		createSimulatorWindow();
		createEditingTextField();
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

	public void createDisplay() {
		if (GUI.display == null) {
			GUI.display = new Display();
		}
	}

	public void createShell() {
		this.shell = new Shell(GUI.display);

		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

		this.shell.setMinimumSize(screenWidth >> 1, screenHeight >> 1);
		this.shell.setText("Turing Machine");
		this.shell.setLocation(screenWidth >> 2, screenHeight >> 2);
		this.shell.setLayout(new GridLayout(2, true));
	}

	public void createMenuBar() {
		
	}	
	
	public void createSimulatorWindow() {
		Label label = new Label(shell, 0);
		// TODO
	}

	public void createEditingTextField() {
		editingField = new Text(shell, SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
		editingField.setText("Test");
		GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		editingField.setLayoutData(layoutData);
	}

}
