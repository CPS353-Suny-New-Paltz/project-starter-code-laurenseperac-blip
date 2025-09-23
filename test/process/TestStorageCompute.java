package process;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestStorageCompute {

	@Test
	void testReadWrite() {
		StorageComputeAPI storage = new StorageComputeImpl(null);
		DataValue input = storage.readInput("inputKey");
		
		assertNotNull(input);
		assertEquals(-1, input.getValue());
		
		boolean success = storage.writeOutput("outputKey", input);
		assertFalse(success);
	}
	
}
