package project.checkpointtests;

import conceptual.ComputeEngineAPI;
import conceptual.ComputeEngineImpl;
import conceptual.ComputeRequest;
import conceptual.ComputeRequestImpl;
import conceptual.ComputeResult;
import process.StorageComputeAPI;
import process.StorageComputeImpl;


public class ManualTestingFramework {

    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    private static final int MAX_ITERATIONS = 100;

    public static void main(String[] args) {
        try {
            ComputeEngineAPI engine = new ComputeEngineImpl(null);
            StorageComputeAPI storage = new StorageComputeImpl();

            int iterations = 0;
            java.util.List<String> inputLines = java.nio.file.Files.readAllLines(
                    java.nio.file.Paths.get(INPUT)
            );

            if (inputLines.isEmpty()) {
            	return;
            }

            StringBuilder outputLine = new StringBuilder();

            for (String line : inputLines) {
                if (iterations++ >= MAX_ITERATIONS) {
                	break;
                }

                int val;
                try {
                    val = Integer.parseInt(line.trim());
                } catch (NumberFormatException e) {
                    continue;
                }

                ComputeRequest request = new ComputeRequestImpl(val);
                ComputeResult result = engine.performComputation(request);

                if (outputLine.length() > 0) {
                	outputLine.append(",");
                }
                outputLine.append(result.getOutput());
            }

            java.nio.file.Files.write(
                    java.nio.file.Paths.get(OUTPUT),
                    outputLine.toString().getBytes()
            );

            System.out.println("Computation finished. Results written to " + OUTPUT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
