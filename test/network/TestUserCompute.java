package network;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Tag("skip")
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
		assertEquals("implemented", response.getMessage());
	}

}