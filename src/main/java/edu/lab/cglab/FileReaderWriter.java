package edu.lab.cglab;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderWriter {

	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(getPath(fileName)));
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getPath(String fileName) {
		return FileReaderWriter.class.getClassLoader().getResource(fileName).getPath().replaceAll("%20", " ");
	}
}
