package process;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Tag("skip")
public class TestStorageCompute {

	@Test
	void testReadWrite() {
		StorageComputeAPI storage = new StorageComputeImpl();
		DataValue input = storage.readInput("inputKey");

		assertNotNull(input);
		assertEquals(-1, input.getValue());

		boolean success = storage.writeOutput("outputKey", input);
		assertTrue(success);
	}

}