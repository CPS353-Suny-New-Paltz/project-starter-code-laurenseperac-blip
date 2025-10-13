package network;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import conceptual.ComputeEngineAPI;
import conceptual.ComputeResult;
import conceptual.ComputeRequest;
import process.DataValue;
import process.MultiDataValue;
import process.StorageComputeAPI;

public class UserComputeImplIntegrationTest {
	
	@Test
	void testStorageThrowsExceptionHandledGracefully() {
		StorageComputeAPI failingStorage = new StorageComputeAPI() {

			@Override
			public DataValue readInput(String filePath) {
				throw new RuntimeException("Simulated read failure");
			}

			@Override
			public boolean writeOutput(String filePath, DataValue value) {
				throw new RuntimeException("Simulated write failure");
			}

			@Override
			public MultiDataValue readAllInputs(String filePath) {
				throw new RuntimeException("Simulated readAllInputs failure");
			}

			@Override
			public boolean writeAllOutputs(String filePath, List<Integer> values) {
				throw new RuntimeException("Simulated writeAllOutputs failure");
			}
			
		};
		
		ComputeEngineAPI mockEngine = (ComputeRequest req) -> new ComputeResult() {
			@Override
			public int getOutput() {
				return 42;
			}
		};
		
		UserComputeImpl impl = new UserComputeImpl(mockEngine, failingStorage);
		
		JobRequest request = new JobRequestImpl("input.txt", "output.txt", ",");
		
		JobResponse response = impl.submitJob(request);
		
		assertNotNull(response);
        assertFalse(response.isSuccess());
        assertTrue(response.getMessage().toLowerCase().contains("unexpected runtime error"));
	}

}
