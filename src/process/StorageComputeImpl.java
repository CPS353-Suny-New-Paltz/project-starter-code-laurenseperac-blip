package process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StorageComputeImpl implements StorageComputeAPI {

    @Override
    public DataValue readInput(String filePath) {
        try (var reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
            String line = reader.readLine();
            if (line == null) {
            	return null;
            }
            return new DataValueImpl(Integer.parseInt(line.trim()));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean writeOutput(String filePath, DataValue data) {
    	try {
            File file = new File(filePath);
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs(); 
            }

            boolean appendComma = file.exists() && file.length() > 0;

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                if (appendComma) {
                	writer.write(",");
                }
                writer.write(String.valueOf(data.getValue()));
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Error writing output file: " + e.getMessage(), e);
        }
    }

	@Override
	public MultiDataValue readAllInputs(String filePath) {
		List<Integer> numbers = new ArrayList<>();
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split("[,\\s]+");
				for (String p: parts) {
					if (!p.isBlank()) {
						numbers.add(Integer.parseInt(p.trim()));
					}
				}
			}
		} catch (IOException e) {
			throw new RuntimeException("Error reading input file: " + e.getMessage(), e);
		}
		return new MultiDataValueImpl(numbers);
	}

	@Override
    public boolean writeAllOutputs(String filePath, List<Integer> values) {
		 try {
	            File file = new File(filePath);
	            if (file.getParentFile() != null) file.getParentFile().mkdirs(); 

	            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
	                for (int i = 0; i < values.size(); i++) {
	                    writer.write(String.valueOf(values.get(i)));
	                    if (i < values.size() - 1) {
	                    	writer.write(","); 
	                    }
	                }
	            }
	            return true;
	        } catch (IOException e) {
	            throw new RuntimeException("Error writing output file: " + e.getMessage(), e);
	        }
    }
}