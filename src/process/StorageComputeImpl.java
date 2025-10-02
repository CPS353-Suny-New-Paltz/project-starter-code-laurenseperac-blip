package process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StorageComputeImpl implements StorageComputeAPI {

	@Override
	public DataValue readInput(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line = reader.readLine();
			int value = Integer.parseInt(line.trim());
			return new DataValueImpl(value);
		} catch (IOException | NumberFormatException e) {
			throw new RuntimeException("Error reading input file: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean writeOutput(String filePath, DataValue data) {
	    try {
	        String existing = "";
	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            existing = reader.readLine(); 
	        } catch (IOException e) {
	        	System.out.println("No existing output file found");
	        }

	        String newContent;
	        if (existing == null || existing.isEmpty()) {
	            newContent = String.valueOf(data.getValue());
	        } else {
	            newContent = existing + "," + data.getValue();
	        }

	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	            writer.write(newContent);
	        }

	        return true;

	    } catch (IOException e) {
	        throw new RuntimeException("Error writing output file " + e.getMessage(), e);
	    }
	}


}