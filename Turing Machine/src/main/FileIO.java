package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileIO {
	static String fileName;

	public static String readExampleFile(String filename) {
		InputStream iStream = FileIO.class.getResourceAsStream(filename);
		return read(iStream);
	}

	public static String readExternalFile(String filePath) {
		try {
			InputStream iStream = new FileInputStream(filePath);
			return read(iStream);
		} catch (Exception e) {
			return null;
		}
	}

	public static String read(InputStream iStream) {
		String lines = "";
		Reader reader = new InputStreamReader(iStream);
		BufferedReader bReader = new BufferedReader(reader);
		String line = "";
		try {
			while ((line = bReader.readLine()) != null) {
				lines = lines.concat(line).concat(System.lineSeparator());
			}
			bReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public static void write(String text, String filePath) {
		try {
			FileWriter fileWriter = new FileWriter(filePath);
			fileWriter.write(text);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
