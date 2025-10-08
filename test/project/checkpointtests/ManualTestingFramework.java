package project.checkpointtests;

import conceptual.ComputeEngineAPI;
import conceptual.ComputeEngineImpl;
import conceptual.ComputeRequest;
import conceptual.ComputeRequestImpl;
import conceptual.ComputeResult;
import process.StorageComputeAPI;
import process.StorageComputeImpl;
import network.UserComputeImpl;
import network.JobResponse;
import network.JobRequest;
import network.JobRequestImpl;
import network.JobResponseImpl;
import network.UserComputeAPI;


public class ManualTestingFramework {

	StorageComputeAPI storage = new StorageComputeImpl();
	ComputeEngineAPI engine = new ComputeEngineImpl();
	UserComputeAPI userAPI = new UserComputeImpl(engine, storage);
    
	JobRequest job = new JobRequestImpl("numbers.txt", "output.txt", ",");
	JobResponse response = userAPI.submitJob(job);
	
	System.out.println("Job success: " + response.isSuccess());
	System.out.println("Message: " + response.getMessage());
}
