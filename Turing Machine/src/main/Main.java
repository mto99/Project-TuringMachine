package main;
public class Main {

	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.parseAndValidate("template.json");
		
		//Start GUI
		GUI app = new GUI();
		app.openGUI();
		
		
	}

}