package network;

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
	        DataValue inputVal;
	        boolean anyWritten = false;

	        while ((inputVal = storage.readInput(request.getInputSource())) != null) {
	            final DataValue currentVal = inputVal; 
	            ComputeRequest compReq = () -> currentVal.getValue(); 
	            ComputeResult compRes = engine.performComputation(compReq);

	            if (compRes == null || compRes.getOutput() == -1) {
	                continue; 
	            }

	            DataValue outputVal = new DataValueImpl(compRes.getOutput());
	            boolean success = storage.writeOutput(request.getOutputDestination(), outputVal);
	            if (!success) {
	                return new JobResponseImpl(false, "Output write failed");
	            }
	            anyWritten = true;
	        }

	        if (!anyWritten) {
	            return new JobResponseImpl(false, "No valid inputs processed");
	        }

	        return new JobResponseImpl(true, "Job completed successfully");

	    } catch (RuntimeException e) {
	        return new JobResponseImpl(false, "Unexpected runtime error: " + e.getMessage());
	    } catch (Exception e) {
	        return new JobResponseImpl(false, "Job failed: " + e.getMessage());
	    }
	}
}
