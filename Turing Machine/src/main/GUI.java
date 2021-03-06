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

import listener.file.ExampleFileListener;
import listener.file.OpenFileListener;
import listener.file.SaveFileListener;
import listener.file.TemplateListener;
import listener.simulator.PauseListener;
import listener.simulator.ReloadListener;
import listener.simulator.ResetListener;
import listener.simulator.StartListener;
import listener.simulator.StepListener;
import listener.simulator.StepbackListener;
import simulation.TuringMachineSimulator;

public class GUI {

	public static final int TAPE_ELEMENT_NUMBER = 13;

	private static final String[] examplesFilenames = {"template", "newNewReplaceChars", "createBinaryPalindrome", "sameNumberOfSymbols", "incrementBinary", "wordHas2Bs", "Circle-Free-BinaryToggle"};
	private static final String[] examplesTexts = {"&Template", "&Replace Chars", "&Create Binary Palindrome", "&Check if same num of 1s and 0s", "&Increment Binary", "&check if Word has 2 B's", "&Circle Free Binary Toggle"};
	
	private static Display display;

	private Shell shell;

	private MenuItem newFile;
	private MenuItem saveFile;
	private MenuItem openFile;
	
	private MenuItem exampleMenuItems[];

	private Label infoLog;

	private Button stepBack;
	private Button play;
	private Button pause;
	private Button step;
	private Button reset;
	private Button reload;
	
	private StyledText editor;

	private MenuItem copyTransitionFunctionTemplate;

	private Button[] tape;
	private Label currentStateName;

	private TuringMachineSimulator simulator;
	
	private RunSimulator runSimulator;
	
	public GUI() {
		initalizeGUI();
		createGUIComponents();
		simulator = new TuringMachineSimulator();
		runSimulator = new RunSimulator(display, simulator, tape, currentStateName);
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
		this.shell.setLayout(new GridLayout(2, true));
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
		setSimulatorListeners();
	}

	private void createMenuBar() {
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		createFileMenu(menu);
		createTemplateMenu(menu);
	}

	private void setExampleListeners() {

		if(examplesFilenames.length!=examplesTexts.length) {
			return;
		}
		for(int i = 0; i<examplesFilenames.length; i++) {
			exampleMenuItems[i].addSelectionListener(new ExampleFileListener("/examples/"+examplesFilenames[i]+".json", this.editor));
		}
	}

	private void setTemplateListeners() {
		copyTransitionFunctionTemplate.addSelectionListener(new TemplateListener());
	}

	private void setSimulatorListeners() {
		play.addSelectionListener(new StartListener(simulator, runSimulator));
		pause.addSelectionListener(new PauseListener(simulator, runSimulator));
		step.addSelectionListener(new StepListener(simulator, tape, currentStateName));
		stepBack.addSelectionListener(new StepbackListener(simulator, tape, currentStateName));
		reset.addSelectionListener(new ResetListener(simulator, tape, currentStateName));
		reload.addSelectionListener(new ReloadListener(simulator, tape, currentStateName, editor, infoLog, reset, step, play, pause, stepBack));
	}
	
	private void createFileMenu(Menu parent) {
		Menu fileDropDown = createMenuDropDown(parent, "&File");

		newFile = new MenuItem(fileDropDown, SWT.PUSH);
		newFile.setText("&New");

		openFile = new MenuItem(fileDropDown, SWT.PUSH);
		openFile.setText("&Open");

		Menu examples = createMenuDropDown(fileDropDown, "&Examples");
		createExampleDropDown(examples);

		saveFile = new MenuItem(fileDropDown, SWT.PUSH);
		saveFile.setText("&Save");
	}

	private void createTemplateMenu(Menu parent) {
		Menu templateDropDown = createMenuDropDown(parent, "&Templates");
		copyTransitionFunctionTemplate = new MenuItem(templateDropDown, SWT.PUSH);
		copyTransitionFunctionTemplate.setText("&Transition Function");
	}

