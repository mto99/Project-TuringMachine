package main;

import java.awt.Toolkit;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

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

	private StyledText editor;
	
	private Label labelSyntaxError;

	private MenuItem copyTransitionFunctionTemplate;
	
	private static final String jsonTemplate = "{"
								+ "\n\t" + '"'+"alphabet"+'"' +" : "+ '"'+"<char>"+'"' +","
								+ "\n\t" + '"'+"states"+'"' +" : " +"[ ]" +","
								+ "\n\t" + '"'+"startState"+'"' +" : "+ '"'+"<statename>"+'"' +","
								+ "\n\t" + '"'+"acceptStates"+'"' +" : "+ '"'+"<statename>"+'"' +","
								+ "\n\t" + '"'+"rejectStates"+'"' +" : "+ '"'+"<statename>"+'"' +","
								
								+ "\n\t" + '"'+"transitionFunction"+'"' +" : "+ "["
									+ "\n\t\t" + "{"
										+ "\n\t\t\t" + '"'+"previousState"+'"' +" : "+ '"'+"<statename>"+'"' +","
										+ "\n\t\t\t" + '"'+"readSymbol"+'"' +" : "+ '"'+"<char>"+'"' +","
										+ "\n\t\t\t" + '"'+"newState"+'"' +" : "+ '"'+"<statename>"+'"' +","
										+ "\n\t\t\t" + '"'+"writtenSymbol"+'"' +" : "+ '"'+"<char>"+'"' +","
										+ "\n\t\t\t" + '"'+"movement"+'"' +" : "+ '"'+"<L / R / N>"+'"'
									+ "\n\t\t" + "} ,"
									+ "\n\t\t" + "{"
										+ "\n\t\t\t" + '"'+"previousState"+'"' +" : "+ '"'+"<statename>"+'"' +","
										+ "\n\t\t\t" + '"'+"readSymbol"+'"' +" : "+ '"'+"<char>"+'"' +","
										+ "\n\t\t\t" + '"'+"newState"+'"' +" : "+ '"'+"<statename>"+'"' +","
										+ "\n\t\t\t" + '"'+"writtenSymbol"+'"' +" : "+ '"'+"<char>"+'"' +","
										+ "\n\t\t\t" + '"'+"movement"+'"' +" : "+ '"'+"<L / R / N>"+'"'
									+ "\n\t\t" + "}"
								+ "\n\t]"
									
								+ "\n\t" + '"'+"tape"+'"' +" : "+ '"'+"<char,... (optional)>"+'"'

								+ "\n}";

	
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
		openFile.addSelectionListener(new OpenFileListener(this.shell, this.editor));
		saveFile.addSelectionListener(new SaveFileListener(this.shell, this.editor));
		example1.addSelectionListener(new ExampleFileListener("/examples/template.json", this.editor));
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
		labelDescription.setText("\nHier you can edit your turing machine");
		
//		editingField = new Text(group, SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
//		editingField.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
//		editingField.setText("Testtext");
		
		editor = new StyledText(group, SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
		editor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		editor.setMargins(5, 5, 5, 5);
		editor.insert(jsonTemplate); //insert json template
		editor.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				String text = editor.getText();
				
				boolean syntaxError = false;
				String errorString = "";
				int marksCount= 0;
				int bracketCount = 0;
				int braceCount = 0;
				
				for (int i=0; i < text.length(); i++) {
					//check for "
					if (text.charAt(i) == '"') {
						marksCount++;
					}
					
					//check for [/]
					if (text.charAt(i) == '[') {
						bracketCount++;
					}
					else if (i == ']'){
						bracketCount--;
					}
					
					//check for {/}
					if (text.charAt(i) == '{') {
						braceCount++;
					}
					else if (i == '}'){
						braceCount--;
					}
				}
				
				//check for errors: "
				if (marksCount%2 != 0) { //error with "
					errorString += ("Syntax error: expected token: " + '"' + " .\n");
					labelSyntaxError.setText(errorString);
					labelSyntaxError.setForeground(new Color(200, 50, 50));
					syntaxError = true;
				}
				else {
					syntaxError = false;
				}
				
				//check for errors: [/]
				if (bracketCount != 0) {
					errorString += ("Syntax error: expected token bracket [ or ]" + " .\n");
					labelSyntaxError.setText(errorString);
					labelSyntaxError.setForeground(new Color(200, 50, 50));
					syntaxError = true;
				}
				else {
					syntaxError = false;
				}
				
				//check for errors: {/}
				if (braceCount != 0) {
					errorString += ("Syntax error: expected token brace { or }" + " .\n");
					labelSyntaxError.setText(errorString);
					labelSyntaxError.setForeground(new Color(200, 50, 50));
					syntaxError = true;
				}
				else {
					syntaxError = false;
				}
				
				
				//check if any errors found, else set default label text
				if (syntaxError == false) {
					labelSyntaxError.setText("No problems");
					labelSyntaxError.setForeground(new Color(50, 150, 50));
				}
				
			}
		});
		
		//for line numbers
		editor.addLineStyleListener(new LineStyleListener() { 
			@Override
			public void lineGetStyle(LineStyleEvent event) {
				// Using ST.BULLET_NUMBER sometimes results in weird alignment.
		        //event.bulletIndex = styledText.getLineAtOffset(event.lineOffset);
		        StyleRange styleRange = new StyleRange();
		        styleRange.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
		        int maxLine = editor.getLineCount();
		        int bulletLength = Integer.toString(maxLine).length();
		        
		        // Width of number character is half the height in monospaced font, add 1 character width for right padding.
		        int bulletWidth = (bulletLength + 1) * editor.getLineHeight() / 2;
		        styleRange.metrics = new GlyphMetrics(0, 0, bulletWidth);
		        event.bullet = new Bullet(ST.BULLET_TEXT, styleRange);
		        // getLineAtOffset() returns a zero-based line index.
		        int bulletLine = editor.getLineAtOffset(event.lineOffset) + 1;
		        event.bullet.text = String.format("%" + bulletLength + "s", bulletLine);
			}
		});
		
		
		//label for description
		labelSyntaxError = new Label(group, 0);
		labelSyntaxError.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		labelSyntaxError.setText("No problems");
		labelSyntaxError.setForeground(new Color(50, 150, 50));
		
	}

	

}
