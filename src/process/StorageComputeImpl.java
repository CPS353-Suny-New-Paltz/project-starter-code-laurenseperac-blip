package process;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageComputeImpl implements StorageComputeAPI {

    @Override
    public DataValue readInput(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line == null) {
            	return null;
            }
            int value = Integer.parseInt(line.trim());
            return new DataValueImpl(value);
        } catch (IOException | NumberFormatException e) {
            throw new RuntimeException("Error reading input file: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean writeOutput(String filePath, DataValue data) {
        try {
            Path path = Paths.get(filePath);
            boolean fileExists = Files.exists(path);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                if (fileExists) {
                    writer.write(",");
                }
                writer.write(String.valueOf(data.getValue()));
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Error writing output file: " + e.getMessage(), e);
        }
    }
}
