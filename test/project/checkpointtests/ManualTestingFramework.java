package project.checkpointtests;

import conceptual.ComputeEngineAPI;
import conceptual.ComputeEngineImpl;
import conceptual.ComputeRequest;
import conceptual.ComputeRequestImpl;
import conceptual.ComputeResult;
import network.UserComputeAPI;
import network.UserComputeImpl;
import process.StorageComputeAPI;
import process.StorageComputeImpl;
import process.DataValue;
import process.DataValueImpl;

public class ManualTestingFramework {

    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    private static final int MAX_ITERATIONS = 100;

    public static void main(String[] args) {
        try {
            ComputeEngineAPI engine = new ComputeEngineImpl(null);
            StorageComputeAPI storage = new StorageComputeImpl();
            UserComputeAPI user = new UserComputeImpl(engine, storage);

            int iterations = 0;
            while (true) {
                if (iterations++ >= MAX_ITERATIONS) break; 

                DataValue inputVal;
                try {
                    inputVal = storage.readInput(INPUT);
                } catch (RuntimeException e) {
                    break;
                }
                if (inputVal == null) {
                    break;
                }

                ComputeRequest request = new ComputeRequestImpl(inputVal.getValue());
                ComputeResult result = engine.performComputation(request);

                storage.writeOutput(OUTPUT, new DataValueImpl(result.getOutput()));
            }

            System.out.println("Computation finished. Results written to " + OUTPUT);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
