package process;

import java.io.*;
import java.nio.file.*;

public class StorageComputeImpl implements StorageComputeAPI {

    @Override
    public DataValue readInput(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line == null) return null;
            return new DataValueImpl(Integer.parseInt(line.trim()));
        } catch (IOException | NumberFormatException e) {
            return null;
        }
    }

    @Override
    public boolean writeOutput(String filePath, DataValue data) {
        try {
            String existing = "";
            Path path = Paths.get(filePath);
            if (Files.exists(path)) {
                existing = Files.readString(path);
            }

            String newContent = existing.isEmpty() ? 
                                String.valueOf(data.getValue()) : 
                                existing + "," + data.getValue();

            Files.writeString(path, newContent);
            return true;

        } catch (IOException e) {
            throw new RuntimeException("Error writing output file: " + e.getMessage(), e);
        }
    }
}