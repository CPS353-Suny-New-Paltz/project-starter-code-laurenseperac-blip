package conceptual;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestComputeEngine {

	@Test
	void testPerformComputationWithMock() {
		ComputeRequest mockRequest = mock(ComputeRequest.class);
		when(mockRequest.getInput()).thenReturn(5);

		ComputeEngineAPI engine = new ComputeEngineImpl(null);
		ComputeResult result = engine.performComputation(mockRequest);

		assertNotNull(result);
		assertEquals(3, result.getOutput());
	}

}