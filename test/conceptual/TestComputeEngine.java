package conceptual;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestComputeEngine {

	@Test
	void testPerformComputationWithMock() {
		ComputeRequest mockRequest = mock(ComputeRequest.class);
		when(mockRequest.getInput()).thenReturn(50);

		ComputeEngineAPI engine = new ComputeEngineImpl(null);
		ComputeResult result = engine.performComputation(mockRequest);

		assertNotNull(result);
		assertEquals(-1, result.getOutput());
	}

}