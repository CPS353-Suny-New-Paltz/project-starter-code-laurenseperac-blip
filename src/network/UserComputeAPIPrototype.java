package network;

public class UserComputeAPIPrototype implements UserComputeAPI {
	@Override
	@NetworkAPIPrototype
	public JobResponse submitJob(JobRequest request) {
		return new JobResponse() {
			public boolean isSuccess() { 
				return true; 
			}
			public String getMessage() {
				return "Prototype response"; 
			}
		};
	}
}
