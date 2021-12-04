package main;
import java.awt.Toolkit;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class GUI {
	
	private static Display display;
	
	private Shell shell;
	
	public GUI() {
		initalizeGUI();
		initalizeSimulatorWindow();
		initalizeEditingTextField();
	}
	
	public void initalizeGUI() {
		createDisplay();
		createShell();
	}
	
	public void initalizeSimulatorWindow() {
		
	}
	
	public void initalizeEditingTextField() {
		
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
		if(GUI.display==null) {
			GUI.display = new Display();
		}
	}
	
	public void createShell() {
		this.shell = new Shell(GUI.display);
		this.shell.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize().width/2,Toolkit.getDefaultToolkit().getScreenSize().height/2);
		this.shell.setText("Turing Machine");
		this.shell.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/4,Toolkit.getDefaultToolkit().getScreenSize().height/4);
	}

}