	private void createExampleDropDown(Menu parent) {
		if(examplesFilenames.length!=examplesTexts.length) {
			return;
		}
		exampleMenuItems = new MenuItem[examplesTexts.length];
		for(int i = 0; i< examplesTexts.length; i++) {
			exampleMenuItems[i] = new MenuItem(parent, SWT.CASCADE);
			exampleMenuItems[i].setText(examplesTexts[i]);
		}
	}

	private Menu createMenuDropDown(Menu parent, String text) {
		MenuItem menu = new MenuItem(parent, SWT.CASCADE);
		menu.setText(text);
		Menu menuDropDown = new Menu(shell, SWT.DROP_DOWN);
		menu.setMenu(menuDropDown);
		return menuDropDown;
	}

	private void createBody() {

		createSimulatorField();

		createEditingTextField(shell);
	}

	private void createSimulatorField() {
		Group groupSimulator = new Group(shell, SWT.FILL);
		groupSimulator.setLayout(new GridLayout(1, true));
		groupSimulator.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		groupSimulator.setText("Simulator");

		currentStateName = new Label(groupSimulator, SWT.CENTER);
		currentStateName.setText("currentState");
		currentStateName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 5, 1));
		currentStateName.setFont(new Font(display, "Arial", 15, SWT.BOLD));

		createTape(groupSimulator);
		
		createSimulatorButtons(groupSimulator);
		
		createInfoLog(groupSimulator);
	}

	private void createInfoLog(Group parent) {
		infoLog = new Label(parent, SWT.FILL);
		infoLog.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
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

				// Width of number character is half the height in monospaced font, add 1
				// character width for right padding.
				int bulletWidth = (bulletLength + 1) * editor.getLineHeight() / 2;
				styleRange.metrics = new GlyphMetrics(0, 0, bulletWidth);
				event.bullet = new Bullet(ST.BULLET_TEXT, styleRange);

				int bulletLine = editor.getLineAtOffset(event.lineOffset) + 1;
				event.bullet.text = String.format("%" + bulletLength + "s", bulletLine);
			}
		});
	}

	private void createTape(Composite simulator) {
		Composite tapeParent = new Composite(simulator, SWT.FILL);
		tapeParent.setLayout(new GridLayout(TAPE_ELEMENT_NUMBER, true));
		tapeParent.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, false));
		tape = new Button[TAPE_ELEMENT_NUMBER];
		createTapeElement(tapeParent);
		tape[TAPE_ELEMENT_NUMBER/2].setBackground(new Color(255, 255, 255));

	}

	private void createSimulatorButtons(Composite simulator) {
		Group functionButtonGroup = new Group(simulator, SWT.FILL);
		functionButtonGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		functionButtonGroup.setLayout(new GridLayout(6, true));
		
		play = new Button(functionButtonGroup, SWT.PUSH);
		play.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, false));
		play.setText("Start");
		step = new Button(functionButtonGroup, SWT.PUSH);
		step.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, false));
		step.setText("Step");
		pause = new Button(functionButtonGroup, SWT.PUSH);
		pause.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, false));
		pause.setText("Pause");
		stepBack = new Button(functionButtonGroup, SWT.PUSH);
		stepBack.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, false));
		stepBack.setText("Stepback");
		reset = new Button(functionButtonGroup, SWT.PUSH);
		reset.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, false));
		reset.setText("Reset");
		reload = new Button(functionButtonGroup, SWT.PUSH);
		reload.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, false));
		reload.setText("Reload");
		
		reset.setEnabled(false);
		play.setEnabled(false);
		step.setEnabled(false);
		pause.setEnabled(false);
		stepBack.setEnabled(false);
	}

	private void createTapeElement(Composite parent) {
		for (int i = 0; i < TAPE_ELEMENT_NUMBER; i++) {
			tape[i] = new Button(parent, SWT.PUSH);
			tape[i].setEnabled(false);
			tape[i].setText("0");
		}
	}

}
