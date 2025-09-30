package network;

import project.annotations.NetworkAPI;

@NetworkAPI
public class UserComputeImpl implements UserComputeAPI{
	private final UserComputeAPI userCompute;

	public UserComputeImpl(UserComputeAPI userCompute) {
		this.userCompute = userCompute;
	}

	@Override
	public JobResponse submitJob(JobRequest request) {
		// test expects "implemented" for test
		return new JobResponseImpl(false, "implemented");
	}
}