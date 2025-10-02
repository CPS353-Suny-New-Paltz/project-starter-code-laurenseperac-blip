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

import java.util.ArrayList;
import java.util.List;

public class ManualTestingFramework {
    
    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
        // TODO 1:
        // Instantiate a real (ie, class definition lives in the src/ folder) implementation 
        // of all 3 APIs
        //
    	   // TODO 2:
        // Run a computation with an input file of <root project dir>/manualTestInput.txt
        // and an output of <root project dir>/manualTestOutput.txt, with a delimiter of ',' 
        //
        //
        // Helpful hint: the working directory of this program is <root project dir>,
        // so you can refer to the files just using the INPUT/OUTPUT constants. You do not 
        // need to (and should not) actually create those files in your repo
    	
    	try {
            ComputeEngineAPI engine = new ComputeEngineImpl(null);
            StorageComputeAPI storage = new StorageComputeImpl();
            UserComputeAPI user = new UserComputeImpl(engine, storage);

            List<String> results = new ArrayList<>();

            DataValue inputVal;
            while (true) {
                try {
                    inputVal = storage.readInput(INPUT);
                } catch (RuntimeException e) {
                    break;
                }

                if (inputVal == null) break;

                ComputeRequest request = new ComputeRequestImpl(inputVal.getValue());
                ComputeResult result = engine.performComputation(request);

                results.add(String.valueOf(result.getOutput()));
            }

            String joined = String.join(",", results);
            storage.writeOutput(OUTPUT, new DataValueImpl(joined.hashCode())); 

            System.out.println("Computation finished. Results written to " + OUTPUT);

        } catch (Exception e) {
            e.printStackTrace();
        }
     
    }
}
