package conceptual;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import process.DataValue;
import process.DataValueImpl;
import integrationinfra.InMemoryInputConfig;
import integrationinfra.InMemoryOutputConfig;
import network.UserComputeAPI;
import network.UserComputeImpl;
import integrationinfra.InMemoryDataStore;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag("skip")
public class ComputeEngineIntegrationTest {

	@Test
	void testEndtoEnd() {
		List<Integer> input = new ArrayList<>(Arrays.asList(1, 10, 25));
		List<String> output = new ArrayList<>();

		InMemoryInputConfig inputConfig = new InMemoryInputConfig(input);
		InMemoryOutputConfig outputConfig = new InMemoryOutputConfig(output);

		InMemoryDataStore store = new InMemoryDataStore(inputConfig, outputConfig);

		ComputeEngineAPI engine = new ComputeEngineImpl(null);
		
		UserComputeAPI user = new UserComputeImpl(null, null);

		while (!inputConfig.getInput().isEmpty()) {
			DataValue val = store.readInput(null);
			ComputeResult result = engine.performComputation(() -> val.getValue());
			DataValue outputVal = new DataValueImpl(result.getOutput());
			store.writeOutput(null, outputVal);
		}
		assertFalse(output.isEmpty());
		assertTrue(output.contains("Value: -1"));
	}

}