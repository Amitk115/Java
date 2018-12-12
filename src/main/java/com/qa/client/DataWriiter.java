package com.qa.client;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;

public class DataWriiter {



	public void writeData(HashMap<String, String> headers) {
		String fileName="Output.txt";
		try {
			PrintWriter outputStream = new PrintWriter(fileName);
			outputStream.println(headers);
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}

}
