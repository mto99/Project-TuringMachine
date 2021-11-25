import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


public class GUI {
	
	private static Display display;
	private Shell shell;
	
	
	// Constructor
	public GUI() {
		
	}
	
	
	// Methods
	
	public void intializeGUI() {
		createDisplay();
		createShell();
		
		shell.pack();
		shell.open();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) 
				display.sleep();
		}
		
		display.dispose();
		
	}
	
	
	//create Display
	public void createDisplay() {
		display = new Display();
	}
	
	
	//create Shell
	public void createShell() {
		shell = new Shell(display);
		shell.setMinimumSize(500,300);
		shell.setText("Turing Machine");
		shell.setLocation(500,200);
		
	}
	

}
