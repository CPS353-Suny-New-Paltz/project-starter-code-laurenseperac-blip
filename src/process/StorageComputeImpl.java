package process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StorageComputeImpl implements StorageComputeAPI {

    @Override
    public DataValue readInput(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                return null;
            }
            String line = Files.readString(path).lines().findFirst().orElse(null);
            if (line == null) {
                return null;
            }
            // remove the first line after reading
            Files.writeString(path, line.lines().skip(1).reduce("", (a, b) -> a + b + "\n"));
            return new DataValueImpl(Integer.parseInt(line.trim()));
        } catch (IOException | NumberFormatException e) {
            return null;
        }
    }

    @Override
    public boolean writeOutput(String filePath, DataValue data) {
        try {
            Path path = Paths.get(filePath);
            String existing = "";
            if (Files.exists(path)) {
                existing = Files.readString(path);
            }

            String newContent = existing.isEmpty() ? String.valueOf(data.getValue())
                    : existing + "," + data.getValue();

            Files.writeString(path, newContent);
            return true;

        } catch (IOException e) {
            throw new RuntimeException("Error writing output file: " + e.getMessage(), e);
        }
    }
}