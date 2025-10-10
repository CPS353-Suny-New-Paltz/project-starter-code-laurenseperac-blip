package network;

import java.util.ArrayList;
import java.util.List;

import conceptual.ComputeEngineAPI;
import conceptual.ComputeRequest;
import conceptual.ComputeResult;
import process.DataValue;
import process.DataValueImpl;
import process.StorageComputeAPI;
import project.annotations.NetworkAPI;



@NetworkAPI
public class UserComputeImpl implements UserComputeAPI{
	private final ComputeEngineAPI engine;
	private final StorageComputeAPI storage;

	public UserComputeImpl(ComputeEngineAPI engine, StorageComputeAPI storage) {
		if (engine == null || storage == null) {
			throw new IllegalArgumentException("Engine and storage must not be null");
		}
		this.engine = engine;
		this.storage = storage;
	}

	@Override
	public JobResponse submitJob(JobRequest request) {
	    if (request == null) {
	        return new JobResponseImpl(false, "Invalid job request: null");
	    }

	    if (request.getInputSource() == null || request.getOutputDestination() == null) {
	        return new JobResponseImpl(false, "Invalid file paths in request");
	    }

	    try {
	        List<Integer> results = new ArrayList<>();

	        DataValue inputVal;
	        while ((inputVal = storage.readInput(request.getInputSource())) != null && inputVal.getValue() != -1) {
	            final int value = inputVal.getValue(); 
	            ComputeRequest compReq = () -> value;

	            ComputeResult compRes = engine.performComputation(compReq);
	            if (compRes != null && compRes.getOutput() != -1) {
	                results.add(compRes.getOutput());
	            }
	        }

	        if (results.isEmpty()) {
	            return new JobResponseImpl(false, "No valid inputs processed");
	        }

	        storage.writeAllOutputs(request.getOutputDestination(), results);

	        return new JobResponseImpl(true, "Job completed successfully");

	    } catch (RuntimeException e) {
	        return new JobResponseImpl(false, "Unexpected runtime error: " + e.getMessage());
	    } catch (Exception e) {
	        return new JobResponseImpl(false, "Job failed: " + e.getMessage());
	    }
	}
}
