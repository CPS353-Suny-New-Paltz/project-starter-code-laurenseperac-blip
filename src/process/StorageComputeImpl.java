package process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StorageComputeImpl implements StorageComputeAPI {

    @Override
    public DataValue readInput(String filePath) {
        try (var reader = new java.io.BufferedReader(new java.io.FileReader(filePath))) {
            String line = reader.readLine();
            if (line == null) return null;
            return new DataValueImpl(Integer.parseInt(line.trim()));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean writeOutput(String filePath, DataValue data) {
        try {
            File file = new File(filePath);
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
}
