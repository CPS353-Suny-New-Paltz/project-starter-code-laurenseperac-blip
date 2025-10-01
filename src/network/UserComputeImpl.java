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
		this.engine = engine;
		this.storage = storage;
	}

	@Override
	public JobResponse submitJob(JobRequest request) {
		try {
			//reads ints from storage
			DataValue inputVal = storage.readInput(request.getInputSource());
			
			//pass to compute engine
			ComputeRequest compReq = () -> inputVal.getValue();
			ComputeResult compRes = engine.performComputation(compReq);
			
			//write to storage
			DataValue outputVal = new DataValueImpl(compRes.getOutput());
			storage.writeOutput(request.getOutputDestination(), outputVal);
			
			//return status
			return new JobResponse() {
				@Override
				public boolean isSuccess() {
					return true;
				}
				@Override
				public String getMessage() {
					return "Job completed successfully";
				}
			};
		} catch (Exception e) {
			return new JobResponse() {
				
				@Override
				public boolean isSuccess() {
					return false;
				}
				
				@Override
				public String getMessage() {
					return "Job failed";
				}
			};
		}
