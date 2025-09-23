package network;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestUserCompute {
	
	@Test
	void testSubmitJobWithMock() {
		JobRequest mockRequest = mock(JobRequest.class);
		when(mockRequest.getInputSource()).thenReturn("input.txt");
		when(mockRequest.getOutputDestination()).thenReturn("output.txt");
		when(mockRequest.getDelimiter()).thenReturn(",");
		
		UserComputeAPI api = new UserComputeImpl(null);
		JobResponse response = api.submitJob(mockRequest);
		
		assertNotNull(response);
		assertFalse(response.isSuccess());
		assertEquals("unimplemented", response.getMessage());
	}

}
