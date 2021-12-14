package main;

import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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
import listener.TemplateListener;


public class GUI {

	private static Display display;

	private Shell shell;

	private MenuItem newFile;
	private MenuItem saveFile;
	private MenuItem openFile;

	private MenuItem example1;

	private Label infoLog;
	
	private StyledText editor;
	
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
		this.shell.setLayout(new GridLayout(2,true));
	}
	
	private void createGUIComponents() {
		createMenuBar();
		createBody();
	}
	
	private void setListener() {
		openFile.addSelectionListener(new OpenFileListener(this.shell, this.editor));
		saveFile.addSelectionListener(new SaveFileListener(this.shell, this.editor));
		newFile.addSelectionListener(new ExampleFileListener("/examples/newFile.json", this.editor));
		setExampleListeners();
		setTemplateListeners();
	}
	
	private void createMenuBar() {
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		createFileMenu(menu);
		createTemplateMenu(menu);
	}
	
	private void setExampleListeners() {
		example1.addSelectionListener(new ExampleFileListener("/examples/template.json", this.editor));
	}
	
	private void setTemplateListeners() {
		copyTransitionFunctionTemplate.addSelectionListener(new TemplateListener());
	}
	
	private void createFileMenu(Menu parent) {
		Menu fileDropDown = createMenuDropDown(parent, "&Datei");

		newFile = new MenuItem(fileDropDown, SWT.PUSH);
		newFile.setText("&Neu");

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

	private void createBody() {
		Group groupSimulator = new Group(shell, SWT.FILL);
		groupSimulator.setLayout(new GridLayout(1,true));
		groupSimulator.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		groupSimulator.setText("Simulator");
		
		createSimulatorField(groupSimulator);
		createInfoLog(groupSimulator);
		
		createEditingTextField(shell);
	}
	
	private void createSimulatorField(Group parent) {
		Composite simulator = new Composite(parent, SWT.FILL);
		simulator.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		simulator.setLayout(new GridLayout(5, true));
		
		Label currentStateName = new Label(simulator, SWT.CENTER);
		currentStateName.setText("start");
		currentStateName.setLayoutData(new GridData(SWT.CENTER, SWT.BEGINNING, false, false, 5, 1));
		currentStateName.setFont(new Font(display, "Arial", 15, SWT.BOLD));
		
		createTape(simulator);
		createSimulatorButtons(simulator);
	}
	
	private void createInfoLog(Group parent) {
		infoLog = new Label(parent, SWT.FILL);
		infoLog.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
		infoLog.setText("Text");
	}

	private void createEditingTextField(Shell parent) {		
		
		editor = new StyledText(parent, SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
		editor.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		editor.setMargins(5, 5, 5, 5);
			
		editor.addLineStyleListener(new LineStyleListener() { 
			@Override
			public void lineGetStyle(LineStyleEvent event) {
		        StyleRange styleRange = new StyleRange();
		        styleRange.foreground = Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
		        int maxLine = editor.getLineCount();
		        int bulletLength = Integer.toString(maxLine).length();
		        
		        // Width of number character is half the height in monospaced font, add 1 character width for right padding.
		        int bulletWidth = (bulletLength + 1) * editor.getLineHeight() / 2;
		        styleRange.metrics = new GlyphMetrics(0, 0, bulletWidth);
		        event.bullet = new Bullet(ST.BULLET_TEXT, styleRange);

		        int bulletLine = editor.getLineAtOffset(event.lineOffset) + 1;
		        event.bullet.text = String.format("%" + bulletLength + "s", bulletLine);
			}
		});	
	}
	
	private void createTape(Composite simulator) {
		Button tape1 = new Button(simulator, SWT.PUSH);
		tape1.setEnabled(false);
		tape1.setText("0");
		Button tape2 = new Button(simulator, SWT.PUSH);
		tape2.setEnabled(false);
		tape2.setText("0");
		Button tape3 = new Button(simulator, SWT.PUSH);
		tape3.setEnabled(false);
		tape3.setText("0");
		tape3.setBackground(new Color(255, 255, 255));
		Button tape4 = new Button(simulator, SWT.PUSH);
		tape4.setEnabled(false);
		tape4.setText("0");
		Button tape5 = new Button(simulator, SWT.PUSH);
		tape5.setEnabled(false);
		tape5.setText("0");
	}

	private void createSimulatorButtons(Composite simulator) {
		Button compile = new Button(simulator, SWT.PUSH);
		compile.setText("Kompilieren");
		Button play = new Button(simulator, SWT.PUSH);
		play.setText("Start");
		Button pause = new Button(simulator, SWT.PUSH);
		pause.setText("Pause");
		Button step = new Button(simulator, SWT.PUSH);
		step.setText("Step");
		Button reset = new Button(simulator, SWT.PUSH);
		reset.setText("Zurücksetzen");
	}
	
}
